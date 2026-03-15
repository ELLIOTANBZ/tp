package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class SameCompanySameRolePredicateTest {

    Name name1 = new Name("name1");
    Role role1 = new Role("roleOne");

    Name name2 = new Name("name1");
    Role role2 = new Role("roleOne");

    Name name3 = new Name("Amy Bee");
    Role role3 = new Role("CEO");

    SameCompanySameRolePredicate firstPredicate = new SameCompanySameRolePredicate(name1, role1);
    SameCompanySameRolePredicate secondPredicate = new SameCompanySameRolePredicate(name2, role2);
    SameCompanySameRolePredicate thirdPredicate = new SameCompanySameRolePredicate(name3, role3);

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        assertTrue(firstPredicate.equals(secondPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(thirdPredicate));
    }

    @Test
    public void test_sameCompanySameRole_returnsTrue() {
        // Same Application
        assertTrue(firstPredicate.test(new PersonBuilder().withName("name1").withRole("roleOne").build()));
        assertTrue(thirdPredicate.test(new PersonBuilder().build()));
    }

    @Test
    public void test_differentCompanyOrRole_returnsFalse() {
        // Different Role or Name
        assertFalse(firstPredicate.test(new PersonBuilder().withName("name1").build()));
        assertFalse(firstPredicate.test(new PersonBuilder().withRole("roleOne").build()));

        // Different Name and Role
        assertFalse(firstPredicate.test(new PersonBuilder().build()));
    }

    @Test
    public void toStringMethod() {
        String expected = SameCompanySameRolePredicate.class.getCanonicalName() + "{Company=Amy Bee, Role=CEO}";
        assertEquals(expected, thirdPredicate.toString());
    }
}
