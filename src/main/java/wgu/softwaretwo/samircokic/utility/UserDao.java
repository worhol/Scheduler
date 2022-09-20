package wgu.softwaretwo.samircokic.utility;

import wgu.softwaretwo.samircokic.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDao {


    public static int passwordCheck(String name, String pass) throws SQLException {
        int id = 0;
        String sql = "SELECT * FROM USERS WHERE User_Name=? AND Password = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pass);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("USER_ID");
        }
        return id;
    }
}