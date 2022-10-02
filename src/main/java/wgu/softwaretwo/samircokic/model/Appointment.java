package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;

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

//    int id = users.get(0).getId();

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public static ObservableList contactIDs = FXCollections.observableArrayList();
    public static ObservableList typeOfAppointment = FXCollections.observableArrayList();
    public static ObservableList customerIDs = FXCollections.observableArrayList();
    public static ObservableList userIDs = FXCollections.observableArrayList();

    public static ObservableList getUserIDs() {
        return userIDs;
    }
    public static void addUserID(int id){
        userIDs.add(id);
    }

    public static ObservableList getCustomerIDs() {
        return customerIDs;
    }
    public static void addCustomerID(int id){
       customerIDs.add(id);
    }

    public static ObservableList getTypeOfAppointment() {
        return typeOfAppointment;
    }
    public static void addType(String type){
        typeOfAppointment.add(type);
    }

    public static ObservableList getContactIDs() {
        return contactIDs;
    }
    public static void addContactID(int id){
        contactIDs.add(id);
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
