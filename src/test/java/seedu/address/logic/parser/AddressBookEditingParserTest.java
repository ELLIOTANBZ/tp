package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;
import seedu.address.testutil.ApplicationUtil;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class AddressBookEditingParserTest {

    private final AddressBookEditingParser parser = new AddressBookEditingParser();

    @Test
    public void parseCommand_edit() throws Exception {
        Application application = new ApplicationBuilder().build();
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(application).build();
        EditCommand command = (EditCommand) parser.parseCommand(ApplicationUtil
                .getEditApplicationDescriptorDetails(descriptor));
        assertEquals(new EditCommand(descriptor), command);
    }
}
