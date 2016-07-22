package com.zpreston.my12306.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zpreston.my12306.R;
import com.zpreston.my12306.activity.ticket.Ticket1Activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketFragment extends Fragment {
    private Button q;


    public TicketFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*lvTicket = (ListView) getActivity().findViewById(R.id.lvTicket);
        mData = getData();
        lvTicket.setAdapter(new lvTicketAdapter());
        lvTicket.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(getActivity(), "点击了我的账户", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(), "点击了我的联系人", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "点击了我的密码", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });*/

        q = (Button) getActivity().findViewById(R.id.query);
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Ticket1Activity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false);
    }

}
