package wgu.softwaretwo.samircokic.DAO;

import wgu.softwaretwo.samircokic.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {

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

    public static void divisions(int countryId) throws SQLException {
//        Customer.getDivisions().clear();
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

    public static int addCustomer(String name, String address, String postalCode, String phone, int divisionId) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, address);
        preparedStatement.setString(3, postalCode);
        preparedStatement.setString(4, phone);
        preparedStatement.setInt(5, divisionId);

//        Schedulle.addCustomers(new Customer(Schedulle.getCustomers().size()+1,name,address,postalCode,phone,getCountryName(getCountryID(divisionId)),getDivisionName(divisionId)));


        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }

    public static int deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID =?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected;
    }

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
