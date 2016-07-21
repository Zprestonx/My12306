package com.zpreston.my12306;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.bean.User;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.daoImpl.UserDaoImpl;
import com.zpreston.my12306.db.DbHelper;
import com.zpreston.my12306.db.UserHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 *
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private DbHelper dbHelper;
    public ApplicationTest() {
        super(Application.class);
    }
    /*
    测试创建四张表
    * */
    public void testCreateAllTables()
    {
        dbHelper = new DbHelper(getContext());
        dbHelper.getWritableDatabase();
        Log.e(" testCreateAllTables", "************** testCreateAllTables");
    }
}