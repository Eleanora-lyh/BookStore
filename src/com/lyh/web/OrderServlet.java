package com.lyh.web;

import com.lyh.pojo.Cart;
import com.lyh.pojo.Order;
import com.lyh.pojo.OrderItem;
import com.lyh.pojo.User;
import com.lyh.service.OrderService;
import com.lyh.service.impl.OrderServiceImpl;
import com.lyh.utils.JdbcUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先获取Cart购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        Integer userId = loginUser.getId();
//        调用orderService.createOrder(Cart,Userid);生成订单
        String orderId = orderService.createOrder(cart, userId);
        // 请求转发到/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
//      重定向是不支持保存到request域中的，所以要保存到session中
        req.getSession().setAttribute("orderId", orderId);

        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //(1)获取订单List
        List<Order> allOrder = orderService.showAllOrders();
        //(2)回传数据
        req.setAttribute("allOrder", allOrder);
        //(3)请求转发到order_manager.jsp（管理订单界面）
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }

    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //(1)获取参数 订单号
        String orderId = req.getParameter("orderId");
        //(2)根据订单号获取订单项列表
        List<OrderItem> orderItemList = orderService.showOrderDetail(orderId);
/*        System.out.println("获得的orderId为："+orderId);
        for (OrderItem orderItem : orderItemList) {
            System.out.println(orderItem);
        }*/
        //(3)回传数据
        req.setAttribute("orderItemList", orderItemList);
        //(4)返回页面
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req, resp);
    }

    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数 用户ID
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {//登录的情况
            //获取用户订单列表
            List<Order> orderList = orderService.showMyOrders(user.getId());
            //回传数据
            req.setAttribute("orderList", orderList);
            //返回页面
            req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
        } else {//未登录的情况下，返回页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //(1)获取订单号
        String orderId = req.getParameter("orderId");
        //(2)签收订单
        orderService.receiveOrder(orderId);
        //(3)获取用户
        User user = (User) req.getSession().getAttribute("user");
        //(4)根据用户id获取用户订单列表
        List<Order> orderList = orderService.showMyOrders(user.getId());
        //(5)回传数据
        req.setAttribute("orderList", orderList);
        //(5)返回页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //(1)获取订单号
        String orderId = req.getParameter("orderId");
        //(2)发货
        orderService.sendOrder(orderId);
        //(3)查新最新数据
        List<Order> allOrder = orderService.showAllOrders();
        //(4)回传数据
        req.setAttribute("allOrder", allOrder);
       /* System.out.println("获得的orderId为22："+orderId);
        for (Order order : allOrder) {
            System.out.println(order);
        }*/
        //(5)返回页面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }
}

