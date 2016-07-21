package com.zpreston.my12306.dao;

import com.zpreston.my12306.bean.Contact;

import java.util.List;

/**
 * Created by user on 2016/7/20.
 * 2016-07-21 08:43
 *
 */
public interface ContactDao {
    /*
    查询我的联系人
    入参：用户ID
    出参：联系人List
    * */
    List<Contact> queryMyContacts(int uid);

    /*
    添加联系人
    入参：Contact对象
    出参：状态码，1表示添加成功，0表示添加失败
    * */
    int addContact(Contact contact);

    /*
    删除联系人
    入参：联系人ID
    出参：状态码，1表示删除成功，0表示删除失败
    * */
    int deleteContact(int contactId);

    /*
    修改联系人
    入参：Contact对象
    出参：状态码，1表示修改成功
    * */
    int updateContact(Contact contact);
}
