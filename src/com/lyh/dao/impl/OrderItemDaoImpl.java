package com.lyh.dao.impl;

import com.lyh.dao.OrderItemDao;
import com.lyh.pojo.Book;
import com.lyh.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDAO implements OrderItemDao {
    @Override
    //将订单项保存到t_order_item，返回插入商品的数量
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice()
                ,orderItem.getOrderId());
    }

    @Override
    //根据orderId查询订单项明细（一个订单号可能有多个商品），返回订单项列表
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select `id`,`name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where order_id = ?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
