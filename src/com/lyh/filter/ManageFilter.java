package com.lyh.filter;

import com.lyh.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将ServletRequest类型强转为HttpServletRequest类型
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        if("admin".equals(user.getUsername()) && "admin".equals(user.getPassword()) ){
            //放行
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            //跳回登录界面
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
