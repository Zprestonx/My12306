package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.db.ContactHelper;

/**
 * Created by user on 2016/7/21.
 */
/*---------------------------------ContactDao的测试-----------------------------------------------------------*/
public class ContactDaoTest extends ApplicationTestCase<Application> {
    private ContactHelper contactHelper;
    public ContactDaoTest() {
        super(Application.class);
    }

}
