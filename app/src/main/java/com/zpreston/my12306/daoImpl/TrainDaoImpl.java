package com.zpreston.my12306.daoImpl;

import android.content.Context;
import android.database.Cursor;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Train;
import com.zpreston.my12306.dao.TrainDao;
import com.zpreston.my12306.db.TrainHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public class TrainDaoImpl implements TrainDao {
    private TrainHelper trainHelper;

    public TrainDaoImpl(Context context){
        trainHelper=new TrainHelper(context);
    }

    @Override
    public List<String> queryCities() {
        List<String> list=new ArrayList<String>();
        String sql="select distinct startStationName from Train";
        Cursor cursor=trainHelper.getReadableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        cursor.close();
        trainHelper.close();
        return list;
    }

    @Override
    public List<Train> queryTrain(String startStationName, String endStationName, String startDate) {
        List<Train> list=new ArrayList<Train>();
        String sql="select * from Train where startStationName=? and endStationName=? and startDate=?";
        Cursor cursor=trainHelper.getReadableDatabase().
                rawQuery(sql,new String[]{startStationName,endStationName,startDate});
        while(cursor.moveToNext()){
            int id=cursor.getInt(0);
            String trainNo=cursor.getString(1);
            String startStation=cursor.getString(2);
            String endStation=cursor.getString(3);
            String startTime=cursor.getString(4);
            String arriveTime=cursor.getString(5);
            String StartDate=cursor.getString(6);
            int seats=cursor.getInt(7);
            int price=cursor.getInt(8);
            Train train=new Train(id,trainNo,startStation,endStation,
                    startTime,arriveTime,StartDate,seats,price);
            list.add(train);
        }
        cursor.close();
        trainHelper.close();
        return list;
    }

}
