package wgu.softwaretwo.samircokic.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wgu.softwaretwo.samircokic.controller.LoginFormController;
import wgu.softwaretwo.samircokic.model.Appointment;
import wgu.softwaretwo.samircokic.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class AppointmentDao {

    public static ObservableList<User> users = FXCollections.observableArrayList();

    public static void addUser(User user) {
        users.add(user);
    }

    public static ObservableList<User> getUsers() {
        return users;
    }

    //    int id = users.get(0).getId();
    public static Appointment setTheAppointment(int num) throws SQLException {
        int appointmentId = 0;
        String title = "";
        String description = "";
        String location = "";
        String contact = "";
        String type = "";
        LocalDateTime start;
        LocalDateTime end ;
        int customerId = 0;
        int userId = 0;
        String sql = "SELECT * FROM APPOINTMENTS WHERE User_ID = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, num);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            appointmentId = resultSet.getInt("Appointment_ID");
            title = resultSet.getString("Title");
            description = resultSet.getString("Description");
            location = resultSet.getString("Location");
            contact = resultSet.getString("Location");
            type = resultSet.getString("Type");
//            start = resultSet.getTime("Start");
//            end = resultSet.getString("End");
            customerId = resultSet.getInt("Customer_ID");
            userId = resultSet.getInt("User_ID");
        }
        Appointment appointment = new Appointment(appointmentId,title,description,location,contact,type,null,null,customerId,userId);
        return appointment;
    }


//    public static String name() throws SQLException {
//        LoginFormController controller = new LoginFormController();
//        User user = controller.sendUser();
//        String title = "";
//        int userId = user.getId();
//        String sql = "SELECT * FROM APPOINTMENTS WHERE User_ID = ?";
//        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
//        preparedStatement.setInt(1, userId);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            title = resultSet.getString("TITLE");
//        }
//        System.out.println(title);
//        return title;
//    }


}
