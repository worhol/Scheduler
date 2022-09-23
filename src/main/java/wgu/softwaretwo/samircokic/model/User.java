package wgu.softwaretwo.samircokic.model;

import wgu.softwaretwo.samircokic.DAO.UserDao;

import java.sql.SQLException;

public class User {
    private int id;
    private String userName;
    private String password;

    public User(int id, String userName, String password) throws SQLException {
        this.id = UserDao.usernameAndPasswordCheck(userName,password);
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
