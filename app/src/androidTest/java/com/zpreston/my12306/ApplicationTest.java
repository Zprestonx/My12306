package com.zpreston.my12306;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.daoImpl.UserDaoImpl;
import com.zpreston.my12306.db.UserHelper;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private UserHelper userHelper;
    public ApplicationTest() {
        super(Application.class);
    }

    //测试创建User表
    public void testCreateUser()
    {
        userHelper = new UserHelper(getContext());
        userHelper.getWritableDatabase();
        Log.e("testCreateUser", "**************testCreateUser");
    }

    //测试UserDao中的loginVerify方法
    public void testLoginVerify()
    {
        Log.e("testLoginVerify", "**************testLoginVerify start");
        UserDaoImpl userDao = new UserDaoImpl(getContext());
        String email = "775079852@qq.com";
        String password = "123";
        int code = userDao.loginVerify(email, password);
        Log.e("testLoginVerify", "**************testLoginVerify "+code);
    }
    //
}