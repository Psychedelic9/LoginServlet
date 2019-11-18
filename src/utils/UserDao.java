package utils;

import bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection connection = JDBCUtils.getConnection();
    private PreparedStatement ps;

    public boolean isPhoneExist(long phone) throws SQLException{
        String sql="SELECT phone FROM login";
        boolean isExist = false;
        ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            if (rs.getLong("phone")==phone){
                isExist =  true;
                break;
            }
        }
        return isExist;
    }

    public  int add(User user) throws SQLException {
        String sql = "insert into login(phone,password) values(?,?)";

        ps = connection.prepareStatement(sql);
        ps.setObject(1,user.getPhone());
        ps.setObject(2,user.getPassword());
        return ps.executeUpdate();
    }
    public  User login(User user) throws SQLException {
        String sql = "select * from login where phone = ? and password = ?";
        ps = connection.prepareStatement(sql);
        ps.setObject(1,user.getPhone());
        ps.setObject(2,user.getPassword());
        ResultSet rs = ps.executeQuery();
        User u =null;
        while(rs.next()){
            long id = rs.getLong("phone");
            String password = rs.getString("password");
            u = new User(id,password);
        }
        return u;
    }

}
