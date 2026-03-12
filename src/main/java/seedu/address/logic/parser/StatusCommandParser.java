package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.logic.commands.StatusCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Status;

/**
 * Parses input arguments and creates a new StatusCommand object.
 */
public class StatusCommandParser implements Parser<StatusCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StatusCommand
     *     and returns a StatusCommand object for execution.
     * @param args user input arguments
     * @return StatusCommand object containing the parsed name and status
     * @throws ParseException if the user input does not conform to the expected format
     */
    public StatusCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STATUS);

        if (!argMultimap.getValue(PREFIX_NAME).isPresent() || !argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE));
        }

        String name = argMultimap.getValue(PREFIX_NAME).get();
        Status status = new Status(argMultimap.getValue(PREFIX_STATUS).get());

        return new StatusCommand(name, status);
    }
}
