package com.zpreston.my12306;

        import android.app.Application;
        import android.test.ApplicationTestCase;
        import android.util.Log;

        import com.zpreston.my12306.bean.User;
        import com.zpreston.my12306.dao.UserDao;
        import com.zpreston.my12306.daoImpl.UserDaoImpl;
        import com.zpreston.my12306.db.UserHelper;

/**
 * Created by user on 2016/7/21.
 *
 */
/*---------------------------------UserDao的测试-----------------------------------------------------------*/
public class UserDaoTest extends ApplicationTestCase<Application>{
    private UserHelper userHelper;
    public UserDaoTest() {
        super(Application.class);
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
        UserDao userDao = new UserDaoImpl(getContext());
        String email = "775079852@qq.com";
        String password = "567";
        int code = userDao.loginVerify(email, password);
        Log.e("testLoginVerify", "**************testLoginVerify "+code);
    }


    //测试UserDao中的getUserById接口
    //测试通过
    public void testGetUserById()
    {
        UserDao userDao = new UserDaoImpl(getContext());
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

        UserDao userDao = new UserDaoImpl(getContext());
        int code = userDao.modifyPassword(uid, oldPassword, newPassword, checkNewPassword);
        Log.e("testModifyPassword", "**************testModifyPassword"+code);
    }

    /*
    测试 insertUser
    * */
    public void testInsertUser()
    {
        int uid = 1;
        String email = "775079852@qq.com";
        String password = "123";
        String userName = "xhs";
        int gender = 1;
        String idCard = "440982199410082894";
        String phone = "15627860619";
        String lastLoginTime = "2016-07-21 17:09";
        int userStatus = 1;
        User user = new User(uid,email,password,userName,gender,idCard,phone,lastLoginTime,userStatus);

        UserDao userDao = new UserDaoImpl(getContext());
        userDao.insertUser(user);
    }
}
