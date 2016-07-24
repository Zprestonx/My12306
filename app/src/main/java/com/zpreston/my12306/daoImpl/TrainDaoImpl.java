package com.zpreston.my12306.daoImpl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zpreston.my12306.bean.Contact;
import com.zpreston.my12306.bean.Train;
import com.zpreston.my12306.dao.TrainDao;
import com.zpreston.my12306.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by preston on 2016/7/20.
 *
 *
 */
public class TrainDaoImpl implements TrainDao {
    private DbHelper dbHelper;

    public TrainDaoImpl(Context context){
        dbHelper=new DbHelper(context);
    }

    @Override
    public List<String> queryCities() {
        List<String> list=new ArrayList<>();
        String sql="select distinct startStationName from Train";
        Cursor cursor=dbHelper.getReadableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        cursor.close();
        dbHelper.close();
        return list;
    }

    @Override
    public List<Train> queryTrain(String startStationName, String endStationName, String startDate) {
        List<Train> list=new ArrayList<>();
        String sql="select * from Train where startStationName=? and endStationName=? and startDate=?";
        Cursor cursor=dbHelper.getReadableDatabase().
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
        dbHelper.close();
        return list;
    }

    @Override
    /*
    根据车次号查询始发站和终点站
    入参：车次号
    出参：List<String> 第一个为始发站，第二个为终点站
    * */
    public List<String> getStartEndStationByTrainNo(String trainNo) {
        List<String> stationList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select startStationName,endStationName from Train where trainNo=?";
        Cursor cursor = db.rawQuery(sql, new String[]{trainNo});
        if(cursor.moveToNext())
        {
            String startStaion = cursor.getString(cursor.getColumnIndex("startStationName"));
            String endStation = cursor.getString(cursor.getColumnIndex("endStationName"));
            stationList.add(startStaion);
            stationList.add(endStation);
            return stationList;
        }
        else
        {
            return null;
        }
    }
}
