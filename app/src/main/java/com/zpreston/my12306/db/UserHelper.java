package com.zpreston.my12306.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public class UserHelper extends SQLiteOpenHelper{
    private static final String DATABASENAME = "My12306.db"; //数据库名称
    private static final int VERSION = 1; //版本

    public UserHelper(Context context) {
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists User";
        db.execSQL(sql);
    }
}
