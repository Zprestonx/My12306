package com.zpreston.my12306.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by preston on 2016/7/20.
 *
 */
public class ContactHelper extends SQLiteOpenHelper{
    private static final String DATABASENAME = "My12306.db";
    private static final int VERSION = 1;

    public ContactHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建Contact表
        String sql = "create table Contact(" +
                "uid integer not null," +
                "contactId integer not null," +
                "contactName varchar(20) not null," +
                "contactCardId varchar(20) not null," +
                "contactPhone varchar(12)," +
                "contactState integer" +
                ")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists Contact";
        db.execSQL(sql);

    }
}
