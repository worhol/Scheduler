package wgu.softwaretwo.samircokic.DAO;

import wgu.softwaretwo.samircokic.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>This class is the blueprint for the User class object and it handles its sql queries.</p>
 *
 * @author Samir Cokic
 */
public abstract class UserDao {

    /**
     * <p>This method queries the database table users and returns the user object</p>
     *
     * @param name username
     * @param pass password
     * @return if the username and a password matches the database records it returns the user object associated with a query.
     * @throws SQLException provides information on database access errors.
     */
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

    /**
     * <p>This method queris the users table and retrieves the user id if it matches the arguments, else it returns 0.</p>
     *
     * @param name username
     * @param pass password
     * @return the user id
     * @throws SQLException provides information on database access errors.
     */
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

    /**
     * <p>this method checks if the username exist in the database, if it exist then it returns its associated id if not then it returns 0.</p>
     *
     * @param name username
     * @return user id
     * @throws SQLException provides information on database access errors.
     */
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