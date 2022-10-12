package wgu.softwaretwo.samircokic.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import wgu.softwaretwo.samircokic.DAO.CustomerDao;

import java.sql.SQLException;

/**
 * <p>This clas is the blueprint for the customer object</p>
 *
 * @author Samir Cokic
 */
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

    /**
     * <p>This method returns country id.</p>
     *
     * @return country id
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * <p>This method sets country id.</p>
     *
     * @param countryID country id
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * <p>This method returns division id.</p>
     *
     * @return division id
     */
    public int getDivisionID() {
        return divisionID;
    }


    /**
     * <p>This method sets division id</p>
     *
     * @param divisionID division id
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * @param customerId unique customer number
     * @param customerName customer name
     * @param address customer address
     * @param postalCode customer postal code
     * @param phoneNumber customer phone number
     * @param countryID customer's country id
     * @param country customer's country name
     * @param divisionID customer's province/state id
     * @param division customer province/state
     */
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

    /**
     * <p>Observable list of the countries</p>
     */
    public static ObservableList<Country>countries = FXCollections.observableArrayList();

    /**
     * <p>This method calls the countries method from CustomerDao class and after it queries the database,
     * method returns the observable list of the countries.</p>
     *
     * @return the observable list of the countries
     * @throws SQLException provides information on database access errors.
     */
    public static ObservableList<Country> getCountries() throws SQLException {
        CustomerDao.countries();
        return countries;
    }

    /**
     * <p>This method takes the country object as the argument and adds it to the countries list.</p>
     *
     * @param country country
     */
    public static void addCountry(Country country) {
        countries.add(country);
    }

    /**
     * <p>Observable list of the divisions</p>
     */
    public static ObservableList<Division>divisions = FXCollections.observableArrayList();


    /**
     * <p>This method returns the list of the divisions</p>
     *
     * @return observable list of divisions
     * @throws SQLException provides information on database access errors.
     */
    public static ObservableList<Division> getDivisions() throws SQLException {
        return divisions;
    }

    /**
     * <p>This method takes the division object as the argument and adds it to the divisions list.</p>
     *
     * @param division division
     */
    public static void addDivision(Division division) {
        divisions.add(division);
    }

    /**
     * <p>This method returns customer id number</p>
     *
     * @return customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * <p>This method sets customer id number</p>
     *
     * @param customerId customer id
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * <p>This method returns customer name</p>
     *
     * @return customer name
     */
    public String getCustomerName() {
        return customerName;
    }


    /**
     * <p>This method sets customer name</p>
     *
     * @param customerName customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * <p>This method returns customer adress</p>
     *
     * @return customer address
     */
    public String getAddress() {
        return address;
    }

    /**
     * <p>This method sets customer address</p>
     *
     * @param address customer address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * <p>This method returns customer postal code</p>
     *
     * @return customer postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * <p>This method sets customer postal code</p>
     *
     * @param postalCode customer postal code.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * <p>This method returns customer phone number</p>
     *
     * @return customer phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * <p>This method sets customer phone number</p>
     *
     * @param phoneNumber customer phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * <p>This method returns customer country</p>
     *
     * @return customer country
     */
    public String getCountry() {
        return country;
    }

    /**
     * <p>This method sets customer country</p>
     *
     * @param country customer country.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * <p>This method returns customer division</p>
     *
     * @return customer division
     */
    public String getDivision() {
        return division;
    }

    /**
     * <p>This method sets customer division</p>
     *
     * @param division customer country.
     */
    public void setDivision(String division) {
        this.division = division;
    }
}

