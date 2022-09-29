package wgu.softwaretwo.samircokic.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import wgu.softwaretwo.samircokic.DAO.CustomerDao;
import wgu.softwaretwo.samircokic.model.Country;
import wgu.softwaretwo.samircokic.model.Customer;
import wgu.softwaretwo.samircokic.model.Division;
import wgu.softwaretwo.samircokic.model.Schedulle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

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
    private ComboBox updateCustomerProvince;
    @FXML
    private TextField updatePhoneNumber;
    @FXML
    private TextField updateCustomerName;
    @FXML
    private TitledPane updateCostumerTitlePane;
    @FXML
    private TextField updateCustomerPostalCode;
    @FXML
    private ComboBox updateCustomerCountry;

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
        try {
            CustomerDao.setCustomer();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerTable.setItems(Schedulle.getCustomers());
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAdressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        customerCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        customerProvinceCol.setCellValueFactory(new PropertyValueFactory<>("division"));

    }

    @FXML
    public void addAppointment(ActionEvent actionEvent) {

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
        CustomerDao.addCustomer(name,address,postalCode,phone, divisionID);

//        Schedulle.addCustomers(new Customer(Schedulle.getCustomers().size()+1,name,address,postalCode,phone,country,province));
        customerTable.setItems(Schedulle.getCustomers());
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
        for(int i = 0; i<Schedulle.getCustomers().size();i++){
            if(i==index){
                id = Schedulle.getCustomers().get(i).getCustomerId();
                customer = Schedulle.getCustomers().get(i);
            }
        }
       if (CustomerDao.deleteCustomer(id)>0){
           deleteCustomerLabel.setText("Customer Deleted");
           Timeline timeline = new Timeline();
           List<KeyValue> values = new ArrayList<>();
           values.add(new KeyValue(deleteCustomerLabel.textProperty(), "Customer Deleted"));
           values.add(new KeyValue(deleteCustomerLabel.textProperty(), "Please select the customer you want to delete and press DELETE button."));
           timeline.getKeyFrames().add(new KeyFrame(new Duration(1000), values.toArray(new  KeyValue[values.size()])));
           timeline.play();
           deleteCostumerTitlePane.setExpanded(false);
       }

        Schedulle.deleteCustomer(customer);
        customerTable.setItems(Schedulle.getCustomers());


    }

    @FXML
    public void updateCustomer(ActionEvent actionEvent) {
    }

    @FXML
    public void getCustomer(ActionEvent actionEvent) {
        int index = customerTable.getSelectionModel().getSelectedIndex();
    }
}
