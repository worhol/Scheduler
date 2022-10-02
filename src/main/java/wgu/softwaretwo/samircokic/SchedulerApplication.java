package wgu.softwaretwo.samircokic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wgu.softwaretwo.samircokic.DAO.JDBC;

import java.io.IOException;
import java.sql.SQLException;

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
//        int rowsAffected = AppointmentDao.addAppointment("One","Two","Three","Four",null,null,3,1,2);
//        if (rowsAffected>0){
//            System.out.println("Insertion successful");
//        }else {
//            System.out.println("Insert failed");
//        }
//        int rows = CustomerDao.addCustomer("Y","Y","u","p",9);
//        System.out.println(rows);
//        System.out.println(CustomerDao.getDivisionId("Alaska"));
//        int rows = CustomerDao.updateCustomer(45,"YES","YES","YES","YES", 45);
//        System.out.println(rows);
        launch();
        ;
//        System.out.println(AppointmentDao.setTheAppointment(1).getType());
//        AppointmentDao.setTheAppointment(1);
//        System.out.println(Schedulle.getAppointments());
        JDBC.closeConnection();

    }
}