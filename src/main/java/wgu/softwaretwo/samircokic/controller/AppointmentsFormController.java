package wgu.softwaretwo.samircokic.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import wgu.softwaretwo.samircokic.DAO.CustomerDao;
import wgu.softwaretwo.samircokic.model.Country;
import wgu.softwaretwo.samircokic.model.Customer;
import wgu.softwaretwo.samircokic.model.Division;
import wgu.softwaretwo.samircokic.model.Schedulle;

import java.net.URL;
import java.sql.SQLException;
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
    private TableColumn locationColumn;
    @FXML
    private TableView appointmentsTable;
    @FXML
    private Label timeZone;

    Stage stage;
    Parent scene;


    ZoneId zoneId = ZoneId.systemDefault();
    @FXML
    private TableColumn endDateColumn1;
    @FXML
    private TableColumn endDateColumn2;
    @FXML
    private TableColumn appointmentIdColumn2;
    @FXML
    private TableColumn appointmentIdColumn1;
    @FXML
    private TextField addTitleTxt;
    @FXML
    private TableView appointmentsTable1;
    @FXML
    private TableColumn customerIdColumn1;
    @FXML
    private TableColumn startDateColumn2;
    @FXML
    private TableView appointmentsTable2;
    @FXML
    private TableColumn customerIdColumn2;
    @FXML
    private TableColumn descriptionColumn2;
    @FXML
    private TableColumn userIdColumn2;
    @FXML
    private TableColumn locationColumn1;
    @FXML
    private TableColumn userIdColumn1;
    @FXML
    private TableColumn startDateColumn1;
    @FXML
    private TableColumn locationColumn2;
    @FXML
    private TableColumn descriptionColumn1;
    @FXML
    private TextField addUserId;
    @FXML
    private TextField addCustomerId;
    @FXML
    private TableColumn contactColumn1;
    @FXML
    private TextField addDescriptionTxt;
    @FXML
    private TextField addLocationTxt;
    @FXML
    private TableColumn contactColumn2;
    @FXML
    private TableColumn typeColumn2;
    @FXML
    private TableColumn typeColumn1;
    @FXML
    private DatePicker addStartDateAndTimePicker;
    @FXML
    private DatePicker addEndDateAndTimePicker;
    @FXML
    private TableColumn titleColumn2;
    @FXML
    private TableColumn titleColumn1;
    @FXML
    private ComboBox addTypeDropBox;
    @FXML
    private ComboBox addContactDropBox;
    @FXML
    private TextField addCustomerAddress;
    @FXML
    private TextField addPhoneNumber;
    @FXML
    private TextField addCustomerName;
    @FXML
    private ComboBox addCustomerCountry;
    @FXML
    private TextField addCustomerPostalCode;
    @FXML
    private TextField customerId;
    @FXML
    private ComboBox addCustomerProvince;
    @FXML
    private TableView customerTable;

    public static ObservableList<Country> countries;
    public static ObservableList<Division> divisions;

    static {
        try {
            divisions = Customer.getDivisions();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            countries = Customer.getCountries();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeZone.setText(zoneId.toString());
        appointmentsTable.setItems(Schedulle.getAppointments());
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        addCustomerCountry.setItems(countries);

    }

    @FXML
    public void addAppointment(ActionEvent actionEvent) {

    }

    @FXML
    public void addCustomer(ActionEvent actionEvent) {
    }

    @FXML
    public void chooseCustomerDivision(ActionEvent actionEvent) throws SQLException {
        int c = addCustomerCountry.getSelectionModel().getSelectedIndex();
        addCustomerProvince.getItems().clear();
        CustomerDao.divisions(c + 1);
        addCustomerProvince.setItems(divisions);
        addCustomerProvince.setVisibleRowCount(5);

    }
}
