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
                "idCard varchar(20) not null,"+
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
                "id integer not null primary key," +
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
                "id integer not null primary key, " +
                "uid integer not null, " +
                "orderNo varchar(20) not null, " +
                "contactId integer not null, " +
                "trainNo varchar(20) not null, " +
                "orderPrice double not null, " +
                "orderState integer not null, " +
                "orderTime varchar(50) not null)";
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
