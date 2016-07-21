package com.zpreston.my12306;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.daoImpl.ContactDaoImpl;
import com.zpreston.my12306.db.ContactHelper;

import java.util.List;

/**
 * Created by user on 2016/7/21.
 *
 */
/*---------------------------------ContactDao的测试-----------------------------------------------------------*/
public class ContactDaoTest extends ApplicationTestCase<Application> {
    private ContactHelper contactHelper;
    public ContactDaoTest() {
        super(Application.class);
    }

    /*
    测试添加联系人
    修改用户的同时应更新Contact表，自己是自己的联系人
    * */
    public void testAddContact() {
        //设置成员的值
        int uid = 1;//属于1的联系人
        int contactId = 1;
        String contactName = "xhs";
        String contactCardId = "440982199410082896";
        String contactPhone = "15627860619";
        int contactState = 1;

        Contact contact = new Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState);
        ContactDao contactDao = new ContactDaoImpl(getContext());
        contactDao.addContact(contact);
    }

    /*
    测试显示所有联系人
    * */
    public void testQueryMyContacts() {
        int uid = 1;
        ContactDao contactDao = new ContactDaoImpl(getContext());
        List<Contact> contacts = contactDao.queryMyContacts(uid);
        for (Contact contact:contacts)
        {
            Log.e("testQueryMyContacts","*************"+contact.toString()+"*************");
        }

    }

    /*
    测试删除联系人
    * */
    public void testDeleteContact()
    {
        int uid = 1;
        int contactId = 2;
        ContactDao contactDao = new ContactDaoImpl(getContext());
        contactDao.deleteContact(uid, contactId);
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
}
