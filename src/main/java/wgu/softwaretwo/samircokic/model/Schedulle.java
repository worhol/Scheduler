package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Schedulle {

    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    public static void updateAppointment(int index, Appointment appointment){
        appointments.set(index,appointment);
    }
    public static void deleteAppointment(Appointment appointment){
        //this can be lambda stream
    }
    public static ObservableList<Appointment> getAppointments(){
        return appointments;
    }

    public static ObservableList<Customer> customers = FXCollections.observableArrayList();

    public static ObservableList<Customer> getCustomers() {
        return customers;
    }

    public static void addCustomers(Customer customer) {
        customers.add(customer);
    }
    public static void deleteCustomer(Customer customer){
        customers.remove(customer);
    }

}
