package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.models.Order;
import org.example.models.Restaurant;
import org.example.models.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceImpl {
    private Integer orderId = 0;
    private List<Order> orderList = new ArrayList<>();
    private Map<String, List<Order>> userWiseOrders = new HashMap<>();
    private Map<String, Restaurant> restaurantNameWiseMap = new HashMap<>();
    private Map<String, UserDetails> mobileNumberWiseUserView = new HashMap<>();
    private String currentLoggedInUserMobileNumber = null;


    public Order placeOrder(String restaurantName, String quantity, String userMobileNumber) {

    }
}
