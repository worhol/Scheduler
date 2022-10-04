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
        String sql = "SELECT DISTINCT TYPE FROM APPOINTMENTS";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            type = resultSet.getString("Type");
            Appointment.addType(type);
        }

    }

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
    public static int deleteAppointment(Appointment appointment) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID =?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, appointment.getAppointmentId());
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }
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
