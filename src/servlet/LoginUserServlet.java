package servlet;

import bean.User;
import utils.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        String phone = req.getParameter("phone");
        String pwd = req.getParameter("password");
        User user = new User();
        user.setPhone(Long.parseLong(phone));
        user.setPassword(pwd);
        UserService us = new UserService();
        User u= null;
        try {
            u = us.login(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(u==null){
            System.out.println("登录失败: 账号"+user.getPhone());
        }else {
            System.out.println("登录成功:账号"+user.getPhone());
        }


    }

}
