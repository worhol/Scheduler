package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

/**
 * <p>This class is the blueprint for the appointment object and its functionalities</p>
 *
 * @author Samir Cokic
 */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;
    private int customerId;
    private int userId;

    /**
     * @param appointmentId  unique appointment number.
     * @param title the title of the appointment.
     * @param description the description of the appointment.
     * @param location the location of the appointment.
     * @param contact the main contact of the appointment.
     * @param type the type of the appointment.
     * @param start scheduled start time of the appointment.
     * @param end scheduled end time of the appointment.
     * @param customerId unique customer's id number.
     * @param userId unique user's id number
     */
    public Appointment(int appointmentId, String title, String description, String location, String contact, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerId = customerId;
        this.userId = userId;
    }

    /**
     * <p>This method returns appointment id number</p>
     *
     * @return appointment id number
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     *<p>This method takes the int as the argument and sets the appointment id number.</p>
     *
     * @param appointmentId appointment id number
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * <p>This method returns the title of the appointment.</p>
     *
     * @return title of the appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * <p>This method takes the String as the argument and sets the appointment title.</p>
     * @param title the title for the appointment.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * <p>This method returns the description of the meeting.</p>
     *
     * @return description of the meeting.
     */
    public String getDescription() {
        return description;
    }

    /**
     * <p>This method takes the String as the argument and sets the appointment description.</p>
     *
     * @param description the description of the meeting.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <p>This method returns the location of the appointment.</p>
     * @return the location of the appointment.
     */
    public String getLocation() {
        return location;
    }

    /**
     * <p>This method takes string and sets it as the location of the appointment.</p>
     *
     * @param location location of the appointment.
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Observable list that contains contacts ids.
     */
    public static ObservableList contactIDs = FXCollections.observableArrayList();
    /**
     * Observable list that contains contacts names.
     */
    public static ObservableList contactName = FXCollections.observableArrayList();
    /**
     * Observable list that contains appointment types.
     */
    public static ObservableList typeOfAppointment = FXCollections.observableArrayList("Preliminary", "General", "De-Briefing");
    /**
     * Observable list that contains customer ids
     */
    public static ObservableList customerIDs = FXCollections.observableArrayList();
    /**
     * Observable list that contains user ids
     */
    public static ObservableList userIDs = FXCollections.observableArrayList();

    /**
     * <p>This method returns observable list with user ids</p>
     *
     * @return user id observable list.
     */
    public static ObservableList getUserIDs() {
        return userIDs;
    }

    /**
     * <p>this method adds user argument to userID observable list.</p>
     *
     * @param id user id
     */
    public static void addUserID(int id){
        userIDs.add(id);
    }

    /**
     * <p>This method returns observable list of customer ids.</p>
     *
     * @return customer id observable list.
     */
    public static ObservableList getCustomerIDs() {
        return customerIDs;
    }

    /**
     * <p>This method takes int id as an argument and adds it to the customerIDs observable list.</p>
     *
     * @param id customer id
     */
    public static void addCustomerID(int id){
       customerIDs.add(id);
    }

    /**
     * <p>This method returns the observable list containing type of the appointment.</p>
     *
     * @return type of the appointment observable list
     */
    public static ObservableList getTypeOfAppointment() {
        return typeOfAppointment;
    }

    /**
     * <p>This method returns the observable list containing contact ids.</p>
     *
     * @return contact ids observable list
     */
    public static ObservableList getContactIDs() {
        return contactIDs;
    }

    /**
     * <p>This method returns the observable list containing names of the contacts.</p>
     *
     * @return contact names observable list
     */
    public static ObservableList getContactName() {
        return contactName;
    }

    /**
     * <p>This method takes string name as an argument and adds it to the contact name observable list.</p>
     *
     * @param name contact name
     */
    public static void addContactName(String name){
        contactName.add(name);
    }


    /**
     * @return contact as a string
     */
    public String getContact() {
        return contact;
    }
    /**
     * <p>This method takes string contact as an argument and sets it as a contact name.</p>
     *
     * @param contact the name of the contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * <p>This method returns type of the appointment.</p>
     *
     * @return type of the appointment
     */
    public String getType() {
        return type;
    }

    /**
     * <p>This method takes string as the argument and sets it as a type of the appointment.</p>
     *
     * @param type of the appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * <p>this method returns start time and date of the appointment</p>
     *
     * @return start of the appointment as a localdateTime.
     */
    public LocalDateTime getStart() {
        return start;
    }


    /**
     * <p>this method sets start time and date of the appointment</p>
     *
     * @param  start of the appointment as a LocalDateTime.
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * <p>this method returns end time and date of the appointment</p>
     *
     * @return end of the appointment as a localdateTime.
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * <p>this method sets end time and date of the appointment</p>
     *
     * @param  end of the appointment as a LocalDateTime.
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * <p>This method returns customer id</p>
     *
     * @return customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * <p>This method returns user id</p>
     *
     * @return user id
     */
    public int getUserId() {
        return userId;
    }

}
