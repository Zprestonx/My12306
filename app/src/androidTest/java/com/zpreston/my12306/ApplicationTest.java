package com.zpreston.my12306;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.bean.User;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.daoImpl.UserDaoImpl;
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
        Log.e("testCreateUser", "**************testCreateUser");
    }

    //测试UserDao中的loginVerify接口
    /*
    测试结果
    账号密码正确时，返回1
    密码不正确时，返回0
    账号不存在时，返回2
    测试通过
    * */
    public void testLoginVerify()
    {
        UserDaoImpl userDao = new UserDaoImpl(getContext());
        String email = "775079852@qq.com";
        String password = "567";
        int code = userDao.loginVerify(email, password);
        Log.e("testLoginVerify", "**************testLoginVerify "+code);
    }


    //测试UserDao中的getUserById接口
    //测试通过
    public void testGetUserById()
    {
        UserDaoImpl userDao = new UserDaoImpl(getContext());
        int uid = 1;
        User user = userDao.getUserById(uid);
        Log.e("testGetUserById", "**************"+user.toString());
    }

    //测试UserDao中的modifyPassword接口
    //测试通过
    public void testModifyPassword()
    {
        //输入 int uid, String oldPassword, String newPassword, String checkNewPassword
        int uid = 1;
        String oldPassword = "123";
        String newPassword = "567";
        String checkNewPassword = "567";

        UserDaoImpl userDao = new UserDaoImpl(getContext());
        int code = userDao.modifyPassword(uid, oldPassword, newPassword, checkNewPassword);
        Log.e("testModifyPassword", "**************testModifyPassword"+code);
    }
}