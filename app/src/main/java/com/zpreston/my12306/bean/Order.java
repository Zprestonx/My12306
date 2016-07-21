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
}
