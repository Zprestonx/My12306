package com.zpreston.my12306.dao;

import com.zpreston.my12306.bean.Order;

import java.util.List;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public interface OrderDao {
    /*
    查询全部订单
    入参：uid用户ID
    出参：Order的List列表
    * */
    List<Order> queryAllOrders(int uid);

    /*
    查询未支付订单
    入参：uid用户ID
    出参：Order的List列表
    * */
    List<Order> queryNotPaidOrders(int uid);

    /*
    查询已支付订单
    入参：uid用户ID
    出参：Order的List列表
    * */
    List<Order> queryAlreadyPaidOrders(int uid);

    /*
    退票
    入参：用户ID，订单号orderNo，乘车人ID
    出参：状态码，1表示退票成功
    * */
    int returnTicket(int uid, String orderNo, int contactId);
}
