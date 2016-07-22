package com.zpreston.my12306;

import android.app.Application;
import android.database.Cursor;
import android.test.ApplicationTestCase;
import android.util.Log;

import com.zpreston.my12306.bean.Train;
import com.zpreston.my12306.dao.TrainDao;
import com.zpreston.my12306.daoImpl.TrainDaoImpl;
import com.zpreston.my12306.db.DbHelper;

import java.util.List;

/**
 * Created by user on 2016/7/21.
 *
 */
/*---------------------------------TrainDao的测试-----------------------------------------------------------*/
public class TrainDaoTest extends ApplicationTestCase<Application>{
    private DbHelper dbHelper;
    public TrainDaoTest() {
        super(Application.class);
    }
    public void testCreateTrain(){
        String sql="select * from Train";
        dbHelper=new DbHelper(getContext());
        Cursor cursor=dbHelper.getWritableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            Log.e("Train",cursor.getString(1)+"--"+cursor.getString(2)+"--"+cursor.getString(3));
        }
        if(!cursor.isClosed()) cursor.close();
        if(dbHelper!=null) dbHelper.close();
        Log.e("Train","************null");
    }
    public void testQueryCities(){
        TrainDao trainDao=new TrainDaoImpl(getContext());
        List<String> list=trainDao.queryCities();
        if(list==null){
            Log.e("queryCities","*********null");
            return;
        }
        Log.e("Train","************not null");
        for(int i=0;i<list.size();i++){
            Log.e("queryCities",list.get(i));
        }
    }
    public void testQueryTrain(){
        TrainDao trainDao=new TrainDaoImpl(getContext());
        List<Train> list=trainDao.queryTrain("广州","上海","2016-07-25");
        for(Train train:list){
            Log.e("queryTrain",train.toString());
        }
    }

}
