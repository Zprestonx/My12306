package com.zpreston.my12306.dao;

import com.zpreston.my12306.bean.User;

/**
 * Created by user on 2016/7/20.
 *
 *
 */

public interface UserDao {

    /*
    登录验证
    入参：邮箱账号 email,密码
    出参，int类型状态码，0表示用户不存在，1验证通过，2密码错误
     */
    int loginVerify(String email, String password);

    /*
    验证密码是否正确
    入参：uid，密码
    出参：1表示密码正确
    * */
    int verifyPassword(int uid, String oldPassword);

    /*
    修改密码
    入参：用户ID, 新密码，第二次输入密码
    出参:状态码，1表示修改成功, 2两次输入的密码不一样
    * */
    int modifyPassword(int uid, String newPassword, String checkNewPassword);

    /*
    退出登录
     入参：用户ID
     出参：状态码，1表示退出成功
    * */
    int logout(int uid);

    /*
    获取User表的某条记录，暂用作测试
    入参：用户ID
    出参：User对象
    * */
    User getUserById(int uid);

    /*
    插入记录，暂用作测试
    入参：User对象
    出参:void
    * */
    void insertUser(User user);
}
