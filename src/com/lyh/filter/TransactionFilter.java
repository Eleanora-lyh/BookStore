package com.lyh.filter;

import com.lyh.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            JdbcUtils.rollBackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);//将异常抛给Tomcat服务器，显示错误友好界面
        }
    }

    @Override
    public void destroy() {

    }
}
