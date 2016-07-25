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
                "values('775079852@qq.com','123','曾华生',1,0,'440982199410082894',0,'15627860619','2016-07-21 23:40', 1)";
        db.execSQL(sql);
        sql = "insert into User(email,password,userName,gender,certificateType,idCard,passengerType,phone,lastLoginTime,userStatus) values" +
                "('Zprestonx@gmail.com','666','郑佩鑫',1,0,'440982199408218897',0,'15627863613','2016-07-21 11:40', 2)";
        db.execSQL(sql);

        //Contact表
        sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(1,1,'123','123','123',1)";
        db.execSQL(sql);
        sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(1,2,'马丽豪','440982199402129312','15627863502',1)";
        db.execSQL(sql);
        sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(1,3,'钟淼君','440982199412229316','15627864521',1)";
        db.execSQL(sql);
        sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(1,4,'王露敷','440982199411829317','15627868509',1)";
        db.execSQL(sql);
        sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(1,5,'蔡文达','440982199410889316','15627868512',1)";
        db.execSQL(sql);
        sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(1,6,'卢学森','440982199407029319','15627863201',1)";
        db.execSQL(sql);
        sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(2,1,'郑佩鑫','440982199403216822','15627860614',0)";
        db.execSQL(sql);
        sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(2,2,'木木木','440982199403388382','15627824383',0)";
        db.execSQL(sql);

        //Train表
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G507','北京','广州','23:47','05:06','2016-07-25',51,255.5)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G2312','广州','北京','12:07','08:21','2016-07-28',11,155.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('K666','广州','北京','11:01','15:21','2016-07-26',18,355.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('K599','北京','广州','05:14','11:12','2016-07-27',9,255.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G71','北京','广州','07:40','17:14','2016-07-27',19,285.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G72','广州','北京','08:27','18:21','2016-07-27',0,235.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('Z202','广州','北京','08:50','06:40','2016-07-27',8,218.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('K528','广州','上海','07:48','04:14','2016-07-27',8,208.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G86','广州','上海','08:00','14:50','2016-07-27',7,218.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G1302','广州','上海','11:23','19:58','2016-07-26',19,198.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('K4160','广州','上海','14:35','08:40','2016-07-28',58,236.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G85','上海','广州','08:00','14:51','2016-07-27',9,248.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('T169','上海','广州','10:23','06:40','2016-07-27',8,212.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G1303','上海','广州','14:10','19:03','2016-07-26',8,251.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('K4150','上海','广州','15:25','05:30','2016-07-28',8,201.0)";
        db.execSQL(sql);
        sql = "insert into Train(trainNo,startStationName,endStationName,startTime,arriveTime,startDate,seats,price) values('G1305','上海','广州','14:40','22:55','2016-07-28',8,286.0)";
        db.execSQL(sql);



        //OrderForm
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(1,'201607212351',1,'G507',255.5,0,'2016-07-21 12:50')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(2,'201607218129',3,'G507',255.5,1,'2016-07-31 11:32')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(2,'201607218129',2,'G507',255.5,1,'2016-07-31 11:32')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(1,'201607218130',3,'G507',255.5,0,'2016-07-31 11:32')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(2,'201607218131',5,'G2322',155.0,0,'2016-07-30 10:32')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(2,'201607218131',6,'G2322',155.0,0,'2016-07-30 10:32')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(1,'201607218132',4,'K666',355.0,1,'2016-07-31 11:32')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(1,'201607222350',1,'G507',235.5,0,'2016-07-22 12:50')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(1,'201607222350',2,'G2312',256,0,'2016-07-24 10:12')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(1,'201607222350',3,'G2312',256,0,'2016-07-21 12:10')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(1,'201607222350',4,'G2312',256,0,'2016-07-22 12:50')";
        db.execSQL(sql);
        sql = "insert into OrderForm(uid, orderNo,contactId,trainNo,orderPrice,orderState,orderTime) values(1,'201607222350',5,'G2312',256,0,'2016-07-21 12:38')";
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
