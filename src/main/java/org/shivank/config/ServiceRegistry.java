package org.shivank.config;

import lombok.*;
import org.shivank.models.Order;
import org.shivank.models.Restaurant;
import org.shivank.models.UserDetails;
import org.shivank.service.OrderService;
import org.shivank.service.RestaurantService;
import org.shivank.service.UserService;
import org.shivank.service.impl.OrderServiceImpl;
import org.shivank.service.impl.RestaurantServiceImpl;
import org.shivank.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Getter
public class ServiceRegistry {

    private static volatile ServiceRegistry instance;
    private final Map<String, UserDetails> mobileNumberWiseUserView = new ConcurrentHashMap<>();
    private final String currentLoggedInUserMobileNumber = null;
    private final Map<String, List<Order>> userWiseOrders = new ConcurrentHashMap<>();
    private final Map<String, Restaurant> restaurantNameWiseMap = new ConcurrentHashMap<>();
    private final List<Order> orderList = new ArrayList<>();

    private final UserService userService;

    private final OrderService orderService;

    private final RestaurantService restaurantService;

    private AtomicInteger orderId = new AtomicInteger(1);

    private ServiceRegistry()
    {
        this.userService = new UserServiceImpl(mobileNumberWiseUserView,currentLoggedInUserMobileNumber);
        this.restaurantService = new RestaurantServiceImpl( restaurantNameWiseMap);
        this.orderService = new OrderServiceImpl(orderId,orderList, userWiseOrders, restaurantService, userService);

    }

    public static ServiceRegistry getInstance()
    {
        if (instance == null) {
            synchronized (ServiceRegistry.class) {
                if (instance == null) {
                    instance = new ServiceRegistry();
                }
            }
        }
        return instance;

    }





}
