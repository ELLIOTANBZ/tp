package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final boolean isIndexDelete;
    private final Index targetIndex;
    private final Name name;
    private final Role role;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.name = null;
        this.role = null;
        isIndexDelete = true;
    }

    public DeleteCommand(Name name, Role role) {
        this.targetIndex = null;
        this.name = name;
        this.role = role;
        isIndexDelete =  false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (isIndexDelete) {
            return executeIndexDelete(model);
        } else {
            return executeNormalDelete(model);
        }
    }

    public CommandResult executeIndexDelete(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Application personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    public CommandResult executeNormalDelete(Model model) throws CommandException {
        requireNonNull(model);
        List<Application> lastShownList = model.getFilteredPersonList();
        SameCompanySameRolePredicate predicate = new SameCompanySameRolePredicate(name, role);

        Application personToDelete = lastShownList.stream().filter(predicate).findFirst()
                .orElseThrow(() -> new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;

        if (isIndexDelete != otherDeleteCommand.isIndexDelete) {
            return false;
        }

        if (isIndexDelete) {
            return targetIndex.equals(otherDeleteCommand.targetIndex);
        } else {
            return name.equals(otherDeleteCommand.name)
                    && role.equals(otherDeleteCommand.role);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
