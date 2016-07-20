package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;

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
    }
}