package wgu.softwaretwo.samircokic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import wgu.softwaretwo.samircokic.DAO.AppointmentDao;
import wgu.softwaretwo.samircokic.DAO.UserDao;
import wgu.softwaretwo.samircokic.model.Appointment;
import wgu.softwaretwo.samircokic.model.Schedule;
import wgu.softwaretwo.samircokic.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
            errorUsername.setText(bundle.getString("USERNAME_ERROR"));
        }
        if (usernameAndPasswordCheck(user, pass) > 0) {
            //do i have appointemnt in next 15 mins...
            Schedule.addUser(new User(usernameAndPasswordCheck(user,pass),user,pass));
            AppointmentDao.setTheAppointment(usernameAndPasswordCheck(user,pass));
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
            errorPassword.setText(bundle.getString("PASSWORD_ERROR"));
        }
    }

}
