package com.zpreston.my12306.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by preston on 2016/7/19.
 *
 *
 */
public class OrderHelper extends SQLiteOpenHelper{
    private static final String DATABASENAME = "My12306.db";
    private static final int VERSION = 1;

    public OrderHelper(Context context)
    {
    super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建OrderForm表，因为Order是数据库关键字，不能同名
        String sql = "create table OrderForm(" +
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
        String sql = "drop table if exists OrderForm";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
