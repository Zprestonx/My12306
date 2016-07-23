package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.bean.User;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.daoImpl.UserDaoImpl;
import com.zpreston.my12306.db.DbHelper;
import com.zpreston.my12306.util.Util;

/**
 * Created by user on 2016/7/21.
 */
/*---------------------------------UserDao的测试-----------------------------------------------------------*/
public class UserDaoTest extends ApplicationTestCase<Application> {
    private DbHelper dbHelper;

    public UserDaoTest() {
        super(Application.class);
    }

    /*
    测试queryUid
    查询uid
    入参：email
    出参：uid ,如果uid为0说明没获取成功
    * */
    public void testQueryUid()
    {
        String email = "775079852@qq.com";
        UserDao userDao = new UserDaoImpl(getContext());
        int uid = userDao.queryUid(email);
        Util.myLog("testQueryUid",String.valueOf(uid));
    }

    //测试UserDao中的loginVerify接口
    /*
    测试结果
    账号密码正确时，返回1
    密码不正确时，返回0
    账号不存在时，返回2
    测试通过
    * */
    public void testLoginVerify() {
        UserDao userDao = new UserDaoImpl(getContext());
        String email = "775079852@qq.com";
        String password = "567";
        int code = userDao.loginVerify(email, password);
        Log.e("testLoginVerify", "**************testLoginVerify " + code);
    }


    //测试UserDao中的getUserById接口
    //测试通过
    public void testGetUserById() {
        UserDao userDao = new UserDaoImpl(getContext());
        int uid = 1;
        User user = userDao.getUserById(uid);
        Log.e("testGetUserById", "**************" + user.toString());
    }

    /*
    测试 验证密码
    * */

    public void testVerifyPassword() {
        String email = "775079852@qq.com";
        String password = "234";
        UserDao userDao = new UserDaoImpl(getContext());
        int code = userDao.verifyPassword(email, password);
        Util.myLog("testVerifyPassword",String.valueOf(code));
    }

    //测试UserDao中的modifyPassword接口
    //测试通过
    public void testModifyPassword() {
        //输入 int uid, String oldPassword, String newPassword, String checkNewPassword
        String email = "775079852@qq.com";
        String newPassword = "123";
        String checkNewPassword = "123";

        UserDao userDao = new UserDaoImpl(getContext());
        int code = userDao.modifyPassword(email, newPassword, checkNewPassword);
        Util.myLog("testModifyPassword", String.valueOf(code));
    }

    /*
    测试 insertUser
    * */
    public void testInsertUser() {
        int uid = 1;
        String email = "123499@qq.com";
        String password = "123";
        String userName = "xxx";
        int gender = 1;
        int certificateType = 0;
        String idCard = "440982199410082894";
        int passengerType = 0;
        String phone = "15627860619";
        String lastLoginTime = "2016-07-21 17:09";
        int userStatus = 1;
        User user = new User(uid, email, password, userName, gender, certificateType, idCard, passengerType, phone, lastLoginTime, userStatus);

        UserDao userDao = new UserDaoImpl(getContext());
        userDao.insertUser(user);
    }

    /*
    获取用户信息
    入参：email，用户邮箱
    出参：User对象
    * */
    public void testGetUserInfo()
    {
        String email = "775079852@qq.com";
        UserDao userDao = new UserDaoImpl(getContext());
        User user = userDao.getUserInfo(email);
        Util.myLog("testGetUserInfo", user.toString());
    }
}
