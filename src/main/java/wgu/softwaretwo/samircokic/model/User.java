package wgu.softwaretwo.samircokic.model;

import wgu.softwaretwo.samircokic.DAO.UserDao;

import java.sql.SQLException;

/**
 * <p>The class User is the blueprint for user object which logs in to scheduling application</p>
 *
 * @author Samir Cokic
 */
public class User {
    private int id;
    private String userName;
    private String password;

    /**
     * @param id user id
     * @param userName username
     * @param password password
     * @throws SQLException provides information on database access errors.
     */
    public User(int id, String userName, String password) throws SQLException {
        this.id = UserDao.usernameAndPasswordCheck(userName,password);
        this.userName = userName;
        this.password = password;
    }

    /**
     * <p>This method returns the user id.</p>
     *
     * @return user id
     */
    public int getId() {
        return id;
    }

    /**
     * <p>This method takes integer as the argument and sets it as the user id.</p>
     *
     * @param id user id
     */
    public void setId(int id) {
        this.id = id;
    }

}
