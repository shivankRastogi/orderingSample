package org.shivank.service.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shivank.models.Order;
import org.shivank.models.Restaurant;
import org.shivank.models.UserDetails;
import org.shivank.service.OrderService;
import org.shivank.service.RestaurantService;
import org.shivank.service.UserService;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceImpl implements OrderService {
    private AtomicInteger orderId ;
    private List<Order> orderList ;
    private Map<String, List<Order>> userWiseOrders ;

    private RestaurantService restaurantService;

    private UserService userService;


    @Override
    public Order placeOrder(String restaurantName, Integer quantity) {
        Restaurant restaurant = restaurantService.getRestaurant(restaurantName);
        UserDetails userDetails = userService.getLoggedInUser();
        if(restaurant==null)
        {
            log.warn("restaurant does not exist");
            return null;
        }
        if(userDetails==null){
            log.warn("No user is logged in");
            return null;
        }
        if(quantity==null)
        {
            log.error("quantity cant be null");
            return null;
        }
        Order newOrder = Order.builder()
                .orderId(orderId.incrementAndGet())
                .quantity(quantity)
                .restaurantName(restaurantName)
                .userMobileNumber(userDetails.getPhoneNumber())
                .build();
        orderList.add(newOrder);
        List<Order> userWiseOrder = Optional.ofNullable(userWiseOrders.get(userDetails.getPhoneNumber())).orElse(new ArrayList<>()) ;
        userWiseOrder.add(newOrder);
        userWiseOrders.put(userDetails.getPhoneNumber(),userWiseOrder);
        return  newOrder;
    }

    @Override
    public List<Order> userOrderHistory(){
        UserDetails userDetails = userService.getLoggedInUser();
        if(userDetails==null){
            log.warn("No user is logged in");
            return null;
        }
        return Optional.ofNullable(userWiseOrders.get(userDetails.getPhoneNumber())).orElse(Collections.emptyList());
    }
}
