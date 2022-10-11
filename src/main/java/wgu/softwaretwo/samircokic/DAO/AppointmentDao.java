package wgu.softwaretwo.samircokic.DAO;

import wgu.softwaretwo.samircokic.model.Appointment;
import wgu.softwaretwo.samircokic.model.Schedule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * <p>This class is handling the sql queries for the appointments class</p>
 *
 * @author Samir Cokic
 */
public abstract class AppointmentDao {


    /**
     * <p>This method queries the data from the database and creates the Appointment object and then adds the object to observable list which is used to populate the appointments table</p>
     *
     * @throws SQLException provides information on database access errors.
     */
public static void setTheAppointment() throws SQLException {
    int appointmentId = 0;
    String title = "";
    String description = "";
    String location = "";
    String contact = "";
    String type = "";
    LocalDateTime start = null;
    LocalDateTime end = null;
    int customerId = 0;
    int userId = 0;
    int contactId = 0;
    String sql = "SELECT * FROM APPOINTMENTS";
    PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
        contactId = resultSet.getInt("Contact_ID");
        appointmentId = resultSet.getInt("Appointment_ID");
        title = resultSet.getString("Title");
        description = resultSet.getString("Description");
        location = resultSet.getString("Location");
        contact = contact(contactId);
        type = resultSet.getString("Type");
        start = resultSet.getTimestamp("Start").toLocalDateTime();
        end = resultSet.getTimestamp("End").toLocalDateTime();
        customerId = resultSet.getInt("Customer_ID");
        userId = resultSet.getInt("User_ID");
        Appointment appointment = new Appointment(appointmentId, title, description, location, contact, type, start, end, customerId, userId);
        Schedule.addAppointment(appointment);
    }
}

    /**
     * <p>This method takes the int argument for contact id and queries the contacts table in database and returns a contact's name.</p>
     *
     * @param num is a unique contact's id
     * @return the String with a contact name.
     * @throws SQLException provides information on database access errors.
     */
    public static String contact(int num) throws SQLException {
        String contact = "";
        String sql = "SELECT * FROM CONTACTS WHERE Contact_ID = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, num);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            contact = resultSet.getString("Contact_Name");
        }
        return contact;
    }

    /**
     * <p>This method takes the String argument for contact name and queries the contacts table in database and returns a contact's unique id.</p>
     *
     * @param name the String with a contact name.
     * @return a unique contact's id.
     * @throws SQLException provides information on database access errors.
     */
    public static int contact(String name) throws SQLException {
        int contact=0;
        String sql = "SELECT * FROM CONTACTS WHERE Contact_Name = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            contact = resultSet.getInt("Contact_ID");
        }
        return contact;
    }

    /**
     * <p>This method takes no arguments and queries the database table contacts for a contact name</p>
     *
     * @throws SQLException provides information on database access errors.
     */
    public static void contactName() throws SQLException {
        String name = "";
        String sql = "SELECT * FROM CONTACTS";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            name = resultSet.getString("Contact_Name");
            Appointment.addContactName(name);
        }
    }

    /**
     * <p>This method takes no arguments and queries the database table customers for a customer's id and adds it to a customer id observable list.</p>
     *
     * @throws SQLException provides information on database access errors.
     */
    public static void customerID() throws SQLException {
        Appointment.getCustomerIDs().clear();
        int id  = 0;
        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("Customer_ID");
            Appointment.addCustomerID(id);
        }
    }

    /**
     * <p>This method takes no arguments and queries the database table users for a user id and adds it to the observable list</p>
     *
     * @throws SQLException provides information on database access errors.
     */
    public static void userID() throws SQLException {
        int id  = 0;
        String sql = "SELECT * FROM USERS";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("User_ID");
            Appointment.addUserID(id);
        }
    }

    /**
     * <p>This method inserts the data from the arguments into appointments table. It returns int > 0 if the insertion was success, else it returns 0.</p>
     *
     * @param title the title of the appointment.
     * @param description the general description of the appointment.
     * @param location the location description of the appointment.
     * @param type type of the appointment.
     * @param start scheduled start time of the appointment.
     * @param end scheduled end time of the appointment.
     * @param customerId unique id of the customer.
     * @param userId unique user id.
     * @param contact the name of the contact.
     * @return if the appointment is successfully added it return the number of the rows added, else it returns zero.
     * @throws SQLException provides information on database access errors.
     */
    public static int addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, String contact) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID ) VALUES(?, ?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5, Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setInt(7, customerId);
        preparedStatement.setInt(8, userId);
        preparedStatement.setInt(9, contact(contact));
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }

    /**
     * <p>This method takes the Appointment object as the argument and queries object's appointment id number and then delete the object based on its id number.</p>
     *
     * @param appointment The appointment object
     * @return if the appointment is successfully deleted it return the number of the rows deleted, else it returns zero.
     * @throws SQLException provides information on database access errors.
     */
    public static int deleteAppointment(Appointment appointment) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID =?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, appointment.getAppointmentId());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }

    /**
     * <p>This method takes the customer's id number as the argument and deletes from database table appointments all the appointments of customer with id number</p>
     *
     * @param id customer's unique id number
     * @return if the appointment is successfully deleted it return the number of the rows deleted, else it returns zero.
     * @throws SQLException provides information on database access errors.
     */
    public static int deleteAllCustomerAppointments(int id) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Customer_ID =?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }

    /**
     * <p>This method updates the data from the arguments into appointments table. It returns int > 0 if the update was success, else it returns 0.</p>
     *
     * @param title the title of the appointment.
     * @param description the general description of the appointment.
     * @param location the location description of the appointment.
     * @param type type of the appointment.
     * @param start start time of the appointment.
     * @param end end time of the appointment.
     * @param customerId unique id of the customer.
     * @param userId unique user id.
     * @param contact contact the name of the contact.
     * @param appointmentId the assigned appointment id number which cannot be changed.
     * @return if the appointment is successfully updated it returns the number of the rows updated, else it returns zero.
     * @throws SQLException provides information on database access errors.
     */
    public static int updateAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, String contact, int appointmentId) throws SQLException {
        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End =?, Customer_ID = ?, User_ID = ?, Contact_ID =? WHERE Appointment_ID = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, description);
        preparedStatement.setString(3, location);
        preparedStatement.setString(4, type);
        preparedStatement.setTimestamp(5,Timestamp.valueOf(start));
        preparedStatement.setTimestamp(6, Timestamp.valueOf(end));
        preparedStatement.setInt(7, customerId);
        preparedStatement.setInt(8,userId);
        preparedStatement.setInt(9,contact(contact));
        preparedStatement.setInt(10, appointmentId);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }
}
