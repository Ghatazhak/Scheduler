package model;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int customerId;
    private int userId;
    private int ContactId;

    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int userId, int contactId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerId = customerId;
        this.userId = userId;
        ContactId = contactId;
    }
    /** This method gets appointmentID.*/
    public int getAppointmentId() {
        return appointmentId;
    }
    /** This method gets title.*/
    public String getTitle() {
        return title;
    }
    /** This method sets title.
     * @param title */
    public void setTitle(String title) {
        this.title = title;
    }
    /** This method gets description.*/
    public String getDescription() {
        return description;
    }

    /** This method gets location.*/
    public String getLocation() {
        return location;
    }

    /** This method gets type.*/
    public String getType() {
        return type;
    }

    /** This method sets type. */
    public void setType(String type) {
        this.type = type;
    }

    /** This method gets start date/time. */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /** This method gets end date/time. */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /** This method gets customer id.*/
    public int getCustomerId() {
        return customerId;
    }


    /** This method gets user id.*/
    public int getUserId() {
        return userId;
    }

    /** This method gets contact id.*/
    public int getContactId() {
        return ContactId;
    }

}
