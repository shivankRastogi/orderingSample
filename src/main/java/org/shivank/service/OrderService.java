package org.shivank.service;

import org.shivank.models.Order;

import java.util.List;

public interface OrderService {

    public Order placeOrder(String restaurantName, Integer quantity);
    public List<Order> userOrderHistory();
}
