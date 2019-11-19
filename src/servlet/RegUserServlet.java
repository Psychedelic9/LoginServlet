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

public class RegUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
        PrintWriter out = resp.getWriter();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=UTF-8");

        //接收请求参数
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");
        String json;
        PrintWriter out = resp.getWriter();

        User user = new User();
        user.setPhone(Long.parseLong(phone));
        user.setPassword(password);
        Gson gson = new Gson();

        //存储数据
        UserService us = new UserService();
        ResultEntity result = new ResultEntity();

        try {
            boolean isExist = us.isExist(user.getPhone());
            if (isExist){
                System.out.println("号码已经注册！号码："+user.getPhone());
                result.setCode(Constants.THIS_PHONE_ALREADY_REGISTERED);
                result.setData("此手机号已被注册！");
                result.setSuccess("false");
                result.setMessage("此手机号已被注册!");

                json = gson.toJson(result);
                System.out.println("result json = "+json);
                out.println(json);
                out.flush();
                out.close();
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int i = 0;
        try {
            i = us.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //跳转
        if (i > 0) {
            result.setCode(Constants.SUCCESS);
            result.setMessage("注册成功！");
            result.setData("注册成功！");
            result.setSuccess("true");
            json = gson.toJson(result);
            System.out.println("result json = "+json);

        } else {
            result.setCode(Constants.FAIL);
            result.setMessage("注册失败！");
            result.setData("注册失败！");
            result.setSuccess("false");
            json = gson.toJson(result);
            System.out.println("result json = "+json);
        }
        out.println(json);
        out.flush();
        out.close();
    }
}


