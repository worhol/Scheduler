package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Report {
    public static ObservableList<Appointment> contactAppointments(String name){
        ObservableList<Appointment> contactSchedulle = FXCollections.observableArrayList();
        for (Appointment appointment: Schedule.getAppointments()){
            if (appointment.getContact().equals(name)){
                contactSchedulle.add(appointment);
            }
        }
        return contactSchedulle;
    }
}
