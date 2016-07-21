package com.zpreston.my12306.daoImpl;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.bean.Train;
import com.zpreston.my12306.dao.OrderDao;

import java.util.List;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> queryAllOrders(int uid) {
        return null;
    }

    @Override
    public List<Order> queryNotPaidOrders(int uid) {
        return null;
    }

    @Override
    public List<Order> queryAlreadyPaidOrders(int uid) {
        return null;
    }

    @Override
    public int returnTicket(int uid, String orderNo, int contactId) {
        return 0;
    }

    @Override
    public String orderTickets(List<Contact> contactList, Train train) {
        return null;
    }

    @Override
    public int submitOrder(int uid, String orderNo) {
        return 0;
    }
}
