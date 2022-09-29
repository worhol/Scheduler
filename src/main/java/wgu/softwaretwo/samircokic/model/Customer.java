package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wgu.softwaretwo.samircokic.DAO.CustomerDao;

import java.sql.SQLException;

public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String country;
    private String division;
    private int countryID;
    private int divisionID;

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, int countryID, String country, int divisionID, String division) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.division = division;
        this.countryID = countryID;
        this.divisionID = divisionID;
    }

    public static ObservableList<Country>countries = FXCollections.observableArrayList();

    public static ObservableList<Country> getCountries() throws SQLException {
        CustomerDao.countries();
        return countries;
    }

    public static void addCountry(Country country) {
        countries.add(country);
    }

    public static ObservableList<Division>divisions = FXCollections.observableArrayList();

    public static ObservableList<Division> getDivisions() throws SQLException {
        return divisions;
    }

    public static void addDivision(Division division) {
        divisions.add(division);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }
}

