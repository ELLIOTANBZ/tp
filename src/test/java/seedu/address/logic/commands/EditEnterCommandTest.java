package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Application;
import seedu.address.model.person.Name;
import seedu.address.model.person.Role;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditEnterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditEnterCommand editCommand = new EditEnterCommand(outOfBoundIndex);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonNameAndRoleUnfilteredList_failure() {
        Name validName = model.getFilteredPersonList().get(0).getName();
        Role validRole = model.getFilteredPersonList().get(0).getRole();

        //Invalid name, valid role
        EditEnterCommand firstEditCommand = new EditEnterCommand(new Name("invalid"), validRole);
        assertCommandFailure(firstEditCommand, model, Messages.MESSAGE_INVALID_APPLICATION_IDENTIFIER);

        //Valid name, invalid role
        EditEnterCommand secondEditCommand = new EditEnterCommand(validName, new Role("invalid"));
        assertCommandFailure(secondEditCommand, model, Messages.MESSAGE_INVALID_APPLICATION_IDENTIFIER);

        //Invalid name and role
        EditEnterCommand thirdEditCommand = new EditEnterCommand(new Name("invalid"), new Role("invalid"));
        assertCommandFailure(thirdEditCommand, model, Messages.MESSAGE_INVALID_APPLICATION_IDENTIFIER);
    }

    @Test
    public void execute_validPersonIndexUnfilteredList_success() {
        Index firstIndex = Index.fromZeroBased(0);
        EditEnterCommand editCommand = new EditEnterCommand(firstIndex);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Application firstApplication = expectedModel.getFilteredPersonList().get(0);
        firstApplication.setBeingEdited(true);

        String expectedMessage = String.format(EditEnterCommand.MESSAGE_ENTER_EDITING_MODE_ACKNOWLEDGEMENT,
                Messages.format(firstApplication));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validPersonNameAndRoleUnfilteredList_success() {
        Name validName = model.getFilteredPersonList().get(0).getName();
        Role validRole = model.getFilteredPersonList().get(0).getRole();
        EditEnterCommand editCommand = new EditEnterCommand(validName, validRole);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Application firstApplication = expectedModel.getFilteredPersonList().get(0);
        firstApplication.setBeingEdited(true);

        String expectedMessage = String.format(EditEnterCommand.MESSAGE_ENTER_EDITING_MODE_ACKNOWLEDGEMENT,
                Messages.format(firstApplication));
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

}
