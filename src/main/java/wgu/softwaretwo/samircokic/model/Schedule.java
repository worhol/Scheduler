package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wgu.softwaretwo.samircokic.DAO.CustomerDao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

public class Schedule {

    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public static void updateAppointment(int index, Appointment appointment) {
        appointments.set(index, appointment);
    }

    public static void deleteAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    public static boolean appointmentOverlap(LocalDateTime start, LocalDateTime end, int customerID) {
        boolean isItOverlap = false;
        for (Appointment appointment : appointments) {
            if (appointment.getCustomerId() == customerID) {
                if ((start.isAfter(appointment.getStart()) && start.isBefore(appointment.getEnd())) || (start.isEqual(appointment.getStart()))
                        || (end.isAfter(appointment.getStart()) && end.isBefore(appointment.getEnd()))
                        ||(start.isBefore(appointment.getStart())&&end.isAfter(appointment.getEnd()))) {
                    isItOverlap = true;
                    System.out.println("Overlap");
                }
            }

        }
        return isItOverlap;
    }

    public static void deleteCustomersAppointments(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == id) {
                appointments.remove(appointment);
            }
        }
        for (Appointment appointment : weeklyAppointments) {
            if (appointment.getAppointmentId() == id) {
                weeklyAppointments.remove(appointment);
            }
        }
        for (Appointment appointment : monthlyAppointments) {
            if (appointment.getAppointmentId() == id) {
                monthlyAppointments.remove(appointment);
            }
        }

    }

    public static ObservableList<Appointment> getAppointments() {
        return appointments;
    }

    public static String appointmentAlert(ZoneId zoneId) {
        LocalDateTime currentTime = LocalDateTime.now(zoneId);
        String meetingAlert = "";
        for (Appointment appointment : appointments) {
            long timeDifference = ChronoUnit.MINUTES.between(currentTime, appointment.getStart());
            if (timeDifference < 15 && timeDifference > 0) {
                meetingAlert = "You have upcoming appointment with the id: " + appointment.getAppointmentId() + " at: " + appointment.getStart();
            } else {
                meetingAlert = "There are no upcoming appointments";
            }
        }
        return meetingAlert;
    }

    public static ObservableList<Appointment> weeklyAppointments = FXCollections.observableArrayList();

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

    public static void deleteWeeklyAppointment(Appointment appointment) {
        Iterator<Appointment> appointmentIterator = weeklyAppointments.iterator();
        while (appointmentIterator.hasNext()) {
            Appointment a = appointmentIterator.next();
            if (a.equals(appointment)) {
                appointmentIterator.remove();
            }
        }
    }

    public static void deleteMonthlyAppointment(Appointment appointment) {
        Iterator<Appointment> appointmentIterator = monthlyAppointments.iterator();
        while (appointmentIterator.hasNext()) {
            Appointment a = appointmentIterator.next();
            if (a.equals(appointment)) {
                appointmentIterator.remove();
            }
        }
    }

    ///////////////////////////////////////////////////////////////////
    //Monthly appointments
    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    public static ObservableList<Appointment> monthlyAppointments = FXCollections.observableArrayList();

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


    public static ObservableList<User> users = FXCollections.observableArrayList();

    public static int getUserID() {
        return users.get(0).getId();
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static ObservableList<Customer> customers = FXCollections.observableArrayList();

    public static ObservableList<Customer> getCustomers() {
        return customers;
    }

    public static void addCustomers(Customer customer) {
        customers.add(customer);
    }

    public static void refreshCustomers() {
        customers.clear();
        CustomerDao.setCustomer();
    }

    public static void refreshAppointments() {
        appointments.clear();
    }

    public static void deleteCustomer(Customer customer) {
        customers.remove(customer);
    }

}
