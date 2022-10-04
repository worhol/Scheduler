package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wgu.softwaretwo.samircokic.DAO.AppointmentDao;
import wgu.softwaretwo.samircokic.DAO.CustomerDao;

public class Schedule {

    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    public static void updateAppointment(int index, Appointment appointment){
        appointments.set(index,appointment);
    }
    public static void deleteAppointment(Appointment appointment){
       appointments.remove(appointment);
    }
    public static ObservableList<Appointment> getAppointments(){
        return appointments;
    }

//    public static void refreshAppointments(){
//        appointments.clear();
//    }

    public static ObservableList<Customer> customers = FXCollections.observableArrayList();

    public static ObservableList<Customer> getCustomers() {
        return customers;
    }

    public static void addCustomers(Customer customer) {
        customers.add(customer);
    }
    public  static  void refreshCustomers(){
        customers.clear();
        CustomerDao.setCustomer();
    }
    public static void refreshAppointments(){
        appointments.clear();
    }
    public static void deleteCustomer(Customer customer){
        customers.remove(customer);
    }

}