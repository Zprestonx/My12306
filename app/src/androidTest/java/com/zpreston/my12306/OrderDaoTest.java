package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.db.OrderHelper;

/**
 * Created by user on 2016/7/21.
 */
/*---------------------------------OrderDao的测试-----------------------------------------------------------*/
public class OrderDaoTest extends ApplicationTestCase<Application>{
    private OrderHelper orderHelper;

    public OrderDaoTest() {
        super(Application.class);
    }
}
