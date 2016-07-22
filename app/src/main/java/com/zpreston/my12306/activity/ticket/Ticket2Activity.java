package com.zpreston.my12306.activity.ticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zpreston.my12306.R;
import com.zpreston.my12306.fragment.TicketFragment;

public class Ticket2Activity extends AppCompatActivity {
    private Button q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket2);
        TicketFragment.activityS.add(this);

        q = (Button) this.findViewById(R.id.button4);
        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ticket2Activity.this, Ticket3Activity.class);
                startActivity(intent);
            }
        });
    }
}
