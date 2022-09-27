package wgu.softwaretwo.samircokic.DAO;

import wgu.softwaretwo.samircokic.model.Country;
import wgu.softwaretwo.samircokic.model.Customer;
import wgu.softwaretwo.samircokic.model.Division;

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
            Country country = new Country(countryId,countryName);
           Customer.addCountry(country);
        }
    }
    public static void divisions(int countryId) throws SQLException {
        String divisionName = "";
        int divisionId = 0;
        String sql = "SELECT * FROM FIRST_LEVEL_DIVISIONS WHERE Country_ID = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setInt(1, countryId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            divisionName = resultSet.getString("Division");
            divisionId = resultSet.getInt("Division_ID");
            Division division = new Division(divisionId,divisionName,countryId);
            Customer.addDivision(division);
        }
    }

}
