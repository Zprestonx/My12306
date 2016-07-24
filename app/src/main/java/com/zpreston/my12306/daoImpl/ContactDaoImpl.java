package com.zpreston.my12306.daoImpl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.dao.ContactDao;
import com.zpreston.my12306.dao.UserDao;
import com.zpreston.my12306.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public class ContactDaoImpl implements ContactDao {
    private DbHelper dbHelper;

    public ContactDaoImpl(Context context) {
        dbHelper = new DbHelper(context);
    }
    @Override
    /*
    根据email查询所有联系人，联表
    * */
    public List<Contact> queryMyContacts(String email) {
        List<Contact> contacts = new ArrayList<>();
        //获取只读数据库对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //SQL语句
        String sql = "select * from User,Contact where User.email = ? and User.uid=Contact.uid";
        Cursor cursor = db.rawQuery(sql, new String[]{email});

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
    入参：邮箱email,Contact对象
    出参：状态码，1表示添加成功，0表示添加失败
    uid和contactId不会传过来，要自动获取
    uid的话就根据邮箱查，contactId就设为自动增长
    * */
    public int addContact(String email,Contact contact) {
        //获取成员的值
        //int uid = contact.getUid();
        //int contactId = contact.getContactId();
        String contactName = contact.getContactName();
        String contactCardId = contact.getContactCardId();
        String contactPhone = contact.getContactPhone();
        int contactState = contact.getContactState();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select User.uid,max(contactId) as maxContactId from User,Contact where email=? and Contact.uid = User.uid";
        Cursor cursor = db.rawQuery(sql, new String[]{email});
        if(cursor.moveToNext())
        {
            int uid = cursor.getInt(cursor.getColumnIndex("uid"));
            int contactId = cursor.getInt(cursor.getColumnIndex("maxContactId"));
            int newContactId = contactId + 1;

            sql = "insert into Contact(uid,contactId,contactName,contactCardId,contactPhone,contactState) values(?,?,?,?,?,?)";

            db.execSQL(sql, new String[]{String.valueOf(uid),String.valueOf(newContactId),contactName,contactCardId,contactPhone,String.valueOf(contactState)});
            cursor.close();
            return 1;
        }
        else
        {
            cursor.close();
            return 0;
        }
    }

    @Override
    public int deleteContact(String email,String contactName) {
        //根据uid获取email
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //String sql = "delete Contact from User,Contact where User.email=? and User.uid=Contact.uid and contactId=?";
        String sql = "delete from Contact where contactName=? and uid=" +
                "(select uid from User where email = ?)";
        db.execSQL(sql, new String[]{contactName,email});
        return 1;
    }

    @Override
    public int updateContact(String contactName,int contactState,String contactPhone) {
        //获取成员的值
        /*int uid = contact.getUid();
        int contactId = contact.getContactId();
        String contactName = contact.getContactName();
        String contactCardId = contact.getContactCardId();
        String contactPhone = contact.getContactPhone();
        int contactState = contact.getContactState();*/

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "update Contact set contactPhone=?, contactState=? where contactName=?";
        db.execSQL(sql, new String[]{contactPhone,String.valueOf(contactState),contactName});
        return 1;
    }

    @Override
    /*
    查询单个联系人
    入参：email用户邮箱，contactId联系人id
    出参：Contact 对象
    * */
    public Contact querySingleContactById(String email, int contactId) {
        //获取只读数据库对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //SQL语句
        String sql = "select * from Contact where contactId=? and uid = " +
                "(select uid from User where email = ?)";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(contactId), email});

        if(cursor.moveToNext())
        {
            //Contact bean 的结构
            int userId = cursor.getInt(cursor.getColumnIndex("uid"));
            int newContactId = cursor.getInt(cursor.getColumnIndex("contactId"));
            String newContactName = cursor.getString(cursor.getColumnIndex("contactName"));
            String contactCardId = cursor.getString(cursor.getColumnIndex("contactCardId"));
            String contactPhone = cursor.getString(cursor.getColumnIndex("contactPhone"));
            int contactState = cursor.getInt(cursor.getColumnIndex("contactState"));

            Contact contact = new Contact(userId, newContactId, newContactName, contactCardId, contactPhone, contactState);
            cursor.close();
            return contact;
        }
        else
        {
            cursor.close();
            return null;
        }
    }

    public Contact querySingleContact(String email, String contactName) {
        //获取只读数据库对象
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //SQL语句
        String sql = "select * from Contact where contactName=? and uid = " +
                "(select uid from User where email = ?)";
        Cursor cursor = db.rawQuery(sql, new String[]{contactName, email});

        if(cursor.moveToNext())
        {
            //Contact bean 的结构
            int userId = cursor.getInt(cursor.getColumnIndex("uid"));
            int newContactId = cursor.getInt(cursor.getColumnIndex("contactId"));
            String newContactName = cursor.getString(cursor.getColumnIndex("contactName"));
            String contactCardId = cursor.getString(cursor.getColumnIndex("contactCardId"));
            String contactPhone = cursor.getString(cursor.getColumnIndex("contactPhone"));
            int contactState = cursor.getInt(cursor.getColumnIndex("contactState"));

            Contact contact = new Contact(userId, newContactId, newContactName, contactCardId, contactPhone, contactState);
            cursor.close();
            return contact;
        }
        else
        {
            cursor.close();
            return null;
        }
    }
}
