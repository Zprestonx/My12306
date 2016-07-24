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
    入参：用户邮箱
    出参：联系人List
    * */
    List<Contact> queryMyContacts(String email);

    /*
    添加联系人
    入参：用户邮箱email, Contact对象，uid和contactId属性可为空值
    出参：状态码，1表示添加成功，0表示添加失败
    * */
    int addContact(String email, Contact contact);

    /*
    删除联系人
    删除哪个用户的哪个联系人
    入参：用户邮箱,联系人ID
    出参：状态码，1表示删除成功，0表示删除失败
    * */
    int deleteContact(String email, String contactName);

    /*
    修改联系人
    入参：Contact对象
    出参：状态码，1表示修改成功
    * */
    int updateContact(String contactName,int contactState,String contactPhone);

    /*
    查询单个联系人
    入参：email用户邮箱，contactId联系人id
    出参：Contact 对象
    * */
    Contact querySingleContact(String email, String contactName);
}
