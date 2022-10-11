package wgu.softwaretwo.samircokic.DAO;

import wgu.softwaretwo.samircokic.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>This class is handling the sql queries for the customer class</p>
 *
 * @author Samir Cokic
 */
public class CustomerDao {

    /**
     * <p>This methods retrieve data from countries table and creates the country object and adds ti to observable list.</p>
     *
     * @throws SQLException provides information on database access errors.
     */
    public static void countries() throws SQLException {
        int countryId = 0;
        String countryName = "";
        String sql = "SELECT * FROM COUNTRIES";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            countryId = resultSet.getInt("Country_ID");
            countryName = resultSet.getString("Country");
            Country country = new Country(countryId, countryName);
            Customer.addCountry(country);
        }
    }

    /**
     * <p>This method takes unique id number and then select all divisions associated with a country's id, then creates the division object and adds it to the observable list.</p>
     *
     * @param countryId the unique country's id number
     * @throws SQLException provides information on database access errors.
     */
    public static void divisions(int countryId) throws SQLException {
        String divisionName = "";
        int divisionId = 0;
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Country_ID = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, countryId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            divisionName = resultSet.getString("Division");
            divisionId = resultSet.getInt("Division_ID");
            Division division = new Division(divisionId, divisionName, countryId);
            Customer.addDivision(division);
        }
    }

    /**
     * <p>This method selects all the data from the customers table and for each row it creates customer object, which is then added to observable list.
     * It catches the sql exceptions.</p>
     */
    public static void setCustomer() {
        int customerId = 0;
        String customerName = "";
        String address = "";
        String postalCode = "";
        String phoneNumber = "";
        int divisionID = 0;
        String country = "";
        String division = "";
        int countryID = 0;
        try {
            String sql = "SELECT * FROM CUSTOMERS";
            PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerId = resultSet.getInt("Customer_ID");
                customerName = resultSet.getString("Customer_Name");
                address = resultSet.getString("Address");
                postalCode = resultSet.getString("Postal_Code");
                phoneNumber = resultSet.getString("Phone");
                divisionID = resultSet.getInt("Division_ID");
                countryID = getCountryID(divisionID);
                country = getCountryName(countryID);
                division = getDivisionName(divisionID);
                Customer customer = new Customer(customerId, customerName, address, postalCode, phoneNumber, countryID, country, divisionID, division);
                Schedule.addCustomers(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>This method updates the data from the arguments into customers table. It returns int > 0 if the update was success, else it returns 0.</p>
     *
     * @param customerID the unique customer's id
     * @param name customer name
     * @param address customer's address
     * @param postalCode customer's postal code
     * @param phone customer's phone number
     * @param divisionID customer's province/state id number
     * @return if the update was success returns greater than 0 number of affected rows, else it returns 0.
     * @throws SQLException provides information on database access errors.
     */
    public static int updateCustomer(int customerID, String name, String address, String postalCode, String phone, int divisionID) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5,divisionID);
        preparedStatement.setInt(6, customerID);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }

    /**
     * <p>This method takes the division id as the argument, queries the first_level_divisions table and returns the country id associated with the state/province id.</p>
     *
     * @param divisionID province/state unique id
     * @return country id associated with a division id.
     * @throws SQLException provides information on database access errors.
     */
    public static int getCountryID(int divisionID) throws SQLException {
        int countryID = 0;
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, divisionID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            countryID = resultSet.getInt("Country_ID");
        }
        return countryID;
    }

    /**
     * <p>This method takes the country id as the argument, queries the countries table and returns the country name associated with the id.</p>
     *
     * @param countryID country's unique id
     * @return country name as a String associated with a country id.
     * @throws SQLException provides information on database access errors.
     */
    public static String getCountryName(int countryID) throws SQLException {
        String countryName = "";
        String sql = "SELECT * FROM COUNTRIES WHERE Country_ID = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, countryID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            countryName = resultSet.getString("Country");
        }
        return countryName;
    }

    /**
     * <p>This method takes the division id as the argument, queries the first_level_divisions table and returns the division name associated with the id.</p>
     *
     * @param divisionID division's unique id
     * @return division name as a String associated with a division id.
     * @throws SQLException provides information on database access errors.
     */
    public static String getDivisionName(int divisionID) throws SQLException {
        String divisionName = "";
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division_ID = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, divisionID);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            divisionName = resultSet.getString("Division");
        }
        return divisionName;
    }

    /**
     * <p>This method inserts the data from the arguments into customers table. It returns int > 0 if the update was success, else it returns 0.</p>
     *
     * @param name the name of the customer
     * @param address customer's address
     * @param postalCode customer's postal code
     * @param phone customer's phone number
     * @param divisionId customer's province/state id number
     * @return if the update was success returns greater than 0 number of affected rows, else it returns 0.
     * @throws SQLException provides information on database access errors.
     */
    public static int addCustomer(String name, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, divisionId);

        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }

    /**
     * <p>This method queris the customers table and remove the customer record based on customer's id</p>
     *
     * @param id customer id
     * @return if the deletion was success returns number of affected rows greater than 0 , else it returns 0.
     * @throws SQLException provides information on database access errors.
     */
    public static int deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID =?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }

    /**
     * <p> This method accepts division name as the argument and returns the id associated with it from the first_level_divisions table.</p>
     *
     * @param name the name of the province/division
     * @return the division id number
     * @throws SQLException provides information on database access errors.
     */
    public static int getDivisionId(String name) throws SQLException {
        int divisionId = 0;
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Division = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            divisionId = resultSet.getInt("Division_ID");
        }
        return divisionId;
    }


}
