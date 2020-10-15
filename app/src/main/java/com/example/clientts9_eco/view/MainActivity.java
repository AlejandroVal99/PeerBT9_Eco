package com.example.clientts9_eco.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.os.Bundle;

import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.clientts9_eco.R;
import com.example.clientts9_eco.comunnication.UDPconnection;
import com.example.clientts9_eco.events.OnMessageListener;
import com.example.clientts9_eco.model.Order;
import com.example.clientts9_eco.model.OrderStatus;
import com.example.clientts9_eco.model.inLimit;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements OnMessageListener, View.OnClickListener {

    private String ip;
    private int port;
    private UDPconnection udp;
    private ImageButton btn_burger, btn_HotDog, btn_burrito, btn_Sandwich;
    private Vibrator vibrator;


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
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        btn_burger.setOnClickListener(this);
        btn_HotDog.setOnClickListener(this);
        btn_burrito.setOnClickListener(this);
        btn_Sandwich.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Gson gson = new Gson();
        switch (v.getId()) {
            case R.id.btn_Burger:
                Order newOrder = new Order("Burger");
                String msg = gson.toJson(newOrder);
                udp.sendMessage(msg);
               
                break;

            case R.id.btn_HotDog:
                Order nOrder = new Order("HotDog");
                String ms = gson.toJson(nOrder);
                udp.sendMessage(ms);

                break;
            case R.id.btn_Sandwich:
                Order eOrder = new Order("Sandwich");
                String m = gson.toJson(eOrder);
                udp.sendMessage(m);

                break;
            case R.id.btn_Burrito:
                Order order = new Order("Burrito");
                String mes = gson.toJson(order);
                udp.sendMessage(mes);

                break;
        }

    }


    @Override
    public void OnOrderStatusReceived(OrderStatus orderStatus) {
        runOnUiThread(
                ()->{
                    if(orderStatus.isReady()){
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setMessage("Puedes pasar por tu orden");
                        alert.setNeutralButton("Voy en camino", (dialog, which) -> dialog.cancel());
                        AlertDialog title = alert.create();
                        title.setTitle("Orden lista");
                        title.show();


                        vibrator.vibrate(1500);
                    }

                }
        );

    }

    @Override
    public void OnOrderReceived(Order newOrder) {

    }

    @Override
    public void OnInLimitReceived(inLimit limit) {
        runOnUiThread(
                () -> {
                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                    if (limit.isFull()) {
                        alert.setMessage("El restaurante no recibe más ordenes");
                        alert.setNeutralButton("Intertar de nuevo", (dialog, which) -> dialog.cancel());
                        AlertDialog title = alert.create();
                        title.setTitle("Restaurante lleno");
                        title.show();


                        vibrator.vibrate(1500);
                    } else {
                        alert.setMessage("Tu pedido ha sido recibido");
                        alert.setNeutralButton("Ok", (dialog, which) -> dialog.cancel());
                        AlertDialog title = alert.create();
                        title.setTitle("Pedido recibido");
                        title.show();


                        // vibrator.vibrate(1500);
                    }
                }
        );


    }


}