package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all existing folders (JSON files) in the data directory.
 */
public class ListFolderCommand extends Command {

    public static final String COMMAND_WORD = "folders";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all existing folders in the data directory.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_NO_FOLDERS = "No folders found in data directory.";
    public static final String MESSAGE_FOLDERS_LISTED = "Folders available:\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(true);
    }

}
