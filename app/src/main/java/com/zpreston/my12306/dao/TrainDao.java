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

}

