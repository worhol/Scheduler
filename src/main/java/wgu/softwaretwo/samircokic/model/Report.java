package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Collections;

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

    private static ObservableList<String> customerAppointmentType = FXCollections.observableArrayList();

    public static ObservableList<String> allCustomersAppointmentTypes(int id) {
        for (Appointment appointment : Schedule.getAppointments()) {
            if (appointment.getCustomerId() == id) {
                customerAppointmentType.add(appointment.getType());
            }
        }
        return customerAppointmentType;
    }
    public static ObservableList<PieChart.Data> typePieChart(int id){
        customerAppointmentType.clear();
        int preliminary = Collections.frequency(allCustomersAppointmentTypes(id),"Preliminary");
        int general = Collections.frequency(allCustomersAppointmentTypes(id),"General");
        int debriefing = Collections.frequency(allCustomersAppointmentTypes(id),"De-Briefing");
        ObservableList<PieChart.Data> pieChart = FXCollections.observableArrayList(
                new PieChart.Data("Preliminary "+preliminary,preliminary),
                new PieChart.Data("General " +general,general),
                new PieChart.Data("De-Briefing "+ debriefing, debriefing));
        return pieChart;
    }

}
