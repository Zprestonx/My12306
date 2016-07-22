package com.zpreston.my12306.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by preston on 2016/7/19.
 *
 *
 */
public class DbHelper extends SQLiteOpenHelper{
    private static final String DATABASENAME = "My12306.db";
    private static final int VERSION = 1;

    public DbHelper(Context context)
    {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建User表
        String sql = "create table User(uid integer primary key autoincrement,"+
                "email varchar(50) not null,"+
                "password varchar(50) not null,"+
                "userName varchar(20) not null,"+
                "gender integer not null,"+
                "certificateType integer not null,"+
                "idCard varchar(20) not null,"+
                "passengerType integer not null,"+
                "phone varchar(11) not null,"+
                "lastLoginTime varchar(20),"+
                "userStatus integer"+
                ")";
        db.execSQL(sql);

        //创建Contact表
        sql = "create table Contact(" +
                "uid integer not null, " +
                "contactId integer not null," +
                "contactName varchar(20) not null," +
                "contactCardId varchar(20) not null," +
                "contactPhone varchar(12)," +
                "contactState integer" +
                ")";
        db.execSQL(sql);

        //创建Train表
        //有double类型吗？
        sql = "create table Train(" +
                "id integer not null primary key autoincrement," +
                "trainNo varchar(10) not null," +
                "startStationName varchar(30) not null," +
                "endStationName varchar(30) not null," +
                "startTime varchar(20) not null," +
                "arriveTime varchar(20) not null," +
                "startDate varchar(20) not null," +
                "seats integer not null," +
                "price double not null" +
                ")";
        db.execSQL(sql);

        //创建OrderForm表
        sql = "create table OrderForm(" +
                "id integer not null primary key autoincrement, " +
                "uid integer not null, " +
                "orderNo varchar(20) not null, " +
                "contactId integer not null, " +
                "trainNo varchar(20) not null, " +
                "orderPrice double not null, " +
                "orderState integer not null, " +
                "orderTime varchar(50) not null)";
        db.execSQL(sql);


        //插入一些数据
        //User表
        sql = "insert into User(email,password,userName,gender,certificateType,idCard,passengerType,phone,lastLoginTime,userStatus) " +
                "values('775079852@qq.com','123','xhs','1',0,'440982199410082894',0,'15627860619','2016-07-21 23:40', 1)";
        db.execSQL(sql);
        sql = "insert into User(email,password,userName,gender,certificateType,idCard,passengerType,phone,lastLoginTime,userStatus) values" +
                "('15627860619@qq.com','666','zpx','1',0,'440982199408218897',0,'15627860613','2016-07-21 11:40', 2)";
        db.execSQL(sql);

        //Contact表
        sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(1,1,'xhs','440982199410082894','15627860619',1)";
        db.execSQL(sql);
        sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(1,2,'rgl','440982199403216822','15627860614',2)";
        db.execSQL(sql);

        //Train表
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G507','北京','广州','23:47','05:06','2016-08-21',51,255.5)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G2312','武汉','云南','12:07','08:21','2016-08-11',11,155.0)";
        db.execSQL(sql);

        //OrderForm
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(1,'201607212350',1,'G507',511,1,'2016-07-21 12:50')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(2,'201607218129',1,'G507',217,1,'2016-07-31 11:32')";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists User";
        db.execSQL(sql);
        sql = "drop table if exists Contact";
        db.execSQL(sql);
        sql = "drop table if exists Train";
        db.execSQL(sql);
        sql = "drop table if exists OrderForm";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
