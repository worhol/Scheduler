package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import wgu.softwaretwo.samircokic.DAO.CustomerDao;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.Optional;

/**
 * <p>This class is the blueprint for appointments scheduling</p>
 *
 * @author Samir Cokic
 */
public class Schedule {

    /**
     * <p>This observable list holds the appointments</p>
     */
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    /**
     * <p>This method adds appointment to appointments list</p>
     *
     * @param appointment appointment to be added
     */
    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    /**
     * <p>This method removes appointment from the appointments list</p>
     *
     * @param appointment appointment to be removed
     */
    public static void deleteAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    /**
     * <p>This method takes LocalDateTime arguments and the int and returns boolean.
     * It searches appointments list for customer id, then it checks the meetings in the list for their scheduled time.
     * If time does not collide with other meetings it returns true, otherwise it returns false</p>
     *
     * @param start beginning of the meeting
     * @param end end of the meeting
     * @param customerID customer id
     * @return boolean true if scheduled meeting overlaps or false if not.
     */
    public static boolean appointmentOverlap(LocalDateTime start, LocalDateTime end, int customerID) {
        boolean isItOverlap = false;
        for (Appointment appointment : appointments) {
            if (appointment.getCustomerId() == customerID) {
                if ((start.isAfter(appointment.getStart()) && start.isBefore(appointment.getEnd())) || (start.isEqual(appointment.getStart()))
                        || (end.isAfter(appointment.getStart()) && end.isBefore(appointment.getEnd()))
                        ||(start.isBefore(appointment.getStart())&&end.isAfter(appointment.getEnd()))) {
                    isItOverlap = true;
                }
            }

        }
        return isItOverlap;
    }

    /**
     * <p>This method takes LocalDateTime arguments and the int and returns boolean.
     * It searches appointments list for customer id, then it checks the meetings in the list for their scheduled time.
     * If time does not collide with other meetings it returns true, otherwise it returns false.
     * If the user is keeping the start and end time of the meeting unchanged, the method will allow it.</p>
     *
     * @param start beginning of the meeting
     * @param end end of the meeting
     * @param customerID customer id
     * @param appointmentID appointment id
     * @return  boolean true if scheduled meeting overlaps or false if not.
     */
    public static boolean updateAppointmentOverlap(LocalDateTime start, LocalDateTime end, int customerID, int appointmentID){
        boolean isItOverlap = false;
        for (Appointment appointment : appointments) {
            if (appointment.getCustomerId() == customerID) {
                if ((start.isAfter(appointment.getStart()) && start.isBefore(appointment.getEnd()))
                        || ((start.isEqual(appointment.getStart()))&&end.isAfter(appointment.getEnd()))
                        || ((start.isEqual(appointment.getStart()))&&end.isBefore(appointment.getEnd()))
                        || (end.isAfter(appointment.getStart()) && end.isBefore(appointment.getEnd()))
                        ||(start.isBefore(appointment.getStart())&&end.isAfter(appointment.getEnd()))) {
                    isItOverlap = true;
                }else if(start.isEqual(appointment.getStart())&& end.isEqual(appointment.getEnd())){
                    if(appointmentID==appointment.getAppointmentId()){
                        isItOverlap = false;
                    }else {
                        isItOverlap = true;
                    }

                }
            }

        }
        return isItOverlap;
    }

    /**
     * <p>This method returns appointments observable list</p>
     *
     * @return appointments observable list
     */
    public static ObservableList<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * <p>This method iterates through the appointments list and looks for the appointments that will begin within 15 minutes.
     * If there is a meeting it returns the string that inform the user about meeting, if not it informs a user that there are no meetings. </p>
     *
     * @return String meeting alert.
     */
    public static String appointmentAlert(int id) {
        LocalDateTime now = LocalDateTime.now();
        String meetingAlert = "";
        for (Appointment appointment : appointments) {
            if(appointment.getUserId()!=id){
                continue;
            }
            long timeDifference = ChronoUnit.MINUTES.between(now, appointment.getStart());
            if (timeDifference <= 15 && timeDifference > 0) {
                meetingAlert = "You have upcoming appointment with the id: " + appointment.getAppointmentId() + " at: " + appointment.getStart();
                break;
            } else {
                meetingAlert = "There are no upcoming appointments";
            }
        }
        return meetingAlert;
    }

    /**
     * <p>Observable list of weekly appointments</p>
     */
    public static ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();

