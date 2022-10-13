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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

import static wgu.softwaretwo.samircokic.DAO.UserDao.usernameAndPasswordCheck;
import static wgu.softwaretwo.samircokic.DAO.UserDao.usernameCheck;

/**
 * <p>This class is the blueprint for controller of the Login page</p>
 *
 * @author Samir Cokic
 */
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
    /**
     * <p>Zone id takes the zone of user's computer.</p>
     */
    ZoneId zoneId = ZoneId.systemDefault();
    /**
     * <p>Resource bundle uses data from language file to translate the Login page and it's warning messages to French.</p>
     */
    ResourceBundle bundle = ResourceBundle.getBundle("languages/language");


    /**
     * <p>This method initialize necessary objects on login page</p>
     *
     * @param url url
     * @param resourceBundle resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        zone.setText(zoneId.toString());
        loginButton.setText(bundle.getString("BUTTON"));
        username.setPromptText(bundle.getString("USERNAME"));
        password.setPromptText(bundle.getString("PASSWORD"));
    }

    /**
     * <p>This method displays appointment form. It first checks for username and a password. If one or both of those
     * are incorrect it displays the warning message. If the user comes from the French speaking country
     * the warning message is displayed in French. It also calls the login activity method that writes login
     * activity onto a file.</p>
     *
     * @param actionEvent action event
     * @throws IOException provides information on file writing errors.
     * @throws SQLException provides information on database access errors.
     */
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
            stage.show();
        } else if (usernameCheck(user) > 0 && usernameAndPasswordCheck(user, pass) == 0) {
            boolean success = false;
            loginActivity(user,success,zoneId);
            errorPassword.setText(bundle.getString("PASSWORD_ERROR"));
        }
    }

    /**
     * <p>This method takes the string filename as the parameter which contains the path to file were login
     * activity of the user is documented. The method takes the boolean argument and depending of outcome
     * it calls the printwriter method to print the string containing username, whether the login was success or failure and date and time of
     * the login attempt as well as the user's zone.</p>
     *
     * @param user user
     * @param success checks if the login was success
     * @param zoneId the zone id of the user
     * @throws IOException provides information on database access errors.
     */
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
