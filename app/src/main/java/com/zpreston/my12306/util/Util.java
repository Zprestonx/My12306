package com.zpreston.my12306.util;

import android.util.Log;

/**
 * Created by user on 2016/7/21.
 */
public class Util {
    //添加一个加密的方法，用来加密密码
    public static String encryption(String plaintext)
    {
       return plaintext;
    }

    //添加一个自定义的Log函数,方便调试
    public static void myLog(String tag, String str)
    {
        Log.e(tag, "*********************************"+tag+"Start*********");
        Log.e(tag, "*********************************"+str+"**************");
        Log.e(tag, "*********************************"+tag+"End**************");
    }


}
