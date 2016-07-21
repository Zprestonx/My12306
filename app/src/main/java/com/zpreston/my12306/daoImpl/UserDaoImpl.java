package com.zpreston.my12306.daoImpl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zpreston.my12306.bean.User;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.db.UserHelper;
import com.zpreston.my12306.util.Util;

import javax.crypto.Cipher;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by preston on 2016/7/20.
 */
public class UserDaoImpl implements UserDao {
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
        if (cursor.moveToNext()) {
            //将密码加密再比较
            String enPassword = Util.encryption(password);
            sql = "select uid from User where email=? and password=?";
            cursor = db.rawQuery(sql, new String[]{email, enPassword});
            if (cursor.moveToFirst()) {
                //验证通过
                //把最近一次登录时间记录下来
                db = userHelper.getWritableDatabase();
                sql = "update User set lastLoginTime=datetime(CURRENT_TIMESTAMP, 'localtime') where email = ?";
                db.execSQL(sql, new String[]{email});

                cursor.close();
                db.close();
                return 1;
            } else {
                //密码不正确
                cursor.close();
                db.close();
                return 0;
            }
        } else {
            //用户不存在
            cursor.close();
            db.close();
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
        if (!newPassword.equals(checkNewPassword)) {
            //如果两次输入的新密码不一样，则返回2
            return 2;
        } else {
            //获取可写的数据库对象
            SQLiteDatabase db = userHelper.getWritableDatabase();

            //加密旧密码，和数据库中加密的密码比较
            String enPassword = Util.encryption(oldPassword);

            //判断旧密码是否正确
            String sql = "select uid from User where uid=? and password=?";
            Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(uid), enPassword});

            if (cursor.moveToNext()) {
                //旧密码正确，在数据库中更新
                enPassword = Util.encryption(newPassword);
                sql = "UPDATE User SET password = ? WHERE uid = ?";
                //这个会返回什么？如果更新不成功呢？
                db.execSQL(sql, new String[]{enPassword, String.valueOf(uid)});

                cursor.close();
                db.close();
                return 1;
            } else {
                //如果没找到密码，则oldPassword输入错误,返回0
                cursor.close();
                db.close();
                return 0;
            }
        }
    }

    @Override
    public int logout(int uid) {
        //暂时想不到要做什么
        return 1;
    }

    @Override
    public User getUserById(int uid) {
        SQLiteDatabase db = userHelper.getReadableDatabase();
        User user = null;

        String sql = "select * from User where uid=?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(uid)});
        if (cursor.moveToNext()) {
            //User(int uid, String email, String password, String userName,
            //String gender, String idCard, String phone, String lastLoginTime, int userStatus) {
            int userId = cursor.getInt(cursor.getColumnIndex("uid"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String userName = cursor.getString(cursor.getColumnIndex("userName"));
            int gender = cursor.getInt(cursor.getColumnIndex("gender"));
            String idCard = cursor.getString(cursor.getColumnIndex("idCard"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String lastLoginTime = cursor.getString(cursor.getColumnIndex("lastLoginTime"));
            int userStatus = cursor.getInt(cursor.getColumnIndex("userStatus"));

            user = new User(userId, email, password, userName, gender, idCard, phone, lastLoginTime, userStatus);


        }

        cursor.close();
        db.close();
        return user;
    }

    /*
    插入User记录
    * */

    @Override
    public void insertUser(User user) {
        SQLiteDatabase db = userHelper.getWritableDatabase();
        String sql = "insert into User(email,password,userName,gender,idCard,phone,lastLoginTime,userStatus) values(?,?,?,?,?,?,?,?)";

        String email = user.getEmail();
        String password = user.getPassword();
        String userName = user.getUserName();
        int gender = user.getGender();
        String idCard = user.getIdCard();
        String phone = user.getPhone();
        String lastLoginTime = user.getLastLoginTime();
        int userStatus = user.getUserStatus();

        db.execSQL(sql, new String[]{email, password, userName, String.valueOf(gender),idCard,phone,lastLoginTime,String.valueOf(userStatus)});
    }
}
