package com.example.clientts9_eco.comunnication;

import android.util.Log;
import android.view.View;

import com.example.clientts9_eco.events.OnMessageListener;
import com.example.clientts9_eco.model.Generic;
import com.example.clientts9_eco.model.OrderStatus;
import com.example.clientts9_eco.model.inLimit;
import com.example.clientts9_eco.view.MainActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPconnection extends Thread{

	private static UDPconnection instance;
	
	private UDPconnection () {
		
	}
	
	public static UDPconnection getInstance() {
		if (instance == null) {
			instance = new UDPconnection();
			instance.start();
		}
		return instance;
	}
	
	
	private DatagramSocket socket;
	private OnMessageListener observer;
	private String direction;
	private int port;
	
	public void run() {
		
		try {
			socket = new DatagramSocket(6000);
			
			 while(true) {

				 byte[] buffer = new byte [500];
				 DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
				 socket.receive(packet);

				 String ipPort = packet.getSocketAddress().toString();
				 String netInfo[]=ipPort.split(":");

				 String msg = new String (packet.getData()).trim();

				 Gson gson = new Gson();

				 Generic generic = gson.fromJson(msg,Generic.class);

				 switch(generic.getType()) {

					 case "inLimit":

					 	inLimit limit = gson.fromJson(msg,inLimit.class);
						observer.OnInLimitReceived(limit);

						 break;

					 case "OrderStatus":
						 OrderStatus status = gson.fromJson(msg,OrderStatus.class);
						 //Log.e("<<<<2",msg);
						 observer.OnOrderStatusReceived(status);
					 	break;

				 }
				 
			 }
			 
			 
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendMessage(String msg) {
		
		new Thread(
				()->{
					
					try {
						direction = "192.168.0.6";
						port = 5000;
						InetAddress ip = InetAddress.getByName(direction);
						DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length,ip,port);
						socket.send(packet);
						
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				
				
				).start();
		
		
	}


	public void setObserver(OnMessageListener observer) {
		this.observer = observer;
	}
}
