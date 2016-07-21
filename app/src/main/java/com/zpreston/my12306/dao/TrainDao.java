package com.zpreston.my12306.dao;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Train;

import java.util.List;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public interface TrainDao {

    /*
    城市查询
    在预订车票选择出发城市和到达城市时使用
    入参：void
    出参：城市List数组
    * */
    List<String> queryCities();

    /*
    车次查询
    入参：始发站名称，终点站名称，出发日期
    出参：车次列表
    * */
    List<Train> queryTrain(String startStationName, String endStationName, String startDate);

    /*
    车票预订
    入参：乘车人列表（可能有多个人乘同一列车）,车次Train对象
    出参：订单号，orderNo
    * */
    String orderTickets(List<Contact> contactList, Train train);

    /*
    提交订单
    入参：用户ID，orderNo 订单号
    出参：状态码，1表示提交成功
    * */
    int submitOrder(int uid, String orderNo);

}

