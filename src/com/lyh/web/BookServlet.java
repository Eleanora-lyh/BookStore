package com.lyh.web;

import com.lyh.pojo.Book;
import com.lyh.service.BookService;
import com.lyh.service.impl.BookServiceImpl;
import com.lyh.utils.WebUtils;
import org.apache.commons.beanutils.LazyDynaList;
import org.apache.commons.collections.ArrayStack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数，封装为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2、点击提交，添加到数据库
        bookService.addBook(book);
        //3、返回manager/bookServlet?action=list程序，执行list显示界面
        String s1 = "/manager/bookServlet?action=list";
        String s2 = req.getContextPath() + "/manager/bookServlet?action=list";
        System.out.println(s1);
        System.out.println(s2);
        resp.sendRedirect(s2);

    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、通过bookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2、通过request域存储图书信息
        req.setAttribute("books",books);
        //3、请求转发到 /pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
