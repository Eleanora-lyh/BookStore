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
    /**
     * @Description 点击添加图书时 new book对象，同时将新书添加到页面中，使用重定向显示新的界面
     * @Param [req, resp]
     * @return void
     **/
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数，封装为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2、点击提交，添加到数据库
        bookService.addBook(book);
        //3、重定向到图书列表 /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list ");
    }
    protected void getBookInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取图书的编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2、查询图书的信息
        Book book = bookService.queryBookByID(id);
        //3、保存图书到request域中
        req.setAttribute("book",book);
        //4、请求转发到 /book/pages/manager/book_edit.jsp中
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2、修改图书信息
        bookService.updateBook(book);
        //3、重定向到list表单项
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list ");
    }
    /**
     * @Description 获取图书的id(String)后，按照id(int)删除图书，使用重定向显示新的界面
     * @Param [req, resp]
     * @return void
     **/
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数，action=delete id=book.getId()
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2、删除图书信息
        bookService.deleteById(id);
        //3、重定向到图书列表
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list ");
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