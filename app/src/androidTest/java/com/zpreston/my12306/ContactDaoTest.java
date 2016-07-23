package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;
import com.zpreston.my12306.db.DbHelper;
import com.zpreston.my12306.util.Util;

import java.util.List;

/**
 * Created by user on 2016/7/21.
 *
 */
/*---------------------------------ContactDao的测试-----------------------------------------------------------*/
public class ContactDaoTest extends ApplicationTestCase<Application> {
    private DbHelper dbHelper;
    public ContactDaoTest() {
        super(Application.class);
    }

    /*
    测试添加联系人
    修改用户的同时应更新Contact表，自己是自己的联系人
    * */
    public void testAddContact() {
        String email = "775079852@qq.com";
        //设置成员的值
        int uid = 1;//属于1的联系人
        int contactId = 1;
        String contactName = "曾华生";
        String contactCardId = "440982199410082896";
        String contactPhone = "15627860613";
        int contactState = 1;

        Contact contact = new Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState);
        ContactDao contactDao = new ContactDaoImpl(getContext());
        contactDao.addContact(email,contact);
        testQueryMyContacts();
    }

    /*
    测试显示所有联系人
    * */
    public void testQueryMyContacts() {
        String email = "775079852@qq.com";
        ContactDao contactDao = new ContactDaoImpl(getContext());
        List<Contact> contacts = contactDao.queryMyContacts(email);
        for (Contact contact:contacts)
        {
            Util.myLog("testQueryMyContacts",contact.toString());
        }

    }

    /*
    测试删除联系人
    * */
    public void testDeleteContact()
    {
        String email = "775079852@qq.com";
        int contactId = 2;
        ContactDao contactDao = new ContactDaoImpl(getContext());
        contactDao.deleteContact(email, contactId);
        testQueryMyContacts();
    }

    /*
    测试更新联系人
    * */
    public void testUpdateContact()
    {
        //设置成员的值
        int uid = 1;//属于1的联系人
        int contactId = 2;
        String contactName = "lgx";
        String contactCardId = "440982199410082894";
        String contactPhone = "15627860619";
        int contactState = 3;
        Contact contact = new Contact(uid, contactId, contactName, contactCardId,contactPhone,contactState);

        ContactDao contactDao = new ContactDaoImpl(getContext());
        contactDao.updateContact(contact);
    }


    /*
    查询单个联系人
    入参：email用户邮箱，contactId联系人id
    出参：Contact 对象
    * */
    public void testQuerySingleContact()
    {
        String email = "775079852@qq.com";
        int contactId = 2;
        ContactDao contactDao = new ContactDaoImpl(getContext());
        Contact contact =  contactDao.querySingleContact(email, contactId);
        Util.myLog("testQuerySingleContact", contact.toString());
    }
}
