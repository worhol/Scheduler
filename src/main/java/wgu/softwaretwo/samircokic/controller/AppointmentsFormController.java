package wgu.softwaretwo.samircokic.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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

    ZoneId zoneId = ZoneId.systemDefault();
    @FXML
    private TextField addAppointmentID;
    @FXML
    private TextField addTitleTxt;
    @FXML
    private ComboBox addAppointmentStart;
    @FXML
    private ComboBox addAppointmentEnd;
    @FXML
    private TextField addDescriptionTxt;
    @FXML
    private TextField addLocationTxt;
    @FXML
    private DatePicker addDate;
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
    @FXML
    private Label cancelAppointmentLbl;
    @FXML
    private TableColumn weeklyLocationColumn;
    @FXML
    private TableColumn weeklyTypeColumn;
    @FXML
    private TableColumn monthlyStartDateColumn;
    @FXML
    private TableColumn monthlyTypeColumn;
    @FXML
    private TableColumn weeklyTitleColumn;
    @FXML
    private TableColumn monthlyCustomerIdColumn;
    @FXML
    private TableColumn monthlyDescriptionColumn;
    @FXML
    private TableColumn monthlyLocationColumn;
    @FXML
    private TableColumn monthlyAppointmentIdColumn;
    @FXML
    private TableColumn weeklyEndDateColumn;
    @FXML
    private TableColumn weeklyStartDateColumn;
    @FXML
    private TableColumn weeklyUserIdColumn;
    @FXML
    private TableColumn monthlyEndDateColumn;
    @FXML
    private TableColumn weeklyAppointmentIdColumn;
    @FXML
    private TableColumn monthlyTitleColumn;
    @FXML
    private TableColumn monthlyContactColumn;
    @FXML
    private TableColumn weeklyContactColumn;
    @FXML
    private TableColumn weeklyCustomerIdColumn;
    @FXML
    private TableColumn weeklyDescriptionColumn;
    @FXML
    private TableColumn monthlyUserIdColumn;
    @FXML
    private TableView weeklyAppointmentsTable;
    @FXML
    private TableView monthlyAppointmentsTable;
    @FXML
    private Label appointmentAlert;
    @FXML
    private ImageView imageAlert;
    @FXML
    private ComboBox contactReportCombo;
    @FXML
    private TableColumn contactReportCustomerIDColumn;
    @FXML
    private TableColumn contactReportEndColumn;
    @FXML
    private TableColumn contactReportAppointmentIDColumn;
    @FXML
    private TableColumn contactReportTypeColumn;
    @FXML
    private TableView contactReportTable;
    @FXML
    private TableColumn contactReportDescriptionColumn;
    @FXML
    private TableColumn contactReportTitleColumn;
    @FXML
    private TableColumn contactReportStartColumn;
    @FXML
    private ComboBox customerReportAppointmentsCombo;
    @FXML
    private PieChart customerAppointmentsPieChart;
    @FXML
    private PieChart customerMonthlyMeetingsPieChart;
    @FXML
    private PieChart customerContactsPieChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentAlert.setText(Schedule.appointmentAlert(zoneId));
        imageAlert.setVisible(true);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(() -> imageAlert.setVisible(false));
            }
        }, 5000l);


        //All appointments table
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

        //Weekly appointments table
        weeklyAppointmentsTable.setItems(Schedule.getWeeklyAppointments(zoneId));
        weeklyContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        weeklyEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        weeklyAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        weeklyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        weeklyUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        weeklyStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        weeklyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        weeklyCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        weeklyDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        weeklyLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        //Monthly appointments table
        monthlyAppointmentsTable.setItems(Schedule.getMonthlyAppointments(zoneId));
        monthlyContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        monthlyEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        monthlyAppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        monthlyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        monthlyUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        monthlyStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        monthlyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        monthlyCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        monthlyDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        monthlyLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        //Contact Report Table
