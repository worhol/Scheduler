package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.Date;

public class Report {
    public static ObservableList<Appointment> contactAppointments(String name) {
        ObservableList<Appointment> contactSchedulle = FXCollections.observableArrayList();
        for (Appointment appointment : Schedule.getAppointments()) {
            if (appointment.getContact().equals(name)) {
                contactSchedulle.add(appointment);
            }
        }
        return contactSchedulle;
    }

    public static ObservableList<Appointment> allCustomersAppointments(int id) {
        ObservableList<Appointment> customerAppointment = FXCollections.observableArrayList();
        for (Appointment appointment : Schedule.getAppointments()) {
            if (appointment.getCustomerId() == id) {
                customerAppointment.add(appointment);
            }
        }
        return customerAppointment;
    }

    private static ObservableList<Integer> customersMeetings = FXCollections.observableArrayList();

    public static ObservableList<PieChart.Data> monthlyPieChart(int id, ZoneId zoneId) {
        customersMeetings.clear();
        for (Appointment appointment : allCustomersAppointments(id)) {
            customersMeetings.add(appointment.getStart().getMonthValue());
        }
        int january = Collections.frequency(customersMeetings, 1);
        int february = Collections.frequency(customersMeetings, 2);
        int march = Collections.frequency(customersMeetings, 3);
        int april = Collections.frequency(customersMeetings, 4);
        int may = Collections.frequency(customersMeetings, 5);
        int june = Collections.frequency(customersMeetings, 6);
        int july = Collections.frequency(customersMeetings, 7);
        int august = Collections.frequency(customersMeetings, 8);
        int september = Collections.frequency(customersMeetings, 9);
        int october = Collections.frequency(customersMeetings, 10);
        int november = Collections.frequency(customersMeetings, 11);
        int december = Collections.frequency(customersMeetings, 12);
        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList(
                new PieChart.Data("January " + january, january),
                new PieChart.Data("February " + february, february),
                new PieChart.Data("March " + march, march),
                new PieChart.Data("April " + april, april),
                new PieChart.Data("May " + may, may),
                new PieChart.Data("June " + june, june),
                new PieChart.Data("July " + july, july),
                new PieChart.Data("August " + august, august),
                new PieChart.Data("September " + september, september),
                new PieChart.Data("October " + october, october),
                new PieChart.Data("November " + november, november),
                new PieChart.Data("December " + december, december));
        return pieChart;
    }

    private static ObservableList<String> customerAppointmentType = FXCollections.observableArrayList();

    public static ObservableList<String> allCustomersAppointmentTypes(int id) {
        customerAppointmentType.clear();
        for (Appointment appointment : Schedule.getAppointments()) {
            if (appointment.getCustomerId() == id) {
                customerAppointmentType.add(appointment.getType());
            }
        }
        return customerAppointmentType;
    }

    public static ObservableList<PieChart.Data> typePieChart(int id) {
        customerAppointmentType.clear();
        int preliminary = Collections.frequency(allCustomersAppointmentTypes(id), "Preliminary");
        int general = Collections.frequency(allCustomersAppointmentTypes(id), "General");
        int debriefing = Collections.frequency(allCustomersAppointmentTypes(id), "De-Briefing");
        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList(
                new PieChart.Data("Preliminary " + preliminary, preliminary),
                new PieChart.Data("General " + general, general),
                new PieChart.Data("De-Briefing " + debriefing, debriefing));
        return pieChart;
    }

    private static ObservableList<String> customerAppointmentContacts = FXCollections.observableArrayList();

    public static ObservableList<String> allCustomersAppointmentContacts(int id) {
        customerAppointmentContacts.clear();
        for (Appointment appointment : Schedule.getAppointments()) {
            if (appointment.getCustomerId() == id) {
                customerAppointmentContacts.add(appointment.getContact());
            }
        }
        return customerAppointmentContacts;
    }

    public static ObservableList<PieChart.Data> customerContactPieChart(int id) {
        customerAppointmentContacts.clear();
        int costa = Collections.frequency(allCustomersAppointmentContacts(id), "Anika Costa");
        int garcia = Collections.frequency(allCustomersAppointmentContacts(id), "Daniel Garcia");
        int lee = Collections.frequency(allCustomersAppointmentContacts(id), "Li Lee");
        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList(
                new PieChart.Data("Anika Costa " + costa, costa),
                new PieChart.Data("Daniel Garcia " + garcia, garcia),
                new PieChart.Data("Li Lee " + lee, lee));
        return pieChart;
    }

}
