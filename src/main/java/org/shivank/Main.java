package org.shivank;

import org.shivank.config.ServiceRegistry;
import org.shivank.enums.SortCriteria;
import org.shivank.models.Order;
import org.shivank.models.Restaurant;
import org.shivank.models.UserDetails;
import org.shivank.service.OrderService;
import org.shivank.service.RestaurantService;
import org.shivank.service.UserService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            ServiceRegistry serviceRegistry = ServiceRegistry.getInstance();

            UserService userService = serviceRegistry.getUserService();
            RestaurantService restaurantService = serviceRegistry.getRestaurantService();
            OrderService orderService = serviceRegistry.getOrderService();

            UserDetails user = userService.registerUser("1234567890", "M", "John", "560001");
            if (user == null) {
                throw new RuntimeException("Failed to register user");
            }

            UserDetails loggedInUser = userService.loginUser("1234567890");
            if (loggedInUser == null) {
                throw new RuntimeException("Failed to login user");
            }

            Restaurant r1 = restaurantService.registerRestaurant("Food Court-1", "BTM/HSR", "NI Thali", 5, 100);
            Restaurant r2 = restaurantService.registerRestaurant("Food Court-2", "BTM", "Burger", 3, 120);
            Restaurant r3 = restaurantService.registerRestaurant("Food Court-3", "BTM", "Burger", 7, 150);

            restaurantService.giveReview("Food Court-1", "Fucked up","1");

            restaurantService.giveReview("Food Court-3", "Amazing", "5");


            if (r1 == null || r2 == null) {
                throw new RuntimeException("Failed to register restaurants");
            }

            Order order = orderService.placeOrder("Food Court-1", 2);
            if (order == null) {
                throw new RuntimeException("Failed to place order");
            }

            System.out.println("Order History: " + orderService.userOrderHistory());
            System.out.println("Restaurants by price: " + restaurantService.showRestaurants("PRICE"));
            System.out.println("Restaurants by rating: " + restaurantService.showRestaurants(SortCriteria.RATING.name()));

        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace();
        }
    }
}