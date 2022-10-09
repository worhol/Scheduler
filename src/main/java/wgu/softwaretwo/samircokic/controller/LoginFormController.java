package wgu.softwaretwo.samircokic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wgu.softwaretwo.samircokic.DAO.AppointmentDao;
import wgu.softwaretwo.samircokic.model.Schedule;
import wgu.softwaretwo.samircokic.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.security.Timestamp;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

import static wgu.softwaretwo.samircokic.DAO.UserDao.usernameAndPasswordCheck;
import static wgu.softwaretwo.samircokic.DAO.UserDao.usernameCheck;

public class LoginFormController implements Initializable {

    @FXML
    private PasswordField password;
    @FXML
    private Label errorUsername;
    @FXML
    private Label zone;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorPassword;
    @FXML
    private TextField username;
    Stage stage;
    Parent scene;
    ZoneId zoneId = ZoneId.systemDefault();
    ResourceBundle bundle = ResourceBundle.getBundle("languages/language");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zone.setText(zoneId.toString());
        loginButton.setText(bundle.getString("BUTTON"));
        username.setPromptText(bundle.getString("USERNAME"));
        password.setPromptText(bundle.getString("PASSWORD"));
    }

    @FXML
    public void displayAppointmentsForm(ActionEvent actionEvent) throws IOException, SQLException {
        errorPassword.setText("");
        errorUsername.setText("");
        String pass = password.getText();
        String user = username.getText();
        if (usernameCheck(user) == 0) {
            boolean success = false;
            errorUsername.setText(bundle.getString("USERNAME_ERROR"));
            loginActivity(user,success,zoneId);
        }
        if (usernameAndPasswordCheck(user, pass) > 0) {
            boolean success = true;
            loginActivity(user,success,zoneId);
            Schedule.addUser(new User(usernameAndPasswordCheck(user,pass),user,pass));
//            AppointmentDao.setTheAppointment(usernameAndPasswordCheck(user,pass));
            AppointmentDao.setTheAppointment();
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/wgu/softwaretwo/samircokic/AppointmentsForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.setMaximized(true);
//            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//
////set Stage boundaries to the lower right corner of the visible bounds of the main screen
//            stage.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() - 1280);
//            stage.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 700);
//            stage.setWidth(1280);
//            stage.setHeight(700);
//
//            stage.show();
//            stage.setMaximized(true);

//            stage.setFullScreen(true);
//            stage.getMinHeight();
//            stage.getMinWidth();
            stage.show();
        } else if (usernameCheck(user) > 0 && usernameAndPasswordCheck(user, pass) == 0) {
            boolean success = false;
            loginActivity(user,success,zoneId);
            errorPassword.setText(bundle.getString("PASSWORD_ERROR"));
        }
    }

    public static void loginActivity(String user, boolean success, ZoneId zoneId) throws IOException {
        String filename = "src/main/java/wgu/softwaretwo/samircokic/login_activity.txt";
        FileWriter fileWriter = new FileWriter(filename,true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        if (success){
            printWriter.println("User "+user+" attempted login SUCCESS on "+ LocalDateTime.now(zoneId)+" "+zoneId+" time.");
        }else {
            user="unknown";
            printWriter.println("User "+user+" attempted login FAILED on "+ LocalDateTime.now(zoneId)+" "+zoneId+" time.");
        }
        printWriter.close();
    }

}
