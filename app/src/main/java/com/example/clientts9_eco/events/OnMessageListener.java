package com.example.clientts9_eco.events;

import com.example.clientts9_eco.model.Order;
import com.example.clientts9_eco.model.OrderStatus;
import com.example.clientts9_eco.model.inLimit;

public interface OnMessageListener {

    void OnOrderStatusReceived(OrderStatus orderStatus);
    void OnOrderReceived(Order newOrder);
    void OnInLimitReceived(inLimit limit);


}
