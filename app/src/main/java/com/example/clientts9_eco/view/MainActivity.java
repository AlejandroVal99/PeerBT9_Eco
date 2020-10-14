package com.example.clientts9_eco.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.clientts9_eco.R;
import com.example.clientts9_eco.events.OnMessageListener;

public class MainActivity extends AppCompatActivity implements OnMessageListener {

    int nOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void OnOrderStatusReceived() {

        nOrders--;

    }
}