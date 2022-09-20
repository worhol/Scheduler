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
import wgu.softwaretwo.samircokic.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;

import static wgu.softwaretwo.samircokic.utility.UserDao.passwordCheck;

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
//        System.out.println(bundle.getString("TEST"));
        loginButton.setText(bundle.getString("TEST"));
    }

    @FXML
    public void displayAppointmentsForm(ActionEvent actionEvent) throws IOException, SQLException {
        String pass = password.getText();
        String user = username.getText();
        if (passwordCheck(user,pass)>0){
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/wgu/softwaretwo/samircokic/AppointmentsForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }else {
            System.out.println("No such user");
        }

    }
}