//        contactReportTable

        contactReportEndColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        contactReportAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        contactReportTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        contactReportStartColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        contactReportTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        contactReportCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        contactReportDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        contactReportCombo.setItems(Appointment.getContactName());
        customerReportAppointmentsCombo.setItems(Appointment.getCustomerIDs());

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
//            AppointmentDao.contactID();
            AppointmentDao.contactName();
            Appointment.getTypeOfAppointment();
            AppointmentDao.customerID();
            AppointmentDao.userID();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        addContactDropBox.setItems(Appointment.getContactIDs());
        addContactDropBox.setItems(Appointment.getContactName());
        addTypeDropBox.setItems(Appointment.getTypeOfAppointment());
        addCustomerID.setItems(Appointment.getCustomerIDs());
        addUserID.setItems(Appointment.getUserIDs());
//        updateContactCombo.setItems(Appointment.getContactIDs());
        updateContactCombo.setItems(Appointment.getContactName());
        updateTypeCombo.setItems(Appointment.getTypeOfAppointment());
        updateCustomerIdCombo.setItems(Appointment.getCustomerIDs());
        updateUserIdCombo.setItems(Appointment.getUserIDs());


    }

    @FXML
    public void addAppointment(ActionEvent actionEvent) throws SQLException {

        try {
            String title = addTitleTxt.getText();
            String description = addDescriptionTxt.getText();
            String location = addLocationTxt.getText();
            String contact = addContactDropBox.getSelectionModel().getSelectedItem().toString();
            String type = addTypeDropBox.getSelectionModel().getSelectedItem().toString();
            LocalTime start = (LocalTime) addAppointmentStart.getValue();
            LocalTime end = (LocalTime) addAppointmentEnd.getValue();
            LocalDate date = addDate.getValue();
            LocalDateTime appointmentStart = LocalDateTime.of(date, start);
            LocalDateTime appointmentEnd = LocalDateTime.of(date, end);
            int customerID = Integer.valueOf(addCustomerID.getSelectionModel().getSelectedItem().toString());
            int userID = Integer.valueOf(addUserID.getSelectionModel().getSelectedItem().toString());
            if(appointmentStart.isAfter(appointmentEnd)||appointmentStart.isEqual(appointmentEnd)){
                    addAppointmentStart.setStyle("-fx-border-color: RED;");
                    addAppointmentEnd.setStyle("-fx-border-color: RED;");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Time issue");
                alert.setHeaderText(null);
                alert.setContentText("Appointment start must be earlier than appointment end");
                addAppointmentStart.setStyle("-fx-border-color: RED;");
                addAppointmentEnd.setStyle("-fx-border-color: RED;");
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType buttonType = result.orElse(ButtonType.OK);
                if (buttonType == ButtonType.OK) {
                    addAppointmentStart.setStyle(null);
                    addAppointmentEnd.setStyle(null);
                }
                    return;
            }
            if (!Schedule.appointmentOverlap(appointmentStart, appointmentEnd, customerID)) {
                AppointmentDao.addAppointment(title, description, location, type, appointmentStart, appointmentEnd, customerID, userID, contact);
                Schedule.getAppointments().clear();
                AppointmentDao.setTheAppointment();
                appointmentsTable.setItems(Schedule.getAppointments());
                Schedule.getWeeklyAppointments(zoneId).clear();
                weeklyAppointmentsTable.setItems(Schedule.getWeeklyAppointments(zoneId));
                Schedule.getMonthlyAppointments(zoneId).clear();
                monthlyAppointmentsTable.setItems(Schedule.getMonthlyAppointments(zoneId));

                addTitleTxt.clear();
                addDescriptionTxt.clear();
                addLocationTxt.clear();
                addContactDropBox.getSelectionModel().clearSelection();
                addTypeDropBox.getSelectionModel().clearSelection();
                addDate.getEditor().clear();
                addAppointmentStart.getSelectionModel().clearSelection();
                addAppointmentEnd.getSelectionModel().clearSelection();
                addCustomerID.getSelectionModel().clearSelection();
                addUserID.getSelectionModel().clearSelection();
                addAppointmentTitlePane.setExpanded(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Appointment time overlap");
                alert.setHeaderText(null);
                alert.setContentText("You attempted to schedule meeting that overlaps with the other customer's meetings");
                addAppointmentStart.setStyle("-fx-border-color: RED;");
                addAppointmentEnd.setStyle("-fx-border-color: RED;");
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType buttonType = result.orElse(ButtonType.OK);
                if (buttonType == ButtonType.OK) {
                    addAppointmentStart.setStyle(null);
                }


            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty Fields");
            alert.setHeaderText(null);
            alert.setContentText("All fields have to be populated");
            if (addTitleTxt.getText().isEmpty()) {
                addTitleTxt.setStyle("-fx-border-color: RED;");
            } else if (addDescriptionTxt.getText().isEmpty()) {
                addDescriptionTxt.setStyle("-fx-border-color: RED;");
            } else if (addLocationTxt.getText().isEmpty()) {
                addLocationTxt.setStyle("-fx-border-color: RED;");
            } else if (addContactDropBox.getSelectionModel().getSelectedItem() == null) {
                addContactDropBox.setStyle("-fx-border-color: RED;");
            } else if (addTypeDropBox.getSelectionModel().getSelectedItem() == null) {
                addTypeDropBox.setStyle("-fx-border-color: RED;");
            } else if (addDate.getValue() == null) {
                addDate.setStyle("-fx-border-color: RED;");
            } else if (addAppointmentStart.getSelectionModel().getSelectedItem() == null) {
                addAppointmentStart.setStyle("-fx-border-color: RED;");
            } else if (addAppointmentEnd.getSelectionModel().getSelectedItem() == null) {
                addAppointmentEnd.setStyle("-fx-border-color: RED;");
            } else if (addCustomerID.getSelectionModel().getSelectedItem() == null) {
                addCustomerID.setStyle("-fx-border-color: RED;");
            } else if (addUserID.getSelectionModel().getSelectedItem() == null) {
                addUserID.setStyle("-fx-border-color: RED;");
            }

            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
            if (buttonType == ButtonType.OK) {
                addTitleTxt.setStyle(null);
                addDescriptionTxt.setStyle(null);
                addLocationTxt.setStyle(null);
                addContactDropBox.setStyle(null);
                addTypeDropBox.setStyle(null);
                addDate.setStyle(null);
                addAppointmentStart.setStyle(null);
                addAppointmentEnd.setStyle(null);
                addCustomerID.setStyle(null);
                addUserID.setStyle(null);
            }

        }

    }


    @FXML
    public void deleteAppointment(ActionEvent actionEvent) throws SQLException, InterruptedException {
        try {
            Appointment appointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
            Appointment appointmentWeekly = (Appointment) weeklyAppointmentsTable.getSelectionModel().getSelectedItem();
            Appointment appointmentMonthly = (Appointment) monthlyAppointmentsTable.getSelectionModel().getSelectedItem();
            if (appointment == null && appointmentMonthly == null) {
                appointment = appointmentWeekly;
            } else if (appointment == null && appointmentWeekly == null) {
                appointment = appointmentMonthly;
            } else if (appointment == null && appointmentWeekly == null && appointmentMonthly == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Appointment not selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select the appointment you want to cancel");
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType buttonType = result.orElse(ButtonType.OK);
//            if (buttonType==ButtonType.OK){
//
//            }
            }
            AppointmentDao.deleteAppointment(appointment);
            Schedule.refreshAppointments();
//        AppointmentDao.setTheAppointment(Schedule.getUserID());
            AppointmentDao.setTheAppointment();
            Schedule.deleteAppointment(appointment); //uncomented due to ConcurrentModificationException
            appointmentsTable.setItems(Schedule.getAppointments());
            Schedule.deleteWeeklyAppointment(appointment);//uncommented due to ConcurrentModificationException
            weeklyAppointmentsTable.setItems(Schedule.getWeeklyAppointments(zoneId));
            Schedule.deleteMonthlyAppointment(appointment);//uncomented due to ConcurrentModificationException
            monthlyAppointmentsTable.setItems(Schedule.getMonthlyAppointments(zoneId));

            cancelAppointmentLbl.setText("Appointment " + appointment.getAppointmentId() + " " + appointment.getType() + " was canceled.");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        deleteAppointmentTitlePane.setExpanded(false);
                        cancelAppointmentLbl.setText("Please select the appointment you want to cancel and press SELECT button");

                    });
                }
            }, 2000l);

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the appointment you want to cancel");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
        }

    }


    @FXML
    public void addCustomer(ActionEvent actionEvent) throws SQLException, IOException {
        try {
            String name = addCustomerName.getText();
            String address = addCustomerAddress.getText();
            String postalCode = addCustomerPostalCode.getText();
            String phone = addPhoneNumber.getText();
//            String country = addCustomerCountry.getSelectionModel().getSelectedItem().toString();
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
            AppointmentDao.customerID();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            if (addCustomerName.getText().isEmpty()) {
                addCustomerName.setStyle("-fx-border-color: RED;");
            } else if (addCustomerAddress.getText().isEmpty()) {
                addCustomerAddress.setStyle("-fx-border-color: RED;");
            } else if (addCustomerPostalCode.getText().isEmpty()) {
                addCustomerPostalCode.setStyle("-fx-border-color: RED;");
            } else if (addPhoneNumber.getText().isEmpty()) {
                addPhoneNumber.setStyle("-fx-border-color: RED;");
            } else if (addCustomerCountry.getSelectionModel().getSelectedItem() == null) {
                addCustomerCountry.setStyle("-fx-border-color: RED;");
            } else if (addCustomerProvince.getSelectionModel().getSelectedItem() == null) {
                addCustomerProvince.setStyle("-fx-border-color: RED;");
            }

            alert.setTitle("Fields can not be empty");
            alert.setHeaderText(null);
            alert.setContentText("Fields can not be empty");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
            if (buttonType == ButtonType.OK) {
                addCustomerName.setStyle(null);
                addCustomerAddress.setStyle(null);
                addCustomerPostalCode.setStyle(null);
                addPhoneNumber.setStyle(null);
                addCustomerCountry.setStyle(null);
                addCustomerProvince.setStyle(null);
            }


        }

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
    public void deleteCustomer(ActionEvent actionEvent) throws SQLException, InterruptedException, IOException {
        int index = customerTable.getSelectionModel().getSelectedIndex();
        int id = 0;
        Customer customer = null;
        for (int i = 0; i < Schedule.getCustomers().size(); i++) {
            if (i == index) {
                id = Schedule.getCustomers().get(i).getCustomerId();
                customer = Schedule.getCustomers().get(i);
            }
        }
        if (customer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Customer not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the customer you want to remove");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
        }
        AppointmentDao.deleteAllCustomerAppointments(id);
        Schedule.getAppointments().clear();
        Schedule.getWeeklyAppointments(zoneId).clear();
        Schedule.getMonthlyAppointments(zoneId).clear();
        AppointmentDao.setTheAppointment();
        appointmentsTable.setItems(Schedule.getAppointments());
        weeklyAppointmentsTable.setItems(Schedule.getWeeklyAppointments(zoneId));
        monthlyAppointmentsTable.setItems(Schedule.getMonthlyAppointments(zoneId));
        if (CustomerDao.deleteCustomer(id) > 0) {
            deleteCustomerLabel.setText("Customer removed.");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    Platform.runLater(() -> {
                        deleteCostumerTitlePane.setExpanded(false);
                        deleteCustomerLabel.setText("Please select the customer you want to remove and press SELECT button");

                    });
                }
            }, 2000l);

        }

        Schedule.deleteCustomer(customer);
        customerTable.setItems(Schedule.getCustomers());
        AppointmentDao.customerID();


    }

    @FXML
    public void updateCustomer(ActionEvent actionEvent) throws SQLException {
        try {
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
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Customer not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the customer you want to update");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
        }

    }

    @FXML
    public void getCustomer(ActionEvent actionEvent) throws SQLException {
        try {
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

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Customer not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the customer you want to update");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
        }

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
        try {
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

            if(startAppointment.isAfter(endAppointment)||startAppointment.isEqual(endAppointment)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Time issue");
                alert.setHeaderText(null);
                alert.setContentText("Appointment start must be earlier than appointment end");
                updateStartTimeCombo.setStyle("-fx-border-color: RED;");
                updateEndTimeCombo.setStyle("-fx-border-color: RED;");
                Optional<ButtonType> result = alert.showAndWait();
                ButtonType buttonType = result.orElse(ButtonType.OK);
                if (buttonType == ButtonType.OK) {
                    updateStartTimeCombo.setStyle(null);
                    updateEndTimeCombo.setStyle(null);
                }
                return;
            }

            String contact = updateContactCombo.getSelectionModel().getSelectedItem().toString();// when not changed to number exception
            AppointmentDao.updateAppointment(title, description, location, type, startAppointment, endAppointment, customerID, userID, contact, appointmentId);
            Schedule.refreshAppointments();
//        AppointmentDao.setTheAppointment(userID);
            AppointmentDao.setTheAppointment();
            appointmentsTable.setItems(Schedule.getAppointments());
            weeklyAppointmentsTable.setItems(Schedule.getWeeklyAppointments(zoneId));
            monthlyAppointmentsTable.setItems(Schedule.getMonthlyAppointments(zoneId));
            updateAppointmentTitlePane.setExpanded(false);

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the appointment you want to cancel");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
        } catch (NumberFormatException n) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the appointment you want to cancel");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
        }
    }

    @FXML
    public void selectAppointment(ActionEvent actionEvent) {
        try {
            Appointment appointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
            Appointment appointmentWeekly = (Appointment) weeklyAppointmentsTable.getSelectionModel().getSelectedItem();
            Appointment appointmentMonthly = (Appointment) monthlyAppointmentsTable.getSelectionModel().getSelectedItem();
            if (appointment == null && appointmentMonthly == null) {
                appointment = appointmentWeekly;
            } else if (appointment == null && appointmentWeekly == null) {
                appointment = appointmentMonthly;
            }
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

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the appointment you want to cancel");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
        }


    }

    @FXML
    public void cancelDeleteAppointment(ActionEvent actionEvent) {
        cancelAppointmentLbl.setText("Please select the appointment you want to cancel and press SELECT button");
        appointmentsTable.getSelectionModel().clearSelection();
        weeklyAppointmentsTable.getSelectionModel().clearSelection();
        monthlyAppointmentsTable.getSelectionModel().clearSelection();
        deleteAppointmentTitlePane.setExpanded(false);
    }

    @FXML
    public void cancelDeleteCustomer(ActionEvent actionEvent) {
        deleteCustomerLabel.setText("Please select the customer you want to delete and press SELECT button");
        customerTable.getSelectionModel().clearSelection();
        deleteCostumerTitlePane.setExpanded(false);
    }


    @FXML
    public void selectAppointmenForDelete(ActionEvent actionEvent) {
        Appointment appointment = (Appointment) appointmentsTable.getSelectionModel().getSelectedItem();
        Appointment appointmentWeekly = (Appointment) weeklyAppointmentsTable.getSelectionModel().getSelectedItem();
        Appointment appointmentMonthly = (Appointment) monthlyAppointmentsTable.getSelectionModel().getSelectedItem();
        if (appointment != null) {
            cancelAppointmentLbl.setText("Are you sure you want to cancel this appointment? Press DELETE to confirm or " +
                    "press CANCEL to exit");
        } else if (appointmentWeekly != null) {
            cancelAppointmentLbl.setText("Are you sure you want to cancel this appointment? Press DELETE to confirm or " +
                    "press CANCEL to exit");
        } else if (appointmentMonthly != null) {
            cancelAppointmentLbl.setText("Are you sure you want to cancel this appointment? Press DELETE to confirm or " +
                    "press CANCEL to exit");
        } else if (appointment == null && appointmentWeekly == null && appointmentMonthly == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Appointment not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the appointment you want to cancel");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
        }

//
    }

    @FXML
    public void selectCustomerForDelete(ActionEvent actionEvent) {
        Customer customer = (Customer) customerTable.getSelectionModel().getSelectedItem();
        if (customer != null) {
            deleteCustomerLabel.setText("Are you sure you want to remove this customer? Press DELETE to DELETE or " +
                    "press CANCEL to exit");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Customer not selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the customer you want to remove");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.OK);
        }

    }

    @FXML
    public void selectContactReportButton(ActionEvent actionEvent) {
        String name = contactReportCombo.getSelectionModel().getSelectedItem().toString();
        contactReportTable.setItems(Report.contactAppointments(name));
    }

    @FXML
    public void clearContactReportButton(ActionEvent actionEvent) {
        contactReportCombo.getSelectionModel().clearSelection();
        contactReportTable.getItems().clear();
    }

    @FXML
    public void customerReportAppointmentsClearButton(ActionEvent actionEvent) {
        customerReportAppointmentsCombo.getSelectionModel().clearSelection();
        customerAppointmentsPieChart.getData().clear();
        customerMonthlyMeetingsPieChart.getData().clear();
        customerContactsPieChart.getData().clear();
    }

    @FXML
    public void customerReportAppointmentsSelectButton(ActionEvent actionEvent) {
        int id = Integer.valueOf(customerReportAppointmentsCombo.getSelectionModel().getSelectedItem().toString());
        customerAppointmentsPieChart.setData(Report.typePieChart(id));
        customerMonthlyMeetingsPieChart.setData(Report.monthlyPieChart(id));
        customerContactsPieChart.setData(Report.customerContactPieChart(id));
    }

    @FXML
    public void cancelUpdateAppointment(ActionEvent actionEvent) {
        appointmentsTable.getSelectionModel().clearSelection();
        weeklyAppointmentsTable.getSelectionModel().clearSelection();
        monthlyAppointmentsTable.getSelectionModel().clearSelection();
        updateAppointmentID.clear();
        updateTitleTxt.clear();
        updateDescriptionTxt.clear();
        updateLocationTxt.clear();
        updateContactCombo.getSelectionModel().clearSelection();
        updateTypeCombo.getSelectionModel().clearSelection();
        updateDatePicker.getEditor().clear();
        updateStartTimeCombo.getSelectionModel().clearSelection();
        updateEndTimeCombo.getSelectionModel().clearSelection();
        updateCustomerIdCombo.getSelectionModel().clearSelection();
        updateUserIdCombo.getSelectionModel().clearSelection();
        updateAppointmentTitlePane.setExpanded(false);
    }

    @FXML
    public void cancelAddCustomer(ActionEvent actionEvent) {
        addCustomerName.clear();
        addCustomerAddress.clear();
        addCustomerPostalCode.clear();
        addPhoneNumber.clear();
        addCustomerCountry.getSelectionModel().clearSelection();
        addCustomerProvince.getSelectionModel().clearSelection();
        addCostumerTitlePane.setExpanded(false);
    }

    @FXML
    public void cancelAddAppointment(ActionEvent actionEvent) {
        addTitleTxt.clear();
        addDescriptionTxt.clear();
        addLocationTxt.clear();
        addContactDropBox.getSelectionModel().clearSelection();
        addTypeDropBox.getSelectionModel().clearSelection();
        addDate.getEditor().clear();
        addAppointmentStart.getSelectionModel().clearSelection();
        addAppointmentEnd.getSelectionModel().clearSelection();
        addCustomerID.getSelectionModel().clearSelection();
        addUserID.getSelectionModel().clearSelection();
        addAppointmentTitlePane.setExpanded(false);
    }

    @FXML
    public void cancelGetCustomer(ActionEvent actionEvent) {
        customerTable.getSelectionModel().clearSelection();
        updateCustomerID.clear();
        updateCustomerName.clear();
        updateCustomerAddress.clear();
        updateCustomerPostalCode.clear();
        updatePhoneNumber.clear();
        updateCustomerCountry.getSelectionModel().clearSelection();
        updateCustomerProvince.getSelectionModel().clearSelection();
        updateCostumerTitlePane.setExpanded(false);
    }
}
