package com.zpreston.my12306.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by preston on 2016/7/20.
 *
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
        insertTrain(db);
    }
    private void insertTrain(SQLiteDatabase db){
        String sql="insert into Train values" +
                "(1,'K528','广州','上海','07:48','20:26','2016-07-25',50,500)," +
                "(2,'D937','广州','上海','19:30','11:25','2016-07-25',50,500)," +
                "(3,'G1301','上海','广州','15:43','22:54','2016-07-25',50,500)," +
                "(4,'K4159','上海','广州','19:30','11:25','2016-07-25',50,500)," +
                "(5,'C7001','广州','深圳','06:00','07:26','2016-07-25',50,500)," +
                "(6,'G6201','广州','深圳','07:00','07:30','2016-07-25',50,500)," +
                "(7,'G6012','深圳','广州','07:10','07:46','2016-07-25',50,500)," +
                "(8,'G6164','深圳','广州','07:48','08:17','2016-07-25',50,500)," +
                "(9,'G4682','广州','杭州','07:28','16:22','2016-07-25',50,500)," +
                "(10,'K4160','广州','杭州','14:35','12:06','2016-07-25',50,500)," +
                "(11,'G1301','杭州','广州','11:30','19:03','2016-07-25',50,500)," +
                "(12,'K209','杭州','广州','18:38','14:58','2016-07-25',50,500)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists Train";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
