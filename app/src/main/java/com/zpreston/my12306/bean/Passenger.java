package com.zpreston.my12306.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 16-7-24.
 */
public class Passenger implements Serializable {
    public String getTvContactName() {
        return tvContactName;
    }

    public void setTvContactName(String tvContactName) {
        this.tvContactName = tvContactName;
    }

    public String getTvIdCard() {
        return tvIdCard;
    }

    public void setTvIdCard(String tvIdCard) {
        this.tvIdCard = tvIdCard;
    }

    public String getTvPhone() {
        return tvPhone;
    }

    public void setTvPhone(String tvPhone) {
        this.tvPhone = tvPhone;
    }

    String tvContactName;
    String tvIdCard;
    String tvPhone;
}
