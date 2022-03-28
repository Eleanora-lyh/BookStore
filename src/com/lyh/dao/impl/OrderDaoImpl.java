package com.lyh.dao.impl;

import com.lyh.dao.OrderDao;
import com.lyh.pojo.Book;
import com.lyh.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDAO implements OrderDao {

    @Override
    //将订单信息order插入到t_order
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    //查询全部订单
    public List<Order> queryOrders() {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    //根据订单号修改订单状态
    public void changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set `status`=? where order_id = ? ";
        update(sql,status,orderId);
    }

    @Override
    //用户根据orderId查询订单明细
    public List<Order> queryOrderByUserId(Integer userId) {
        String sql = "select `order_id` as orderId,`create_time` as createTime,`price`,`status`,`user_id` as userId from t_order where user_id = ?";
        return queryForList(Order.class,sql,userId);
    }
}
