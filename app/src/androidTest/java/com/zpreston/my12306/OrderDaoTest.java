package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.bean.Train;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.dao.OrderDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;
import com.zpreston.my12306.daoImpl.OrderDaoImpl;
import com.zpreston.my12306.db.DbHelper;
import com.zpreston.my12306.util.Util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/7/21.
 */
/*---------------------------------OrderDao的测试-----------------------------------------------------------*/
public class OrderDaoTest extends ApplicationTestCase<Application> {
    private DbHelper dbHelper;

    public OrderDaoTest() {
        super(Application.class);
    }

    /*
    测试查询所有订单
    * */
    /*
    查询全部订单
    入参：email，用户邮箱
    出参：Order的List列表
    * */
    public void testQueryAllOrders() {
        String email = "775079852@qq.com";
        OrderDao orderDao = new OrderDaoImpl(getContext());
        List<Order> orders = orderDao.queryAllOrders(email);
        for (Order order : orders) {
            Util.myLog("testQueryAllOrders", order.toString());
        }
    }

    /*
    测试未支付订单
    * */
    /*
     未支付订单
    入参：email，用户邮箱
     出参：Order的List列表
     * */
    public void testQueryNotPaidOrders() {
        String email = "775079852@qq.com";
        OrderDao orderDao = new OrderDaoImpl(getContext());
        List<Order> orders = orderDao.queryNotPaidOrders(email);
        for (Order order : orders) {
            Util.myLog("testQueryNotPaidOrders", order.toString());
        }
    }

    /*
    测试
    查询已支付订单
    入参：email，用户邮箱
    出参：Order的List列表
    * */
    public void testQueryAlreadyPaidOrders() {
        String email = "775079852@qq.com";
        OrderDao orderDao = new OrderDaoImpl(getContext());
        List<Order> orders = orderDao.queryAlreadyPaidOrders(email);
        for (Order order : orders) {
            Util.myLog("testQueryAlreadyPaidOrders", order.toString());
        }
    }

    /*
    测试
    退票
    入参：email，用户邮箱,订单号orderNo，乘车人ID
    出参：状态码，1表示退票成功
    * */
    public void testReturnTicket() {
        String email = "775079852@qq.com";
        String orderNo = "201607212350";
        int contactId = 1;
        OrderDao orderDao = new OrderDaoImpl(getContext());
        orderDao.returnTicket(email, orderNo, contactId);
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
        int contactId = 3;
        String contactName = "玛丽好";
        String contactCardId = "440982199407152896";
        String contactPhone = "15627860608";
        int contactState = 0;
        Contact contact1 = new Contact(uid, contactId, contactName, contactCardId, contactPhone, contactState);
        contactList.add(contact1);

        uid = 1;
        contactId = 4;
        contactName = "曾培兴";
        contactCardId = "440982199403216829";
        contactPhone = "15627860601";
        contactState = 0;
        Contact contact2 = new Contact(uid, contactId, contactName, contactCardId, contactPhone, contactState);
        contactList.add(contact2);

        //构造Train对象

        int id = 3;
        String trainNo = "G1207";
        String startStationName = "北京";
        String endStationName = "广州";
        String startTime = "23:47";
        String arriveTime = "05:06";
        String startDate = "2016-08-21";
        int seats = 11;
        double price = 285.5;
        Train train = new Train(id, trainNo, startStationName, endStationName, startTime, arriveTime, startDate, seats, price);

        OrderDao orderDao = new OrderDaoImpl(getContext());
        orderDao.orderTickets(contactList, train);

        //查看OrderForm表，看看插入有没有成功
        testQueryAllOrders();
    }


    /*
    测试
    支付订单
    入参：用户ID，orderNo 订单号
    出参：状态码，1表示提交成功
    把订单状态修改为1，表示已支付
    * */
    public void testPayForOrder()
    {
        String email = "775079852@qq.com";
        String orderNo = "20160723125622";
        OrderDao orderDao = new OrderDaoImpl(getContext());
        orderDao.payForOrder(email, orderNo);
        testQueryAlreadyPaidOrders();
    }


    /*
    取消订单
    入参：email，用户邮箱,订单号orderNo
    出参：状态码，1表示退票成功
    * */
    public void testCancelOrder()
    {
        String email = "775079852@qq.com";
        String orderNo = "20160723011523";
        OrderDao orderDao = new OrderDaoImpl(getContext());
        orderDao.cancelOrder(email, orderNo);
    }


    /*
    获取同一订单的联系人
    入参：email，用户邮箱,订单号orderNo
    出参：List<Contact> contacts， 联系人列表
    * */
    public void testGetSameOrderContacts()
    {
        String email = "775079852@qq.com";
        String orderNo = "20160723011523";
        OrderDao orderDao = new OrderDaoImpl(getContext());
        List<Contact> contacts = orderDao.getSameOrderContacts(email, orderNo);
        for (Contact contact:contacts)
        {
            Util.myLog("testGetSameOrderContacts", contact.toString());
        }
    }


}
