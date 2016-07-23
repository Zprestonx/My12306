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
    入参：email，用户邮箱
    出参：Order的List列表
    * */
    List<Order> queryAllOrders(String email);

   /*
    查询未支付订单
    入参：email，用户邮箱
    出参：Order的List列表
    * */
    List<Order> queryNotPaidOrders(String email);

    /*
    查询已支付订单
    入参：email，用户邮箱
    出参：Order的List列表
    * */
    List<Order> queryAlreadyPaidOrders(String email);

    /*
    退票
    入参：email，用户邮箱,订单号orderNo，乘车人ID
    出参：状态码，1表示退票成功
    * */
    int returnTicket(String email, String orderNo, int contactId);

    /*
    车票预订，执行后状态都为未支付
    入参：乘车人列表（可能有多个人乘同一列车）,车次Train对象
    出参：订单号，orderNo
    * */
    String orderTickets(List<Contact> contactList, Train train);

    /*
    支付订单
    入参：email，用户邮箱，orderNo 订单号
    出参：状态码，1表示提交成功
    * */
    int payForOrder(String email, String orderNo);

    /*
    取消订单
    入参：email，用户邮箱,订单号orderNo
    出参：状态码，1表示退票成功
    * */
    int cancelOrder(String email, String orderNo);

    /*
    获取同一订单的联系人
    入参：email，用户邮箱,订单号orderNo
    出参：List<Contact> contacts， 联系人列表
    * */
    List<Contact> getSameOrderContacts(String email, String orderNo);
}
