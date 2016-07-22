package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.bean.Train;
import com.zpreston.my12306.dao.OrderDao;
import com.zpreston.my12306.daoImpl.OrderDaoImpl;
import com.zpreston.my12306.db.OrderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/7/21.
 */
/*---------------------------------OrderDao的测试-----------------------------------------------------------*/
public class OrderDaoTest extends ApplicationTestCase<Application> {
    private OrderHelper orderHelper;

    public OrderDaoTest() {
        super(Application.class);
    }

    /*
    测试查询所有订单
    * */
    /*
    查询全部订单
    入参：uid用户ID
    出参：Order的List列表
    * */
    public void testQueryAllOrders() {
        int uid = 1;
        OrderDao orderDao = new OrderDaoImpl(getContext());
        List<Order> orders = orderDao.queryAllOrders(uid);
        for (Order order : orders) {
            Log.e("testQueryAllOrders", "********testQueryAllOrders***********" + order.toString());
        }

    }

    /*
    测试未支付订单
    * */
    /*
     未支付订单
     入参：uid用户ID
     出参：Order的List列表
     * */
    public void testQueryNotPaidOrders() {
        int uid = 1;
        OrderDao orderDao = new OrderDaoImpl(getContext());
        List<Order> orders = orderDao.queryAllOrders(uid);
        for (Order order : orders) {
            Log.e("testQueryAllOrders", "********testQueryAllOrders***********" + order.toString());
        }
    }

    /*
    测试
    查询已支付订单
    入参：uid用户ID
    出参：Order的List列表
    * */
    public void testQueryAlreadyPaidOrders() {
        int uid = 1;
        OrderDao orderDao = new OrderDaoImpl(getContext());
        List<Order> orders = orderDao.queryAllOrders(uid);
        for (Order order : orders) {
            Log.e("testQueryAllOrders", "********testQueryAllOrders***********" + order.toString());
        }
    }

    /*
    测试
    退票
    入参：用户ID，订单号orderNo，乘车人ID
    出参：状态码，1表示退票成功
    * */
    public void testReturnTicket() {
        int uid = 1;
        String orderNo = "G207";
        int contactId = 1;
        OrderDao orderDao = new OrderDaoImpl(getContext());
        orderDao.returnTicket(uid, orderNo, contactId);
        //查看订单状状态是否变为2了
        //直接调用测试的方法显示表的信息
        testQueryAllOrders();
    }

    /*
    车票预订，执行后状态都为未支付
    入参：乘车人列表（可能有多个人乘同一列车）,车次Train对象
    出参：订单号，orderNo
    * */
    public void testOrderTickets() {
        //构造contactList
        List<Contact> contactList = new ArrayList<>();
        int uid = 1;
        int contactId = 1;
        String contactName = "xhs";
        String contactCardId = "440982199410082894";
        String contactPhone = "15627860619";
        int contactState = 1;
        Contact contact1 = new Contact(uid, contactId, contactName, contactCardId, contactPhone, contactState);
        contactList.add(contact1);

        uid = 1;
        contactId = 2;
        contactName = "rgl";
        contactCardId = "440982199403216822";
        contactPhone = "15627860614";
        contactState = 2;
        Contact contact2 = new Contact(uid, contactId, contactName, contactCardId, contactPhone, contactState);
        contactList.add(contact2);

        //构造Train对象

        int id = 1;
        String trainNo = "G507";
        String startStationName = "北京";
        String endStationName = "广州";
        String startTime = "23:47";
        String arriveTime = "05:06";
        String startDate = "2016-08-21";
        int seats = 51;
        double price = 255.5;
        Train train = new Train(id, trainNo, startStationName, endStationName, startTime, arriveTime, startDate, seats, price);

        OrderDao orderDao = new OrderDaoImpl(getContext());
        orderDao.orderTickets(contactList, train);

        //查看OrderForm表，看看插入有没有成功
        testQueryAllOrders();
    }

    /*
    测试
    提交订单
    入参：用户ID，orderNo 订单号
    出参：状态码，1表示提交成功
    * */
    public void testSubmitOrder() {
        //参数
        int uid = 1;
        String orderNo = "201607220929";
        OrderDao orderDao = new OrderDaoImpl(getContext());
        orderDao.submitOrder(uid, orderNo);
    }
}
