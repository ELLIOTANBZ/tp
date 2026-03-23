package seedu.address.model.person;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Event class to represent upcoming tasks of an application.
 */
public class Upcoming {
    private final String eventName;
    private final Date eventDate;

    /**
     * Event with a deadline.
     * @param eventName event Description.
     * @param eventDate event date.
     */
    public Upcoming(String eventName, String eventDate) {
        this.eventName = eventName;
        this.eventDate = new Date(eventDate);
    }

    public String getEventName() {
        return eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, eventDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Upcoming", eventName)
                .add("Date", eventDate).toString();
    }

}
