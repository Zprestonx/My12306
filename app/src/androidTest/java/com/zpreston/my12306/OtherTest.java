package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.zpreston.my12306.util.Util;

import java.text.SimpleDateFormat;

/**
 * Created by user on 2016/7/23.
 */
public class OtherTest extends ApplicationTestCase<Application>{
    public OtherTest() {
        super(Application.class);
    }
    /*
    生成本地日期和时间的字符串
    获取当前日期和时间，可能要修改为本地日期
    * */
    public void testCreateDateAndTime()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String orderTime = simpleDateFormat.format(new java.util.Date());
        Util.myLog("testCreateDateAndTime",orderTime);
    }
}
