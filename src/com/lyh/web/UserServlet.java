package com.lyh.web;

import com.lyh.pojo.User;
import com.lyh.service.UserService;
import com.lyh.service.impl.UserServiceImpl;
import com.lyh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //(1)获取请求的参数
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //(2)判断用户名密码是否正确
        User loginUser = userService.login(user);
        if (loginUser == null) {
            req.setAttribute("username", user.getUsername());
            req.setAttribute("msg", "用户名或密码错误");
            //错误返回登录界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            //正确，进入登录成功界面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
//        System.out.println(user.getUsername() + " " +user.getPassword()+ " " + user.getEmail());
        String code = req.getParameter("code");
        //2、检查 验证码是否正确  === 写死,要求验证码为:abcde
        if ("abcde".equalsIgnoreCase(code)) {
            //3、检查 用户名是否可用
            if (userService.existsUsername(user.getUsername())) {//已存在，不可用
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());
                req.setAttribute("msg", "用户名[" + user.getUsername() + "]已存在!");
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //可用
                //调用Sservice保存到数据库
                userService.registUser(user);
                //跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.setAttribute("msg", "验证码错误");
//            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }
}
