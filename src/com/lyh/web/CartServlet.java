package com.lyh.web;

import com.google.gson.Gson;
import com.lyh.pojo.Book;
import com.lyh.pojo.Cart;
import com.lyh.pojo.CartItem;
import com.lyh.service.BookService;
import com.lyh.service.impl.BookServiceImpl;
import com.lyh.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    BookService bookService = new BookServiceImpl();
    /**
     * @Description 将指定商品加入到购物车
     **/
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //(1)获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //(2)调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookByID(id);
        //(3)把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //(4)调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //(4)最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());
        //(5)重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //(1)获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //(2)调用bookService.queryBookById(id):Book得到图书的信息
        Book book = bookService.queryBookByID(id);
        //(3)把图书信息，转换成为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //(4)调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //(4)最后一个添加的商品名称
        req.getSession().setAttribute("lastName", cartItem.getName());
        //(5)返回购物车总的商品数量和最后一个添加的商品名称
        Map<String,Object> resultMap = new HashMap<>();

        resultMap.put("totalCount",cart.getTotalCount());
        resultMap.put("lastName",cartItem.getName());

        Gson gson = new Gson();
        String resultMapJsonString = gson.toJson(resultMap);
        resp.getWriter().write(resultMapJsonString);
    }
    /**
     * @Description 将指定商品从购物车中移除
     **/
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 按照id删除购物车商品项
            cart.deleteItem(id);
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // 按照id删除购物车商品项
            cart.clear();
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 商品编号 、商品数量
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        // 获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            // 修改商品数量
            cart.updateCount(id,count);
            // 重定向回原来购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
