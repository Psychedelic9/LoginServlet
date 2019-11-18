package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    public  static Connection connection;
    private  static String url = "jdbc:mysql://localhost:3306/login?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    private  static String user = "root";
    private  static String password = "pwd";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.print("Connection start!!!");
            connection = DriverManager.getConnection(url,user,password);
            if (connection == null){
                System.out.print("Connection null!!!");
            }else {
                System.out.print("Connection success!!!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return  connection;
    }

}
