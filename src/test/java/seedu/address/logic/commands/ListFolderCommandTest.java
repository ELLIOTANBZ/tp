package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class ListFolderCommandTest {

    @Test
    public void execute_returnsListFoldersSignal() {
        Model model = new ModelManager();
        CommandResult result = new ListFolderCommand().execute(model);
        assertTrue(result.isListFolders());
    }

}
