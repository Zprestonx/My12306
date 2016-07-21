package com.zpreston.my12306.daoImpl;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Train;
import com.zpreston.my12306.dao.TrainDao;

import java.util.List;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public class TrainDaoImpl implements TrainDao {
    @Override
    public List<String> queryCities() {
        return null;
    }

    @Override
    public List<Train> queryTrain(String startStationName, String endStationName, String startDate) {
        return null;
    }

    @Override
    public String orderTickets(List<Contact> contactList, Train train) {
        return null;
    }

    @Override
    public int submitOrder(int uid, String orderNo) {
        return 0;
    }
}
