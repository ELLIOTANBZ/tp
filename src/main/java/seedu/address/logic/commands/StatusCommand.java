package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Status;

/**
 * Updates the status of a person by name.
 */
public class StatusCommand extends Command {

    public static final String COMMAND_WORD = "status";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates a person's status.\n"
                                    + "Parameters: n/NAME s/STATUS\n" + "Example: status n/Alex Yeoh s/Interviewing";

    public static final String MESSAGE_SUCCESS = "Updated status: %1$s";

    private final String name;
    private final Status status;

    /**
     * Creates a StatusCommand to update the status of the specified person.
     * @param name Name of the person whose status is to be updated
     * @param status New status to set for the person
    */
    public StatusCommand(String name, Status status) {
        this.name = name;
        this.status = status;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> personList = model.getFilteredPersonList();

        Person target = null;

        for (Person p : personList) {
            if (p.getName().fullName.equalsIgnoreCase(name)) {
                target = p;
                break;
            }
        }

        if (target == null) {
            throw new CommandException("Person not found.");
        }

        Person updatedPerson = new Person(target.getName(), target.getPhone(), target.getEmail(), target.getAddress(),
                                        status, target.getTags());

        model.setPerson(target, updatedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedPerson));
    }
}
