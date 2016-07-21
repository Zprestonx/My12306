package com.zpreston.my12306.dao;

/**
 * Created by user on 2016/7/20.
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
    修改密码
    入参：用户ID, 旧密码，新密码，第二次输入密码
    出参:状态码，1表示修改成功
    * */
    int modifyPassword(int uid, String oldPassword, String newPassword, String checkNewPassword);

    /*
    退出登录
     入参：用户ID
     出参：状态码，1表示退出成功
    * */
    int logout(int uid);

}
