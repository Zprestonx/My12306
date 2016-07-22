package com.zpreston.my12306.dao;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.bean.Train;

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

    /*
    车票预订，执行后状态都为未支付
    入参：乘车人列表（可能有多个人乘同一列车）,车次Train对象
    出参：订单号，orderNo
    * */
    String orderTickets(List<Contact> contactList, Train train);

    /*
    提交订单
    入参：用户ID，orderNo 订单号
    出参：状态码，1表示提交成功
    * */
    int submitOrder(int uid, String orderNo);
}
