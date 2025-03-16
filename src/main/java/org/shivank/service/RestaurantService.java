package org.shivank.service;

import org.shivank.models.Restaurant;

import java.util.List;

public interface RestaurantService {

    public Restaurant registerRestaurant(String restaurantName, String locality, String dish, int quantity, int price);

    public List<Restaurant> showRestaurants(String sortCriteria );

    public Restaurant giveReview(String restaurantName, String review, String rating);

    public Restaurant updatePrice(String restaurantName, int price);

    public Restaurant updateQuantity(String restaurantName, int quantity) ;

    public Restaurant getRestaurant(String restaurantName);
}
