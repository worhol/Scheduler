package wgu.softwaretwo.samircokic.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AppointmentsFormController implements Initializable {

    @FXML
    private TableColumn contactColumn;
    @FXML
    private TableColumn endDateColumn;
    @FXML
    private TableColumn appointmentIdColumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn userIdColumn;
    @FXML
    private TableColumn startDateColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn customerIdColumn;
    @FXML
    private TableColumn descriptionColumn;
    @FXML
    private TableView appointmentsTable;
    @FXML
    private Label timeZone;

    Stage stage;
    Parent scene;


    ZoneId zoneId = ZoneId.systemDefault();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeZone.setText(zoneId.toString());
    }
}
