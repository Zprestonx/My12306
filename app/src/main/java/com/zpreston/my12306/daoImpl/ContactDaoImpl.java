package com.zpreston.my12306.daoImpl;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.dao.ContactDao;

import java.util.List;

/**
 * Created by preston on 2016/7/20.
 */
public class ContactDaoImpl implements ContactDao {
    @Override
    public List<Contact> queryMyContacts(int uid) {
        return null;
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
