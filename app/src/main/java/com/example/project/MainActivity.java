package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
{
    Button historyBtn, visionBtn, addressBtn, supportBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    static String historyTxt;
    static String visionTxt;
    static String addressTxt;

    Handler mHandler  = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("CompanyInfo");

        historyBtn = (Button) findViewById(R.id.historyBtn);
        addressBtn = (Button) findViewById(R.id.addressBtn);
        supportBtn = (Button) findViewById(R.id.supportBtn);
        visionBtn = (Button) findViewById(R.id.visionBtn);

        Query getTxt = reference;

        getTxt.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    historyTxt = dataSnapshot.child("History").child("Text").getValue(String.class);
                    visionTxt = dataSnapshot.child("Vision").child("Text").getValue(String.class);
                    addressTxt = dataSnapshot.child("Address").child("Text").getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        visionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(v, visionRunnable, 500);
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openActivity(v, historyRunnable, 500);
            }
        });

        addressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(v, addressRunnable, 500);
            }
        });

        supportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                startActivity(intent);
            }
        });
    }

    public void openActivity(View v, Runnable r, int delay)
    {
        mHandler.postDelayed(r, delay);
    }

    private Runnable historyRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
            intent.putExtra("HISTORY", historyTxt);

            startActivity(intent);
        }
    };

    private Runnable visionRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), VisionActivity.class);
            intent.putExtra("VISION", visionTxt);

            startActivity(intent);
        }
    };

    private Runnable addressRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(getApplicationContext(), AddressActivity.class);
            intent.putExtra("ADDRESS", addressTxt);

            startActivity(intent);
        }
    };



}
