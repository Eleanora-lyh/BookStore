package com.lyh.web;

import com.lyh.pojo.Book;
import com.lyh.pojo.Page;
import com.lyh.service.BookService;
import com.lyh.service.impl.BookServiceImpl;
import com.lyh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * @Description 此类为处理web层的类：处理页面传递的参数，进而调用service层的方法实现具体的页面功能
 * @Param
 * @return
 **/
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    /**
     * @Description 点击添加时 <td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
     * 进入book_edit.jsp，如果是add <input type="hidden" name="action" value="${empty param.id?"add":"update"}"/>
     * 调用此方法，创建一个book对象，使用重定向/manager/bookServlet?action=list调用list方法，显示新的界面
     * @Param [req, resp]
     * @return void
     **/
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数，封装为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2、点击提交，添加到数据库
        bookService.addBook(book);
        //3、重定向到图书列表 /book/managers/bookServlet?action=list
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo+=1;
        resp.sendRedirect(req.getContextPath() + "/managers/bookServlet?action=page&pageNo="+ pageNo);
    }
    /**
     * @Description 点击某书的修改时<a href="managers/bookServlet?action=getBookInfo&id=${book.id}">修改</a>
     * 通过反射调用此方法，获取到该书的信息，显示在修改界面book_edit.jsp中
     * @Param [req, resp]
     * @return void
     **/
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
    /**
     * @Description 点击修改会先进入<a href="managers/bookServlet?action=getBookInfo&id=${book.id}">修改</a>
     * 跳转进book_edit.jsp中，在此界面<input type="hidden" name="action" value="${empty param.id?"add":"update"}"/>
     * 根据action在提交的时候选择调用update方法，获取参数得到新书，同时重定向到list方法
     * @Param [req, resp]
     * @return void
     **/
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //2、修改图书信息
        bookService.updateBook(book);
        //3、重定向到list表单项
        resp.sendRedirect(req.getContextPath() + "/managers/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    /**
     * @Description 点击删除时，<a class="deleteClass" href="managers/bookServlet?action=delete&id=${book.id}">
     *     调用此方法，获取参数得到图书id，根据id删除图书，并重定向到list方法
     * @Param [req, resp]
     * @return void
     **/
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数，action=delete id=book.getId()
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2、删除图书信息
        bookService.deleteById(id);
        //3、重定向到图书列表
        resp.sendRedirect(req.getContextPath() + "/managers/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    /**
     * @Description 点击图书管理时 <a href="manager/bookServlet?action=list">图书管理</a>
     * 就会调用此方法，查询数据库的全部图书信息得到books，进入新的界面/pages/manager/book_manager.jsp，显示表头，遍历books显示图书
     * @Param [req, resp]
     * @return void
     **/
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、通过bookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2、通过request域存储图书信息
        req.setAttribute("books",books);
        //3、请求转发到 /pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    /**
     * @Description 处理分页功能
     * @Param [req, resp]
     * @return void
     **/
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1、获取请求的参数 pageNo(默认第一页) pageSize(默认为4)
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2、调用bookService.page(pageNo,pageSize) 获取page对象
        Page<Book> page = bookService.page(pageNo,pageSize);

        page.setUrl("managers/bookServlet?action=page");
        //3、保存page对象到request域中
        req.setAttribute("page",page);
        //4、请求转发到book_manager页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
















