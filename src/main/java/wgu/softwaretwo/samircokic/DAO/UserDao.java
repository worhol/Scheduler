package wgu.softwaretwo.samircokic.DAO;

import wgu.softwaretwo.samircokic.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDao {

    public static User user(String name, String pass) throws SQLException {
        int id = 0;
        String sql = "SELECT * FROM USERS WHERE User_Name=? AND Password = ?";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pass);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getInt("USER_ID");
        }
        User user = new User(id,name,pass);
        return user;
    }

    public static int usernameAndPasswordCheck(String name, String pass) throws SQLException {
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
    public static int usernameCheck(String name) throws SQLException {
        int id = 0;
        String sql = "SELECT * FROM USERS WHERE User_Name=? ";
        PreparedStatement preparedStatement = JDBC.connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(!resultSet.next()){
           id=0;
        }else {
            id = resultSet.getInt("USER_ID");
        }

        return id;
    }

}