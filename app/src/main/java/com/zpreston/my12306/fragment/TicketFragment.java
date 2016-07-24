package com.zpreston.my12306.fragment;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.ticket.QueryCity;
import com.zpreston.my12306.activity.ticket.Ticket1Activity;
import com.zpreston.my12306.adapter.QueryHistoryAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketFragment extends Fragment {

    private static final int BEGIN_CITY_CODE=1;
    private static final int END_CITY_CODE=2;

    private int year=0;
    private int month=0;
    private int day=0;

    public static ArrayList<Activity> activityS=new ArrayList<Activity>();

    private Button query;
    private TextView time;
    private TextView beginCity,endCity;
    private ListView listView;

    public TicketFragment(){

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        time=(TextView)getActivity().findViewById(R.id.Time);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        beginCity=(TextView)getActivity().findViewById(R.id.BeginCity);
        beginCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),QueryCity.class);
                startActivityForResult(intent,BEGIN_CITY_CODE);
            }
        });
        endCity=(TextView)getActivity().findViewById(R.id.EndCity);
        endCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),QueryCity.class);
                startActivityForResult(intent,END_CITY_CODE);
            }
        });
        query = (Button) getActivity().findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将查询城市加入查询历史中
                addToHistory();
                if(year==0 || month==0 || day==0){
                    Toast.makeText(getActivity(),"请选择日期",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(), Ticket1Activity.class);
                    intent.putExtra("beginCity",beginCity.getText().toString());
                    intent.putExtra("endCity",endCity.getText().toString());
                    intent.putExtra("year",year);
                    intent.putExtra("month",month);
                    intent.putExtra("day",day);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        listView=(ListView) getActivity().findViewById(R.id.listView);
        listView.setDivider(null);
        List<String> data=getData();
        final QueryHistoryAdapter adapter=new QueryHistoryAdapter(getActivity(),data);
        if(data!=null){
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String cities[]=adapter.getItem(position).toString().split("-");
                    beginCity.setText(cities[0]);
                    endCity.setText(cities[1]);
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==BEGIN_CITY_CODE && resultCode== Activity.RESULT_OK){
            if(data!=null){
                beginCity.setText(data.getStringExtra("city"));
            }
        }else if(requestCode==END_CITY_CODE && resultCode== Activity.RESULT_OK){
            if(data!=null){
                endCity.setText(data.getStringExtra("city"));
            }
        }
    }

    private void showDateDialog(){
        Calendar calendar=Calendar.getInstance(Locale.CHINA);
        Date nowDate=new Date();
        calendar.setTime(nowDate);
        int nowYear=calendar.get(Calendar.YEAR);
        int nowMonth=calendar.get(Calendar.MONTH);
        int nowDay=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int mYear, int monthOfYear, int dayOfMonth) {
                year=mYear;
                month=monthOfYear+1;
                day=dayOfMonth;
                time.setText(month+"月"+day+"日");
                time.setTextSize(25);
            }
        },nowYear,nowMonth,nowDay);
        dialog.show();
    }

    private void addToHistory(){
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String firstFrom=sharedPreferences.getString("firstFrom",null);
        String firstTo=sharedPreferences.getString("firstTo",null);
        if(null!=firstFrom && null!=firstTo){
            Log.e("TAG-getFirst",firstFrom+"　"+firstTo);
            editor.putString("secondFrom",firstFrom);
            editor.putString("secondTo",firstTo);
        }
        editor.putString("firstFrom",beginCity.getText().toString());
        editor.putString("firstTo",endCity.getText().toString());
        editor.commit();
    }

    private List<String> getData(){
        List<String> list=new ArrayList<String>();
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("history", Context.MODE_PRIVATE);
        String firstFrom=sharedPreferences.getString("firstFrom",null);
        String firstTo=sharedPreferences.getString("firstTo",null);
        if(null!=firstFrom && null!=firstTo){
            list.add(firstFrom+"-"+firstTo);
            String secondFrom=sharedPreferences.getString("secondFrom",null);
            String secondTo=sharedPreferences.getString("secondTo",null);
            Log.e("TAG-getSecond",secondFrom+"　"+secondTo);
            if(null!=secondFrom && null!=secondTo){
                list.add(secondFrom+"-"+secondTo);
            }
            return list;
        }
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false);
    }

}
