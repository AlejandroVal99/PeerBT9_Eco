package com.example.clientts9_eco.view;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.widget.ImageButton;

import com.example.clientts9_eco.R;
import com.example.clientts9_eco.comunnication.UDPconnection;
import com.example.clientts9_eco.events.OnMessageListener;
import com.example.clientts9_eco.model.Order;
import com.example.clientts9_eco.model.OrderStatus;
import com.example.clientts9_eco.model.inLimit;

public class MainActivity extends AppCompatActivity implements OnMessageListener {

    private String ip;
    private int port;
    private UDPconnection udp;
    private ImageButton btn_burger, btn_HotDog, btn_burrito, btn_Sandwich;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        udp = UDPconnection.getInstance();
        udp.setObserver(this);

        btn_burger = findViewById(R.id.btn_Burger);
        btn_HotDog = findViewById(R.id.btn_HotDog);
        btn_burrito = findViewById(R.id.btn_Burrito);
        btn_Sandwich = findViewById(R.id.btn_Sandwich);

    }




    @Override
    public void OnOrderStatusReceived(OrderStatus orderStatus) {

    }

    @Override
    public void OnOrderReceived(Order newOrder) {

    }

    @Override
    public void OnInLimitReceived(inLimit limit) {

    }
}