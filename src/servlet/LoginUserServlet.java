package servlet;

import bean.ResultEntity;
import bean.User;
import com.google.gson.Gson;
import utils.Constants;
import utils.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        Gson gson = new Gson();
        User user = new User();
        String json;
        PrintWriter out = resp.getWriter();

        user.setPhone(Long.parseLong(phone));
        user.setPassword(pwd);
        UserService us = new UserService();
        User u= null;
        ResultEntity result = new ResultEntity();

        try {
            u = us.login(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(u==null){
            result.setSuccess("false");
            result.setData("登录失败！");
            result.setMessage("登录失败！");
            result.setCode(Constants.FAIL);
            json = gson.toJson(result);
            System.out.println("登录失败:账号"+user.getPhone());
        }else {
            result.setSuccess("true");
            result.setData("登录成功！");
            result.setMessage("登录成功！");
            result.setCode(Constants.SUCCESS);
            json = gson.toJson(result);
            System.out.println("登录成功: 账号"+user.getPhone());
        }
        out.println(json);
        out.flush();
        out.close();


    }

}
