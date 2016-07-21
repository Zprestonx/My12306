package com.zpreston.my12306.daoImpl;

import android.content.Context;
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

    public ContactDaoImpl(Context context) {
        contactHelper = new ContactHelper(context);
    }
    @Override
    /*
    根据uid查询所有联系人，要用cursor
    * */
    public List<Contact> queryMyContacts(int uid) {
        List<Contact> contacts = new ArrayList<>();
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
    /*
    添加联系人
    入参：Contact对象
    出参：状态码，1表示添加成功，0表示添加失败
    * */
    public int addContact(Contact contact) {
        //获取成员的值
        int uid = contact.getUid();
        int contactId = contact.getContactId();
        String contactName = contact.getContactName();
        String contactCardId = contact.getContactCardId();
        String contactPhone = contact.getContactPhone();
        int contactState = contact.getContactState();

        SQLiteDatabase db = contactHelper.getWritableDatabase();
        String sql = "insert into Contact values(?,?,?,?,?,?)";

        //怎么判断执行错误？
        db.execSQL(sql, new String[]{String.valueOf(uid),String.valueOf(contactId),contactName,contactCardId,contactPhone,String.valueOf(contactState)});
        return 1;
    }

    @Override
    public int deleteContact(int uid,int contactId) {
        SQLiteDatabase db = contactHelper.getWritableDatabase();
        String sql = "delete from Contact where uid=? and contactId=?";
        db.execSQL(sql, new String[]{String.valueOf(uid), String.valueOf(contactId)});
        return 1;
    }

    @Override
    public int updateContact(Contact contact) {
        //获取成员的值
        int uid = contact.getUid();
        int contactId = contact.getContactId();
        String contactName = contact.getContactName();
        String contactCardId = contact.getContactCardId();
        String contactPhone = contact.getContactPhone();
        int contactState = contact.getContactState();

        SQLiteDatabase db = contactHelper.getWritableDatabase();
        String sql = "update Contact set uid=?, contactId=?, contactName=?, contactCardId=?, contactPhone=?, contactState=? where uid=? and contactId=?";
        db.execSQL(sql, new String[]{String.valueOf(uid),String.valueOf(contactId),contactName,contactCardId,contactPhone,String.valueOf(contactState),String.valueOf(uid),String.valueOf(contactId)});
        return 1;
    }
}
