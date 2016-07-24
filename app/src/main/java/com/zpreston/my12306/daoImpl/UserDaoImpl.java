package com.zpreston.my12306.daoImpl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zpreston.my12306.bean.User;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.db.DbHelper;
import com.zpreston.my12306.util.Util;

import java.text.SimpleDateFormat;

import javax.crypto.Cipher;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by preston on 2016/7/20.
 */
public class UserDaoImpl implements UserDao {
    private DbHelper dbHelper;

    public UserDaoImpl(Context context) {
        dbHelper = new DbHelper(context);
    }

    @Override
    public int queryUid(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select uid from User where email = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        if(cursor.moveToNext())
        {
            int uid = cursor.getInt(cursor.getColumnIndex("uid"));
            cursor.close();
            db.close();
            return uid;
        }
        else
        {
            cursor.close();
            db.close();
            return 0;
        }
    }

    @Override
    public int loginVerify(String email, String password) {
        //创建或打开一个只读数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
                db = dbHelper.getWritableDatabase();
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
    public int verifyPassword(String email, String password) {
        //获取可写的数据库对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //加密密码，和数据库中加密的密码比较
        String enPassword = Util.encryption(password);

        //判断旧密码是否正确
        String sql = "select uid from User where email=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{email, enPassword});

        if (cursor.moveToNext()) {
            //旧密码正确，在数据库中更新
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

    @Override
    /*
    根据邮箱,把密码取出来和输入的oldPassword比较，如果相同，且两次输入的新密码一样，则修改旧密码,最后返回0
    如果没找到密码，则oldPassword输入错误,返回0
    如果两次输入的新密码不一样，则返回2
    * */
    public int modifyPassword(String email, String newPassword, String checkNewPassword) {
        if (!newPassword.equals(checkNewPassword)) {
            //如果两次输入的新密码不一样，则返回2
            return 2;
        } else {
            //加密旧密码，和数据库中加密的密码比较
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String enPassword = Util.encryption(newPassword);
            String sql = "UPDATE User SET password = ? WHERE email = ?";
            //这个会返回什么？如果更新不成功呢？
            db.execSQL(sql, new String[]{enPassword, email});
            db.close();
            return 1;
        }
    }

    @Override
    public int logout(String email) {
        //暂时想不到要做什么
        return 1;
    }

    @Override
    public User getUserById(int uid) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
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
            int certificateType = cursor.getInt(cursor.getColumnIndex("certificateType"));
            String idCard = cursor.getString(cursor.getColumnIndex("idCard"));
            int passengerType = cursor.getInt(cursor.getColumnIndex("passengerType"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String lastLoginTime = cursor.getString(cursor.getColumnIndex("lastLoginTime"));
            int userStatus = cursor.getInt(cursor.getColumnIndex("userStatus"));

            user = new User(userId, email, password, userName, gender, certificateType, idCard, passengerType, phone, lastLoginTime, userStatus);


        }

        cursor.close();
        db.close();
        return user;
    }

    /*
    插入User记录
    用户是第一个联系人，所以同时插入一条记录到Contact表
    * */

    @Override
    public void insertUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "insert into User(email,password,userName,gender,certificateType,idCard, passengerType,phone,lastLoginTime,userStatus) values(?,?,?,?,?,?,?,?,?,?)";

        String email = user.getEmail();
        String password = user.getPassword();
        String userName = user.getUserName();
        int gender = user.getGender();
        int certificateType = user.getCertificateType();
        String idCard = user.getIdCard();
        int passengerType = user.getPassengerType();
        String phone = user.getPhone();
        String lastLoginTime = user.getLastLoginTime();
        int userStatus = user.getUserStatus();

        db.execSQL(sql, new String[]{email, password, userName, String.valueOf(gender), String.valueOf(certificateType), idCard, String.valueOf(passengerType), phone, lastLoginTime, String.valueOf(userStatus)});

        //先获取uid，查询User表中最大的uid值
        sql = "select uid from User where email=?";
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        if (cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndex("uid"));
            int contactId = 1;
            String contactName = userName;
            String contactCardId = idCard;
            String contactPhone = phone;
            int contactState = passengerType;
            //插入Contact
            sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(?,?,?,?,?,?)";
            db.execSQL(sql, new String[]{String.valueOf(uid), String.valueOf(contactId), contactName, contactCardId, contactPhone, String.valueOf(contactState)});
        }
        cursor.close();

    }

    @Override
    /*
    获取用户信息
    入参：email，用户邮箱
    出参：User对象
    * */
    public User getUserInfo(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        User user = null;

        String sql = "select * from User where email=?";
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        if (cursor.moveToNext()) {
            //User(int uid, String email, String password, String userName,
            //String gender, String idCard, String phone, String lastLoginTime, int userStatus) {
            int userId = cursor.getInt(cursor.getColumnIndex("uid"));
            String userEmail = cursor.getString(cursor.getColumnIndex("email"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String userName = cursor.getString(cursor.getColumnIndex("userName"));
            int gender = cursor.getInt(cursor.getColumnIndex("gender"));
            int certificateType = cursor.getInt(cursor.getColumnIndex("certificateType"));
            String idCard = cursor.getString(cursor.getColumnIndex("idCard"));
            int passengerType = cursor.getInt(cursor.getColumnIndex("passengerType"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String lastLoginTime = cursor.getString(cursor.getColumnIndex("lastLoginTime"));
            int userStatus = cursor.getInt(cursor.getColumnIndex("userStatus"));

            user = new User(userId, userEmail, password, userName, gender, certificateType, idCard, passengerType, phone, lastLoginTime, userStatus);
        }

        cursor.close();
        db.close();
        return user;
    }


    /*
    注册
    入参：User对象
    出参:状态码，code，1为成功
    * */
    @Override
    public int register(User user) {

        //获取User对象属性
        String email = user.getEmail();
        String password = user.getPassword();
        String userName = user.getUserName();
        int gender = user.getGender();
        int certificateType = user.getCertificateType();
        String idCard = user.getIdCard();
        int passengerType = user.getPassengerType();
        String phone = user.getPhone();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String lastLoginTime= simpleDateFormat.format(new java.util.Date());

        int userStatus = 1; //默认为1，可订票状态

        //插入
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "insert into User(email,password,userName,gender,certificateType,idCard, passengerType,phone,lastLoginTime,userStatus) values(?,?,?,?,?,?,?,?,?,?)";
        db.execSQL(sql, new String[]{email, password, userName, String.valueOf(gender), String.valueOf(certificateType), idCard, String.valueOf(passengerType), phone, lastLoginTime, String.valueOf(userStatus)});
        //在Contact表同时添加一条记录
        //先获取uid
        sql = "select uid from User where email=?";
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        if (cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndex("uid"));
            int contactId = 1;
            String contactName = userName;
            String contactCardId = idCard;
            String contactPhone = phone;
            int contactState = passengerType;
            //插入Contact
            sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(?,?,?,?,?,?)";
            db.execSQL(sql, new String[]{String.valueOf(uid), String.valueOf(contactId), contactName, contactCardId, contactPhone, String.valueOf(contactState)});
        }
        cursor.close();
        return 1;
    }
}
