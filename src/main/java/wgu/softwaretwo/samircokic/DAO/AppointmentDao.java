package wgu.softwaretwo.samircokic.DAO;

import wgu.softwaretwo.samircokic.model.Appointment;
import wgu.softwaretwo.samircokic.model.Schedule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class AppointmentDao {


    public static void setTheAppointment(int num) throws SQLException {
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
        String sql = "SELECT * FROM APPOINTMENTS WHERE User_ID = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, num);
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
   public static void contactID() throws SQLException {
        int id  = 0;
        String sql = "SELECT * FROM CONTACTS";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("Contact_ID");
            Appointment.addContactID(id);
        }
    }
    public static void customerID() throws SQLException {
        int id  = 0;
        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("Customer_ID");
            Appointment.addCustomerID(id);
        }
    }
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
    public static void type() throws SQLException {
        String type = "";
        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            type = resultSet.getString("Type");
            Appointment.addType(type);
        }

    }

    public static int addAppointment(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int customerId, int userId, int contact) throws SQLException {
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
        preparedStatement.setInt(9, contact);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }
}
