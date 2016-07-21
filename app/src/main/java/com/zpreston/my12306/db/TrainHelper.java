package com.zpreston.my12306.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by preston on 2016/7/20.
 *
 */
public class TrainHelper extends SQLiteOpenHelper{
    private static final String DATABASENAME = "My12306.db";
    private static final int VERSION = 1;

    public TrainHelper(Context context)
    {
        super(context, DATABASENAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建Train表
        //有double类型吗？
        String sql = "create table Train(" +
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists Train";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
