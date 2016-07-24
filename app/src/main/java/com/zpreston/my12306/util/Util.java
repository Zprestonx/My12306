package com.zpreston.my12306.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/7/21.
 */
public class Util {
    private Context context;
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

    public Util(Context context)
    {
       this.context = context;
    }

    /*
    调用getEmail()方法前先创建Util对象，构造函数要传入当前的上下文
    * */
    public String getEmail()
    {
        SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        //第二个参数是缺省值，找不到的时候用这个
        String email = sp.getString("name", "");
        return email;
    }

    /*
    调用getPassword()方法前先创建Util对象，构造函数要传入当前的上下文
    * */
    public String getPassword()
    {
        SharedPreferences sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        //第二个参数是缺省值，找不到的时候用这个
        String password = sp.getString("pwd", "");
        return password;
    }


}
