package com.lyh.service.impl;

import com.lyh.dao.BookDao;
import com.lyh.dao.OrderDao;
import com.lyh.dao.OrderItemDao;
import com.lyh.dao.impl.BookDaoImpl;
import com.lyh.dao.impl.OrderDaoImpl;
import com.lyh.dao.impl.OrderItemDaoImpl;
import com.lyh.pojo.*;
import com.lyh.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    /**
     * @Description 根据购物车和用户id生成订单
     * @return 订单号
     **/
    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 订单号===唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        // 创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
        // 保存订单
        orderDao.saveOrder(order);
        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            // 转换为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 更新库存和销量
            Book book = bookDao.queryBookByID(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount() );
            book.setStock( book.getStock() - cartItem.getCount() );
            bookDao.updateBook(book);
        }
        // 清空购物车
        cart.clear();
        return orderId;
    }

    /**
     * @Description 管理员可以查询全部订单，返回订单列表
     **/
    @Override
    public List<Order> showAllOrders() {
        List<Order> orderList = new ArrayList<>();
        orderList = orderDao.queryOrders();
        return orderList;
    }
    /**
     * @Description 发货：根据订单编号修改订单状态
     **/
    @Override
    public void sendOrder(String orderId) {
        //0表示未发货，改为1表示已发货
        orderDao.changeOrderStatus(orderId,1);
    }
    /**
     * @Description 管理员/用户可以根据 orderId 查询订单明细，即订单项列表
     * @return 返回订单表
     **/
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems = orderItemDao.queryOrderItemByOrderId(orderId);
        return orderItems;
    }
    /**
     * @Description 用户根据 userId 查看我的订单（可能是多个订单）
     * @return 返回订单列表
     **/
    @Override
    public List<Order> showMyOrders(Integer userId) {
        List<Order> orderList = new ArrayList<>();
        orderList = orderDao.queryOrderByUserId(userId);
        return orderList;
    }
    /**
     * @Description 根据订单编号签收订单即确认收货
     **/
    @Override
    public void receiveOrder(String orderId) {
        //1表示已发货，改为2表示已签收
        orderDao.changeOrderStatus(orderId,2);
    }
}
