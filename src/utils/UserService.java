package utils;

import bean.User;

import java.sql.SQLException;

public class UserService {
    private UserDao userDao = new UserDao();

    /**
     * 添加用户
     * @param user 要添加的用户对象
     * @throws SQLException
     */
    public int add(User user) throws SQLException {
        return userDao.add(user);
    }
    public User login(User user) throws SQLException {
        return userDao.login(user);
    }
    public boolean isExist(long phone) throws SQLException{
        return userDao.isPhoneExist(phone);
    }
}
