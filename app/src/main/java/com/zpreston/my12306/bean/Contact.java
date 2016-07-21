package com.zpreston.my12306.bean;

/**
 * Created by preston on 2016/7/20.
 *
 */
public class Contact {
    private int uid;
    private int contactId;
    private String contactName;
    private String contactCardId;
    private String contactPhone;
    private String contactState;

    public Contact()
    {}

    public Contact(int uid, int contactId, String contactName, String contactCardId, String contactPhone, String contactState) {
        this.uid = uid;
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactCardId = contactCardId;
        this.contactPhone = contactPhone;
        this.contactState = contactState;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactCardId() {
        return contactCardId;
    }

    public void setContactCardId(String contactCardId) {
        this.contactCardId = contactCardId;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactState() {
        return contactState;
    }

    public void setContactState(String contactState) {
        this.contactState = contactState;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "uid=" + uid +
                ", contactId=" + contactId +
                ", contactName='" + contactName + '\'' +
                ", contactCardId='" + contactCardId + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", contactState='" + contactState + '\'' +
                '}';
    }
}
