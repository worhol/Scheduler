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

    /**
     * <p>This method takes the int as the argument and then utilize lambda expression to return the observable list of PieChart.Data type.
     * It first refresh the customerMeetings list, then it utilize lambda expression to convert
     * the all customer appointments list from into a stream using the stream method.
     * Then it maps the appointments by month, utilizing the frequency method which looks for the month occurrences in allCustomerAppointments and count them.
     * PieChart class then takes the counted appointments and creates a new object for each month and adds them to customerMeetings observable list.</p>
     *
     * @param id customer id
     * @return the pie chart data with frequency of the meetings per month.
     */
    public static ObservableList<PieChart.Data> monthlyPieChart(int id) {
        customersMeetings.clear();
        allCustomersAppointments(id).stream().map(appointment -> appointment.getStart().getMonthValue()).forEach(customersMeetings::add);
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
        return FXCollections.observableArrayList(
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
    }

    /**
     * <p>This method takes integer as the argument and returns observable list of customer appointments associated with the argument.
     * It first clears the cash from customerAppointmentType observable list.
     * After that it utilize lambda expression to return observable list. It converts the all appointments list from Schedule class into a stream using the stream method.
     * Then it filters the appointments by their customer id. At the end filtered appointments are added
     * to customerAppointmentType observable list. </p>
     *
     * @param id customer id
     * @return observable list with customer appointment types
     */
    public static ObservableList<String> allCustomersAppointmentTypes(int id) {
        customerAppointmentType.clear();
        Schedule.getAppointments().stream().filter(appointment -> appointment.getCustomerId() == id).map(Appointment::getType).forEach(customerAppointmentType::add);
        return customerAppointmentType;
    }

    /**
     * <p>This method takes the int as the argument and then  return the observable list of PieChart.Data type.
     * It first refresh the customerAppointmentType list, then it utilize the frequency method which looks for the appointment types occurrences in allCustomerAppointmentTypes and count them.
     * PieChart class then takes the counted appointment types and creates a new object for each type and returns them in observable list.</p>
     *
     * @param id customer id
     * @return the pie chart data with frequency of appointment types.
     */
    public static ObservableList<PieChart.Data> typePieChart(int id) {
        customerAppointmentType.clear();
        int preliminary = Collections.frequency(allCustomersAppointmentTypes(id), "Preliminary");
        int general = Collections.frequency(allCustomersAppointmentTypes(id), "General");
        int debriefing = Collections.frequency(allCustomersAppointmentTypes(id), "De-Briefing");
        return FXCollections.observableArrayList(
                new PieChart.Data("Preliminary " + preliminary, preliminary),
                new PieChart.Data("General " + general, general),
                new PieChart.Data("De-Briefing " + debriefing, debriefing));
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
