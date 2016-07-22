package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.bean.Train;
import com.zpreston.my12306.dao.OrderDao;
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
        String orderNo = "201607212350";
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
        int contactId = 2;
        String contactName = "mlh";
        String contactCardId = "440982199407152896";
        String contactPhone = "15627860608";
        int contactState = 0;
        Contact contact1 = new Contact(uid, contactId, contactName, contactCardId, contactPhone, contactState);
        contactList.add(contact1);

        uid = 1;
        contactId = 3;
        contactName = "zpx";
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
        int uid = 1;
        String orderNo = "20160722033207";
        OrderDao orderDao = new OrderDaoImpl(getContext());
        orderDao.payForOrder(uid, orderNo);
        testQueryAlreadyPaidOrders();
    }


    /*
    生成本地日期和时间的字符串
    获取当前日期和时间，可能要修改为本地日期
    * */
    public void testCreateDateAndTime()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String orderTime = simpleDateFormat.format(new java.util.Date());
        Util.myLog("testCreateDateAndTime",orderTime);
    }
}
