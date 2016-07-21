package com.zpreston.my12306.daoImpl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.db.UserHelper;

/**
 * Created by preston on 2016/7/20.
 *
 */
public class UserDaoImpl implements UserDao{
    private UserHelper userHelper;

    public UserDaoImpl(Context context) {
        userHelper = new UserHelper(context);
    }

    @Override
    public int loginVerify(String email, String password) {
        //创建或打开一个只读数据库
        SQLiteDatabase db = userHelper.getReadableDatabase();
        String sql = "select uid from User where email=?";
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        if(cursor.moveToNext())
        {
            //将密码加密再比较
            String enPassword = password;
            sql = "select uid from User where email=? and password=?";
            cursor = db.rawQuery(sql, new String[]{email, enPassword});
            if(cursor.moveToNext())
            {
                //验证通过
                cursor.close();
                return 1;
            }
            else
            {
                //密码不正确
                cursor.close();
                return 0;
            }
        }
        else
        {
            //用户不存在
            cursor.close();
            return 2;
        }
    }

    @Override
    /*
    根据uid查找对密码,取出来和输入的oldPassword比较，如果相同，且两次输入的新密码一样，则修改旧密码,最后返回0
    如果没找到密码，则oldPassword输入错误,返回0
    如果两次输入的新密码不一样，则返回2
    * */
    public int modifyPassword(int uid, String oldPassword, String newPassword, String checkNewPassword) {
        //SQLiteDatabase db = userHelper

        return 0;
    }

    @Override
    public int logout(int uid) {
        return 0;
    }
}
