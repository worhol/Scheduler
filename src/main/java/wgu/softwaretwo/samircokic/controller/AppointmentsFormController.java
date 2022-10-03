package wgu.softwaretwo.samircokic.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import wgu.softwaretwo.samircokic.DAO.AppointmentDao;
import wgu.softwaretwo.samircokic.DAO.CustomerDao;
import wgu.softwaretwo.samircokic.model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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
    private TextField addAppointmentID;
    @FXML
    private TextField addTitleTxt;
    @FXML
    private ComboBox addAppointmentStart;
    @FXML
    private ComboBox addAppointmentEnd;
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
    private TableColumn contactColumn1;
    @FXML
    private TextField addDescriptionTxt;
    @FXML
    private TextField addLocationTxt;
    @FXML
    private DatePicker addDate;
    @FXML
    private TableColumn contactColumn2;
    @FXML
    private TableColumn typeColumn2;
    @FXML
    private TableColumn typeColumn1;
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

    @FXML
    private TableColumn customerNameCol;
    @FXML
    private TableColumn customerPhoneCol;
    @FXML
    private TableColumn customerProvinceCol;
    @FXML
    private TableColumn customerPostalCodeCol;
    @FXML
    private TableColumn customerIdCol;
    @FXML
    private TableColumn customerAdressCol;
    @FXML
    private TableColumn customerCountry;
    @FXML
    private Label deleteCustomerLabel;
    @FXML
    private TitledPane addCostumerTitlePane;
    @FXML
    private TitledPane deleteCostumerTitlePane;
    @FXML
    private TextField updateCustomerAddress;
    @FXML
    private TextField updateCustomerID;
    @FXML
    private ComboBox<Division> updateCustomerProvince;
    @FXML
    private TextField updatePhoneNumber;
    @FXML
    private TextField updateCustomerName;
    @FXML
    private TitledPane updateCostumerTitlePane;
    @FXML
    private TextField updateCustomerPostalCode;
    @FXML
    private ComboBox<Country> updateCustomerCountry;
    @FXML
    private ComboBox addCustomerID;
    @FXML
    private ComboBox addUserID;
    @FXML
    private TitledPane deleteAppointmentTitlePane;
    @FXML
    private TitledPane addAppointmentTitlePane;
    @FXML
    private TitledPane updateAppointmentTitlePane;
    @FXML
    private ComboBox updateContactCombo;
    @FXML
    private TextField updateTitleTxt;
    @FXML
    private TextField updateDescriptionTxt;
    @FXML
    private ComboBox updateEndTimeCombo;
    @FXML
    private TextField updateAppointmentID;
    @FXML
    private ComboBox updateUserIdCombo;
    @FXML
    private ComboBox updateStartTimeCombo;
    @FXML
    private ComboBox updateTypeCombo;
    @FXML
    private ComboBox updateCustomerIdCombo;
    @FXML
    private DatePicker updateDatePicker;
    @FXML
    private TextField updateLocationTxt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeZone.setText(zoneId.toString());
        appointmentsTable.setItems(Schedule.getAppointments());
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
        updateCustomerCountry.setItems(countries);

        CustomerDao.setCustomer();

        customerTable.setItems(Schedule.getCustomers());
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAdressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        customerProvinceCol.setCellValueFactory(new PropertyValueFactory<>("division"));

        LocalTime timeIn = LocalTime.of(5, 0);
        LocalTime timeOut = LocalTime.of(22, 0);
        while (timeIn.isBefore(timeOut.plusSeconds(1))) {
            addAppointmentStart.getItems().add(timeIn);
            addAppointmentEnd.getItems().add(timeIn);
            updateStartTimeCombo.getItems().add(timeIn);
            updateEndTimeCombo.getItems().add(timeIn);
            timeIn = timeIn.plusMinutes(15);
        }
        try {
            AppointmentDao.contactID();
            AppointmentDao.type();
            AppointmentDao.customerID();
            AppointmentDao.userID();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addContactDropBox.setItems(Appointment.getContactIDs());
        addTypeDropBox.setItems(Appointment.getTypeOfAppointment());
        addCustomerID.setItems(Appointment.getCustomerIDs());
        addUserID.setItems(Appointment.getUserIDs());
        updateContactCombo.setItems(Appointment.getContactIDs());
        updateTypeCombo.setItems(Appointment.getTypeOfAppointment());
        updateCustomerIdCombo.setItems(Appointment.getCustomerIDs());
        updateUserIdCombo.setItems(Appointment.getUserIDs());


    }

    @FXML
    public void addAppointment(ActionEvent actionEvent) throws SQLException {
//        int appointmentID = Integer.valueOf(addAppointmentID.getText());
        String title = addTitleTxt.getText();
        String descritpion = addDescriptionTxt.getText();
        String location = addLocationTxt.getText();
        int contact = Integer.valueOf(addContactDropBox.getSelectionModel().getSelectedItem().toString());
        String type = addTypeDropBox.getSelectionModel().getSelectedItem().toString();
        LocalTime start = (LocalTime) addAppointmentStart.getValue();
        LocalTime end = (LocalTime) addAppointmentEnd.getValue();
        LocalDate date = addDate.getValue();
        LocalDateTime appointmentStart = LocalDateTime.of(date, start);
        LocalDateTime appointmentEnd = LocalDateTime.of(date, end);
        int customerID = Integer.valueOf(addCustomerID.getSelectionModel().getSelectedItem().toString());
        int userID = Integer.valueOf(addUserID.getSelectionModel().getSelectedItem().toString());

//        Appointment appointment = new Appointment(appointmentID,title,descritpion,location,contact,type,appointmentStart,appointmentEnd,customerID,userID);
        AppointmentDao.addAppointment(title, descritpion, location, type, appointmentStart, appointmentEnd, customerID, userID, contact);
        Schedule.getAppointments().clear();
        AppointmentDao.setTheAppointment(userID);
        appointmentsTable.setItems(Schedule.getAppointments());

        addTitleTxt.clear();
        addDescriptionTxt.clear();
        addLocationTxt.clear();
        addContactDropBox.getSelectionModel().clearSelection();
        addTypeDropBox.getSelectionModel().clearSelection();
        addDate.getEditor().clear();
        addDate.setValue(null);
        addAppointmentStart.getSelectionModel().clearSelection();
        addAppointmentEnd.getSelectionModel().clearSelection();
        addCustomerID.getSelectionModel().clearSelection();
        addUserID.getSelectionModel().clearSelection();
        addAppointmentTitlePane.setExpanded(false);
    }

    @FXML
    public void deleteAppointment(ActionEvent actionEvent) throws SQLException {
        Appointment appointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        AppointmentDao.deleteAppointment(appointment);
        Schedule.deleteAppointment(appointment);
        appointmentsTable.setItems(Schedule.getAppointments());
        //needs to add the alert
        deleteAppointmentTitlePane.setExpanded(false);

    }


    @FXML
    public void addCustomer(ActionEvent actionEvent) throws SQLException, IOException {
        String name = addCustomerName.getText();
        String address = addCustomerAddress.getText();
        String postalCode = addCustomerPostalCode.getText();
        String phone = addPhoneNumber.getText();
        String country = addCustomerCountry.getSelectionModel().getSelectedItem().toString();
        String province = addCustomerProvince.getSelectionModel().getSelectedItem().toString();
        int divisionID = CustomerDao.getDivisionId(province);
        CustomerDao.addCustomer(name, address, postalCode, phone, divisionID);
        Schedule.refreshCustomers();

        customerTable.setItems(Schedule.getCustomers());
        addCustomerName.clear();
        addCustomerAddress.clear();
        addCustomerPostalCode.clear();
        addPhoneNumber.clear();
        addCustomerCountry.getSelectionModel().clearSelection();
        addCustomerProvince.getSelectionModel().clearSelection();
        addCostumerTitlePane.setExpanded(false);
    }

    @FXML
    public void chooseCustomerDivision(ActionEvent actionEvent) throws SQLException {
        int c = addCustomerCountry.getSelectionModel().getSelectedIndex();
        addCustomerProvince.getItems().clear();
        CustomerDao.divisions(c + 1);
        addCustomerProvince.setItems(divisions);
        addCustomerProvince.setVisibleRowCount(5);
    }

    @FXML
    public void deleteCustomer(ActionEvent actionEvent) throws SQLException, InterruptedException {
        int index = customerTable.getSelectionModel().getSelectedIndex();
        int id = 0;
        Customer customer = null;
        for (int i = 0; i < Schedule.getCustomers().size(); i++) {
            if (i == index) {
                id = Schedule.getCustomers().get(i).getCustomerId();
                customer = Schedule.getCustomers().get(i);
            }
        }
        if (CustomerDao.deleteCustomer(id) > 0) {
            deleteCustomerLabel.setText("Customer Deleted");
            Timeline timeline = new Timeline();
            List<KeyValue> values = new ArrayList<>();
            values.add(new KeyValue(deleteCustomerLabel.textProperty(), "Customer Deleted"));
            values.add(new KeyValue(deleteCustomerLabel.textProperty(), "Please select the customer you want to delete and press DELETE button."));
            timeline.getKeyFrames().add(new KeyFrame(new Duration(1000), values.toArray(new KeyValue[values.size()])));
            timeline.play();
            deleteCostumerTitlePane.setExpanded(false);
        }

        Schedule.deleteCustomer(customer);
        customerTable.setItems(Schedule.getCustomers());


    }

    @FXML
    public void updateCustomer(ActionEvent actionEvent) throws SQLException {
        Customer customer = (Customer) customerTable.getSelectionModel().getSelectedItem();
        String name = updateCustomerName.getText();
        String address = updateCustomerAddress.getText();
        String postalCode = updateCustomerPostalCode.getText();
        String phone = updatePhoneNumber.getText();
//        String country = updateCustomerCountry.getSelectionModel().getSelectedItem().getCountryName();
        int divisionId = updateCustomerProvince.getSelectionModel().getSelectedItem().getDivisionId();
        int customerID = Integer.valueOf(updateCustomerID.getText());
        if (CustomerDao.updateCustomer(customerID, name, address, postalCode, phone, divisionId) > 0) {
            Schedule.refreshCustomers();

            updateCostumerTitlePane.setExpanded(false);
        }
        updateCustomerName.clear();
        updateCustomerAddress.clear();
        updateCustomerPostalCode.clear();
        updatePhoneNumber.clear();
        updateCustomerProvince.getSelectionModel().clearSelection();
        updateCustomerCountry.getSelectionModel().clearSelection();//this would throw null pointer
        updateCustomerID.clear();
    }

    @FXML
    public void getCustomer(ActionEvent actionEvent) throws SQLException {
        Customer customer = (Customer) customerTable.getSelectionModel().getSelectedItem();

        updateCustomerID.setText(String.valueOf(customer.getCustomerId()));
        updateCustomerName.setText(customer.getCustomerName());
        updateCustomerAddress.setText(customer.getAddress());
        updatePhoneNumber.setText(customer.getPhoneNumber());
        updateCustomerPostalCode.setText(customer.getPostalCode());

        for (Country country : updateCustomerCountry.getItems()) {
            if (country.getCountryId() == customer.getCountryID()) {
                updateCustomerCountry.setValue(country);
                break;
            }
        }
        for (Division division : updateCustomerProvince.getItems()) {
            if (division.getDivisionId() == customer.getDivisionID()) {
                updateCustomerProvince.setValue(division);
            }
        }

        CustomerDao.divisions(customer.getCountryID());
        updateCustomerProvince.setItems(divisions);

    }

    @FXML
    public void chooseCustomerDivisionUpdate(ActionEvent actionEvent) throws SQLException {
        Country c = updateCustomerCountry.getValue();
        if (c == null) {
            return;
        }// there was a nullpointer bug
        updateCustomerProvince.getItems().clear();
        CustomerDao.divisions(c.getCountryId());
        updateCustomerProvince.setItems(divisions);
        updateCustomerProvince.setVisibleRowCount(5);

    }

    @FXML
    public void updateAppointment(ActionEvent actionEvent) throws SQLException {
        int appointmentId = Integer.valueOf(updateAppointmentID.getText());
        String title = updateTitleTxt.getText();
        String description = updateDescriptionTxt.getText();
        String location = updateLocationTxt.getText();
        String type = updateTypeCombo.getSelectionModel().getSelectedItem().toString();
        LocalTime start = (LocalTime) updateStartTimeCombo.getValue();
        LocalTime end = (LocalTime) updateEndTimeCombo.getValue();
        LocalDate date = updateDatePicker.getValue();
        LocalDateTime startAppointment = LocalDateTime.of(date, start);
        LocalDateTime endAppointment = LocalDateTime.of(date, end);
        int customerID = Integer.valueOf(updateCustomerIdCombo.getSelectionModel().getSelectedItem().toString());
        int userID = Integer.valueOf(updateUserIdCombo.getSelectionModel().getSelectedItem().toString());
        int contactID = Integer.valueOf(updateContactCombo.getSelectionModel().getSelectedItem().toString());// when not changed to number exception
        AppointmentDao.updateAppointment(title,description,location,type,startAppointment,endAppointment,customerID,userID,contactID,appointmentId);
        Schedule.refreshAppointments();
        AppointmentDao.setTheAppointment(userID);
        appointmentsTable.setItems(Schedule.getAppointments());
        updateAppointmentTitlePane.setExpanded(false);
    }

    @FXML
    public void selectAppointment(ActionEvent actionEvent) {
        Appointment appointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        updateAppointmentID.setText(String.valueOf(appointment.getAppointmentId()));
        updateTitleTxt.setText(appointment.getTitle());
        updateDescriptionTxt.setText(appointment.getDescription());
        updateLocationTxt.setText(appointment.getLocation());
        updateContactCombo.setValue(appointment.getContact());
        updateTypeCombo.setValue(appointment.getType());
        updateDatePicker.setValue(appointment.getStart().toLocalDate());
        updateStartTimeCombo.setValue(appointment.getStart().toLocalTime());
        updateEndTimeCombo.setValue(appointment.getEnd().toLocalTime());
        updateCustomerIdCombo.setValue(appointment.getCustomerId());
        updateUserIdCombo.setValue(appointment.getUserId());


    }

}
