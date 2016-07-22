package com.zpreston.my12306.daoImpl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Order;
import com.zpreston.my12306.bean.Train;
import com.zpreston.my12306.dao.OrderDao;
import com.zpreston.my12306.db.OrderHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public class OrderDaoImpl implements OrderDao {
    private OrderHelper orderHelper;

    //这是干什么用的？
    public OrderDaoImpl(Context context)
    {
        orderHelper = new OrderHelper(context);
    }
    @Override
    /*
    查询全部订单
    入参：uid用户ID
    出参：Order的List列表
    * */
    public List<Order> queryAllOrders(int uid) {
        //接收数据的列表
        List<Order> orders = new ArrayList<>();
        //获取数据库对象
        SQLiteDatabase db = orderHelper.getReadableDatabase();
        //SQL语句
        String sql = "select * from OrderForm where uid=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(uid)});

        //循环读取
        while (cursor.moveToNext())
        {
            //Order bean 的结构
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int userId = cursor.getInt(cursor.getColumnIndex("uid"));
            String orderNo = cursor.getString(cursor.getColumnIndex("orderNo"));
            int contactId = cursor.getInt(cursor.getColumnIndex("contactId"));
            String trainNo = cursor.getString(cursor.getColumnIndex("trainNo"));
            double orderPrice = cursor.getDouble(cursor.getColumnIndex("orderPrice"));
            int orderState = cursor.getInt(cursor.getColumnIndex("orderState"));
            String orderTime = cursor.getString(cursor.getColumnIndex("orderTime"));
            Order order = new Order(id, userId, orderNo, contactId, trainNo, orderPrice, orderState, orderTime);
            orders.add(order);
        }
        db.close();
        cursor.close();
        return orders;
    }

    @Override
    /*
    查询未支付订单,orderState = 0
    入参：uid用户ID
    出参：Order的List列表
    * */
    public List<Order> queryNotPaidOrders(int uid) {
        List<Order> orders = new ArrayList<>();

        //获取数据库对象
        SQLiteDatabase db = orderHelper.getReadableDatabase();
        //SQL语句
        String sql = "select * from OrderForm where uid=? and orderState=0";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(uid)});

        //循环读取
        while (cursor.moveToNext())
        {
            //Order bean 的结构
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int userId = cursor.getInt(cursor.getColumnIndex("uid"));
            String orderNo = cursor.getString(cursor.getColumnIndex("orderNo"));
            int contactId = cursor.getInt(cursor.getColumnIndex("contactId"));
            String trainNo = cursor.getString(cursor.getColumnIndex("trainNo"));
            double orderPrice = cursor.getDouble(cursor.getColumnIndex("orderPrice"));
            int orderState = cursor.getInt(cursor.getColumnIndex("orderState"));
            String orderTime = cursor.getString(cursor.getColumnIndex("orderTime"));
            Order order = new Order(id, userId, orderNo, contactId, trainNo, orderPrice, orderState, orderTime);
            orders.add(order);
        }
        db.close();
        cursor.close();
        return orders;
    }

    @Override
    /*
    查询已支付订单,orderState=1
    入参：uid用户ID
    出参：Order的List列表
    * */
    public List<Order> queryAlreadyPaidOrders(int uid) {
        List<Order> orders = new ArrayList<>();

        //获取数据库对象
        SQLiteDatabase db = orderHelper.getReadableDatabase();
        //SQL语句
        String sql = "select * from OrderForm where uid=? and orderState=1";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(uid)});

        //循环读取
        while (cursor.moveToNext())
        {
            //Order bean 的结构
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            int userId = cursor.getInt(cursor.getColumnIndex("uid"));
            String orderNo = cursor.getString(cursor.getColumnIndex("orderNo"));
            int contactId = cursor.getInt(cursor.getColumnIndex("contactId"));
            String trainNo = cursor.getString(cursor.getColumnIndex("trainNo"));
            double orderPrice = cursor.getDouble(cursor.getColumnIndex("orderPrice"));
            int orderState = cursor.getInt(cursor.getColumnIndex("orderState"));
            String orderTime = cursor.getString(cursor.getColumnIndex("orderTime"));
            Order order = new Order(id, userId, orderNo, contactId, trainNo, orderPrice, orderState, orderTime);
            orders.add(order);
        }
        db.close();
        cursor.close();
        return orders;
    }

    @Override
    /*
    退票
    入参：用户ID，订单号orderNo，乘车人ID
    出参：状态码，1表示退票成功
    把订单状态置为取消,2
    * */
    public int returnTicket(int uid, String orderNo, int contactId) {
        SQLiteDatabase db = orderHelper.getWritableDatabase();
        String sql = "update OrderForm set orderState=2 where uid=? and orderNo=? and contactId=?";
        db.execSQL(sql, new String[]{String.valueOf(uid),orderNo,String.valueOf(contactId)});
        db.close();
        return 1;
    }

    @Override
    /*
    车票预订
    入参：乘车人列表（可能有多个人乘同一列车）,车次Train对象
    出参：订单号，orderNo
    订单号按照订票时间生成，比如201607220757000 表示是2016年7月22日7点57分买的票,后加三位流水号
    循环读取联系人，把车次信息和乘车人信息插入到订单表中，
    需要取联系人的：uid，contactId
    orderNo自动生成,暂定为订票时间
    取train对象的 trainNo
    orderPrice这是订单总价 = train.getPrice * 乘车人数量
    orderState = 0 //表示未支付

    * */
    public String orderTickets(List<Contact> contactList, Train train) {
        //先取需要的数据
        String trainNo = train.getTrainNo();
        //订单价格为票的单价
        double orderPrice = train.getPrice();
        int orderState = 0;
        //获取当前日期和时间，可能要修改为本地日期
        SimpleDateFormat orderTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String orderTime = orderTimeFormat.format(new java.util.Date());
        //订单号和日期时间的值一样，格式不同

        SimpleDateFormat orderNoFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String orderNo = orderNoFormat.format(new java.util.Date());

        //获取数据库对象
        SQLiteDatabase db = orderHelper.getWritableDatabase();
        String sql = "insert into OrderForm(uid,orderNo,contactId,trainNo,orderPrice,orderState,orderTime) " +
                "values(?,?,?,?,?,?,?)";
        for(Contact contact:contactList)
        {
            int uid = contact.getUid();
            int contactId = contact.getContactId();
            db.execSQL(sql, new String[]{String.valueOf(uid),orderNo,String.valueOf(contactId),trainNo,String.valueOf(orderPrice),String.valueOf(orderState),orderTime});
        }
        return orderNo;
    }

    /*
    支付订单
    入参：用户ID，orderNo 订单号
    出参：状态码，1表示提交成功
    把订单状态修改为1，表示已支付
    * */
    @Override
    public int payForOrder(int uid, String orderNo) {
        SQLiteDatabase db = orderHelper.getWritableDatabase();
        String sql = "update OrderForm set orderState=1 where uid=? and orderNo=?";
        db.execSQL(sql, new String[]{String.valueOf(uid),orderNo});
        db.close();
        return 1;
    }
}
