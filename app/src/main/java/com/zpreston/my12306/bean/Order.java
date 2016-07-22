package com.zpreston.my12306.bean;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public class Order {
    private int id;
    private int uid; //用户id
    private String orderNo;
    private int contactId;
    private String trainNo;
    private double orderPrice; //订单总价
    private int orderState; //订单状态 0未支付，1已支付，2已取消
    private String orderTime; //

    public Order(){}
    public Order(int id, int uid, String orderNo, int contactId, String trainNo, double orderPrice, int orderState, String orderTime) {
        this.id = id;
        this.uid = uid;
        this.orderNo = orderNo;
        this.contactId = contactId;
        this.trainNo = trainNo;
        this.orderPrice = orderPrice;
        this.orderState = orderState;
        this.orderTime = orderTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", uid=" + uid +
                ", orderNo='" + orderNo + '\'' +
                ", contactId=" + contactId +
                ", trainNo='" + trainNo + '\'' +
                ", orderPrice=" + orderPrice +
                ", orderState=" + orderState +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}
