package com.zpreston.my12306.bean;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
//9个字段
public class User {
    private int uid;
    private String email;
    private String password;
    private String userName;
    private int gender;
    private String idCard;
    private String phone;
    private String lastLoginTime;
    private int userStatus;

    //要有空的构造方法
    public User()
    {

    }

    public User(int uid, String email, String password, String userName, int gender, String idCard, String phone, String lastLoginTime, int userStatus) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.gender = gender;
        this.idCard = idCard;
        this.phone = phone;
        this.lastLoginTime = lastLoginTime;
        this.userStatus = userStatus;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", idCard='" + idCard + '\'' +
                ", phone='" + phone + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", userStatus=" + userStatus +
                '}';
    }
}

