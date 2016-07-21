package com.zpreston.my12306.daoImpl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.db.ContactHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public class ContactDaoImpl implements ContactDao {
    private ContactHelper contactHelper;

    @Override
    /*
    根据uid查询所有联系人，要用cursor
    * */
    public List<Contact> queryMyContacts(int uid) {
        List<Contact> contacts = new ArrayList<Contact>();
        //获取只读数据库对象
        SQLiteDatabase db = contactHelper.getReadableDatabase();
        //SQL语句
        String sql = "select * from Contact where uid = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(uid)});

        while (cursor.moveToNext())
        {
            //Contact bean 的结构
            int userId = cursor.getInt(cursor.getColumnIndex("uid"));
            int contactId = cursor.getInt(cursor.getColumnIndex("contactId"));
            String contactName = cursor.getString(cursor.getColumnIndex("contactName"));
            String contactCardId = cursor.getString(cursor.getColumnIndex("contactCardId"));
            String contactPhone = cursor.getString(cursor.getColumnIndex("contactPhone"));
            int contactState = cursor.getInt(cursor.getColumnIndex("contactState"));

            Contact contact = new Contact(userId, contactId, contactName, contactCardId, contactPhone, contactState);
            contacts.add(contact);
        }
        cursor.close();
        return contacts;
    }

    @Override
    public int addContact(Contact contact) {
        return 0;
    }

    @Override
    public int deleteContact(int contactId) {
        return 0;
    }

    @Override
    public int updateContact(Contact contact) {
        return 0;
    }
}