    /**
     * <p>This method takes the ZoneId object as the parameter and returns weekly appointments in observable list.
     * It checks for the first day of the week in a users zone and then select all the appointments in that week and adds them
     * to weekly appointments list.</p>
     *
     * @param zoneId user zone
     * @return observable list of the appointments
     */
    public static ObservableList<Appointment> getWeeklyAppointments(ZoneId zoneId) {
        weeklyAppointments.clear();
        LocalDateTime current = LocalDateTime.now();
        ZonedDateTime zonedDateTime = current.atZone(zoneId);
        ZonedDateTime firstDayOfTheWeek = zonedDateTime.with(ChronoField.DAY_OF_WEEK, 1);
        ZonedDateTime lastDayOfTheWeek = firstDayOfTheWeek.plusWeeks(1);
        for (Appointment appointment : appointments) {
            if (appointment.getStart().isAfter(firstDayOfTheWeek.toLocalDateTime())
//                    || appointment.getStart().isEqual(firstDayOfTheWeek.toLocalDateTime())
                    && appointment.getStart().isBefore(lastDayOfTheWeek.toLocalDateTime())) {
                weeklyAppointments.add(appointment);
            }
        }
        return weeklyAppointments;
    }

    /**
     * <p>This method iterates through the weekly appointments list and removes the appointment given as the argument</p>
     *
     * @param appointment appointment
     */
    public static void deleteWeeklyAppointment(Appointment appointment) {
        Iterator<Appointment> appointmentIterator = weeklyAppointments.iterator();
        while (appointmentIterator.hasNext()) {
            Appointment a = appointmentIterator.next();
            if (a.equals(appointment)) {
                appointmentIterator.remove();
            }
        }
    }
    /**
     * <p>This method iterates through the monthly appointments list and removes the appointment given as the argument</p>
     *
     * @param appointment appointment
     */
    public static void deleteMonthlyAppointment(Appointment appointment) {
        Iterator<Appointment> appointmentIterator = monthlyAppointments.iterator();
        while (appointmentIterator.hasNext()) {
            Appointment a = appointmentIterator.next();
            if (a.equals(appointment)) {
                appointmentIterator.remove();
            }
        }
    }
    /**
     * <p>Observable list of monthly appointments</p>
     */
    public static ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();

    /**
     * <p>This method takes the ZoneId object as the parameter and returns monthly appointments in observable list.
     * It checks for the first day of the month in a users zone and then select all the appointments in that month and adds them
     * to weekly appointments list.</p>
     *
     * @param zoneId user zone
     * @return observable list of the appointments
     */
    public static ObservableList<Appointment> getMonthlyAppointments(ZoneId zoneId) {
        monthlyAppointments.clear();
        LocalDateTime current = LocalDateTime.now();
        ZonedDateTime zonedDateTime = current.atZone(zoneId);
        ZonedDateTime firstDayOfTheMonth = zonedDateTime.with(ChronoField.DAY_OF_MONTH, 1);
        ZonedDateTime lastDayOfTheMonth = firstDayOfTheMonth.plusMonths(1);
        for (Appointment appointment : appointments) {
            if (appointment.getStart().isAfter(firstDayOfTheMonth.toLocalDateTime())
//                    || appointment.getStart().isEqual(firstDayOfTheMonth.toLocalDateTime())
                    && appointment.getStart().isBefore(lastDayOfTheMonth.toLocalDateTime())) {
                monthlyAppointments.add(appointment);
            }
        }
        return monthlyAppointments;
    }


    /**
     * <p>Observable list of the users</p>
     */
    public static ObservableList<User> users = FXCollections.observableArrayList();

    /**
     * <p>This method returns first element in users list and gets it's id</p>
     *
     * @return user id
     */
    public static int getUserID() {
        return users.get(0).getId();
    }

    /**
     * <p>This method takes user object as the argument and adds it to the users list.</p>
     *
     * @param user user
     */
    public static void addUser(User user) {
        users.add(user);
    }

    /**
     * <p>Customers observable list</p>
     */
    public static ObservableList<Customer> customers = FXCollections.observableArrayList();

    /**
     * <p>This method returns customers observable list.</p>
     *
     * @return observable list
     */
    public static ObservableList<Customer> getCustomers() {
        return customers;
    }

    /**
     * <p>This method takes customer object as the argument and adds it to the customer list.</p>
     *
     * @param customer customer
     */
    public static void addCustomers(Customer customer) {
        customers.add(customer);
    }

    /**
     * <p>This method clears customers list and after that it resets.</p>
     */
    public static void refreshCustomers() {
        customers.clear();
        CustomerDao.setCustomer();
    }

    /**
     * <p>This method clears appointments list.</p>
     */
    public static void refreshAppointments() {
        appointments.clear();
    }

    /**
     * <p>This method takes customer object as the argument and removes it from the customers list.</p>
     *
     * @param customer customer
     */
    public static void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }

}
