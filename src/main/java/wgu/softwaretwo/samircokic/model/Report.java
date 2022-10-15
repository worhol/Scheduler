package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Collections;

/**
 * <p>This class is a blueprint for the reports about customer appointments by type and month and by contact. It also provides the methods for reporting the contact's schedule</p>
 *
 * @author Samir Cokic
 */
public class Report {
    /**
     * <p>Observable list that contains all customer's meetings.</p>
     */
    private static final ObservableList<Integer> customersMeetings = FXCollections.observableArrayList();
    /**
     * <p>Observable list that contains all customer's types of the appointment.</p>
     */
    private static final ObservableList<String> customerAppointmentType = FXCollections.observableArrayList();
    /**
     * <p>Observable list that contains all customer's appointment contacts.</p>
     */
    private static final ObservableList<String> customerAppointmentContacts = FXCollections.observableArrayList();

    /**
     * <p>This method takes the string as the argument and then utilize lambda expression to return observable list. It
     * converts the all appointments list from Schedule class into a stream using the stream method.
     * Then it filters the appointments by their contact's name. At the end filtered appointments are added
     * to customerSchedule observable list.</p>
     *
     * @param name the name of the contact.
     * @return observable list with that contains all contact's appointments.
     */
    public static ObservableList<Appointment> contactAppointments(String name) {
        ObservableList<Appointment> contactSchedule = FXCollections.observableArrayList();
        Schedule.getAppointments().stream().filter(appointment -> appointment.getContact().equals(name)).forEachOrdered(contactSchedule::add);
        return contactSchedule;
    }

    /**
     * <p>This method takes the int as the argument and then utilize lambda expression to return observable list. It
     * converts the all appointments list from Schedule class into a stream using the stream method.
     * Then it filters the appointments by their customer id. At the end filtered appointments are added
     * to customerAppointment observable list.</p>
     *
     * @param id the unique customer id.
     * @return observable list with that contains all customer appointments.
     */
    public static ObservableList<Appointment> allCustomersAppointments(int id) {
        ObservableList<Appointment> customerAppointment = FXCollections.observableArrayList();
        Schedule.getAppointments().stream().filter(appointment -> appointment.getCustomerId() == id).forEach(customerAppointment::add);
        return customerAppointment;
    }
    ///////////////////////////////////////////////////////////////////

    /**
     * <p>This method takes the two ints as the argument and then utilize lambda expression to return observable list. It
     * converts the all appointments list from Schedule class into a stream using the stream method.
     * Then it filters the appointments by their customer id and month. At the end filtered type of appointments are added
     * to customerAppointment observable list.</p>
     *
     * @param id    the unique customer id.
     * @param month month of the meeting
     * @return observable list with that contains all customer appointments by type
     */
    public static ObservableList<String> allCustomersMonthlyTypeAppointments(int id, int month) {
        ObservableList<String> customerAppointment = FXCollections.observableArrayList();
        Schedule.getAppointments().stream().filter(appointment -> appointment.getStart().getMonthValue() == month && appointment.getCustomerId() == id).map(Appointment::getType).forEachOrdered(customerAppointment::add);
        return customerAppointment;
    }

    /**
     * <p>This method takes int as the argument and returns the string that contains customer report of
     * their appointments by month and type utilizing Collections.frequency method</p>
     *
     * @param id customer id
     * @return String with the report
     */
    public static String monthlyType(int id) {
        String report = "";
        String jan = "";
        String feb = "";
        String mar = "";
        String apr = "";
        String may = "";
        String jun = "";
        String jul = "";
        String aug = "";
        String sep = "";
        String oct = "";
        String nov = "";
        String dec = "";
        if (allCustomersMonthlyTypeAppointments(id, 1) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 1), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 1), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 1), "De-Briefing");
            jan = "JANUARY:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 2) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 2), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 2), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 2), "De-Briefing");
            feb = "FEBRUARY:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 3) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 3), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 3), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 3), "De-Briefing");
            mar = "MARCH:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 4) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 4), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 4), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 4), "De-Briefing");
            apr = "APRIL:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 5) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 5), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 5), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 5), "De-Briefing");
            may = "MAY:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 6) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 6), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 6), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 6), "De-Briefing");
            jun = "JUNE:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 7) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 7), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 7), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 7), "De-Briefing");
            jul = "JULY:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 8) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 8), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 8), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 8), "De-Briefing");
            aug = "AUGUST:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 9) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 9), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 9), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 9), "De-Briefing");
            sep = "SEPTEMBER:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 10) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 10), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 10), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 10), "De-Briefing");
            oct = "OCTOBER:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 11) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 11), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 11), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 11), "De-Briefing");
            nov = "NOVEMBER:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        if (allCustomersMonthlyTypeAppointments(id, 12) != null) {
            int preliminary = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 12), "Preliminary");
            int general = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 12), "General");
            int debriefing = Collections.frequency(allCustomersMonthlyTypeAppointments(id, 12), "De-Briefing");
            dec = "DECEMBER:    Preliminary " + preliminary + "  General " + general + "  De-Briefing " + debriefing;
        }
        report = jan + "\n\n" + feb + "\n\n" + mar + "\n\n" + apr + "\n\n" + may + "\n\n" + jun + "\n\n" + jul + "\n\n" + aug + "\n\n" + sep + "\n\n" + oct + "\n\n" + nov + "\n\n" + dec;
        return report;
    }



    /**
     * <p>This method takes integer as the argument and returns observable list of customer appointment contacts associated with the argument.
     * It first clears the cache from customerAppointmentContacts observable list.
     * After that it utilize lambda expression to return observable list. It converts the all appointments list from Schedule class into a stream using the stream method.
     * Then it filters the appointments by their customer id. At the end filtered appointments are added
     * to customerAppointmentContacts observable list. </p>
     *
     * @param id customer id
     * @return observable list with customer appointment contacts
     */
    public static ObservableList<String> allCustomersAppointmentContacts(int id) {
        customerAppointmentContacts.clear();
        Schedule.getAppointments().stream().filter(appointment -> appointment.getCustomerId() == id).map(Appointment::getContact).forEach(customerAppointmentContacts::add);
        return customerAppointmentContacts;
    }

    /**
     * <p>This method takes the int as the argument and then return the observable list of PieChart.Data type.
     * It first refresh the customerAppointmentContact list, then it utilize the frequency method which looks
     * for the appointment contact occurrences by their name in allCustomerAppointmentContacts and count them.
     * PieChart class then takes the counted appointment types and creates a new object for each type and returns them in observable list.</p>
     *
     * @param id customer id
     * @return the pie chart data with frequency of appointment contacts.
     */
    public static ObservableList<PieChart.Data> customerContactPieChart(int id) {
        customerAppointmentContacts.clear();
        int costa = Collections.frequency(allCustomersAppointmentContacts(id), "Anika Costa");
        int garcia = Collections.frequency(allCustomersAppointmentContacts(id), "Daniel Garcia");
        int lee = Collections.frequency(allCustomersAppointmentContacts(id), "Li Lee");
        return FXCollections.observableArrayList(
                new PieChart.Data("Anika Costa " + costa, costa),
                new PieChart.Data("Daniel Garcia " + garcia, garcia),
                new PieChart.Data("Li Lee " + lee, lee));
    }

}
