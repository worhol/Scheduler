package wgu.softwaretwo.samircokic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wgu.softwaretwo.samircokic.DAO.AppointmentDao;
import wgu.softwaretwo.samircokic.DAO.JDBC;
import wgu.softwaretwo.samircokic.DAO.UserDao;
import wgu.softwaretwo.samircokic.controller.LoginFormController;
import wgu.softwaretwo.samircokic.model.Schedulle;
import wgu.softwaretwo.samircokic.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class SchedulerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SchedulerApplication.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
//        Locale.setDefault(new Locale("fr"));;
        launch();
//        System.out.println(AppointmentDao.setTheAppointment(1).getType());
//        AppointmentDao.setTheAppointment(1);
//        System.out.println(Schedulle.getAppointments());
        JDBC.closeConnection();

    }
}