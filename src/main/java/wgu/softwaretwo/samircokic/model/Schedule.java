package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wgu.softwaretwo.samircokic.DAO.CustomerDao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;

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
    public static void deleteCustomersAppointments(int id){
        for (Appointment appointment: appointments){
            if (appointment.getAppointmentId()==id){
                appointments.remove(appointment);
            }
        }
    }
    public static ObservableList<Appointment> getAppointments(){
        return appointments;
    }
    public static String appointmentAlert(ZoneId zoneId){
        LocalDateTime currentTime = LocalDateTime.now(zoneId);
        String meetingAlert = "";
        for (Appointment appointment: appointments){
            long timeDifference = ChronoUnit.MINUTES.between(currentTime,appointment.getStart());
           if (timeDifference<15&&timeDifference>0){
               meetingAlert = "You have upcoming appointment with the id: "+appointment.getAppointmentId()+" at: "+appointment.getStart();
            }else {
               meetingAlert = "There are no upcoming appointments";
           }
        }
        return meetingAlert;
    }
public static ObservableList<User> users = FXCollections.observableArrayList();

    public static int getUserID() {
        return users.get(0).getId();
    }
    public static void addUser(User user){
        users.add(user);
    }
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
