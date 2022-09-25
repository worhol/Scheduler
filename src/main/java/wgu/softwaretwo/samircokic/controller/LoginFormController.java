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
import wgu.softwaretwo.samircokic.DAO.UserDao;
import wgu.softwaretwo.samircokic.model.Appointment;
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
            AppointmentDao.setTheAppointment(usernameAndPasswordCheck(user,pass));
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/wgu/softwaretwo/samircokic/AppointmentsForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.setMaximized(true);
            stage.show();
        } else if (usernameCheck(user) > 0 && usernameAndPasswordCheck(user, pass) == 0) {
            errorPassword.setText(bundle.getString("PASSWORD_ERROR"));
        }
    }

}
