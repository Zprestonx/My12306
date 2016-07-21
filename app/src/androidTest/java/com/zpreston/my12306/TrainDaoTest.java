package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.db.TrainHelper;

/**
 * Created by user on 2016/7/21.
 */
/*---------------------------------TrainDao的测试-----------------------------------------------------------*/
public class TrainDaoTest extends ApplicationTestCase<Application>{
    private TrainHelper trainHelper;
    public TrainDaoTest() {
        super(Application.class);
    }

}
