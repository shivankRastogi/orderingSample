package org.example.service.impl;

import lombok.*;
        import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.enums.SortCriteria;
import org.example.models.Restaurant;
import org.example.models.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Slf4j
public class RestaurantServiceImpl {

    public Restaurant registerRestaurant(String restaurantName, String locality, String dish, int quantity, int price, Map<String, Restaurant> restaurantNameWiseMap) {
        if(restaurantNameWiseMap.containsKey(restaurantName)){
            log.error("Restaurant already exists");
        }
        if(StringUtils.isAnyBlank(restaurantName, locality, dish)){
            log.error("Restaurant name, or locality, or dish, or price are blank");
        }
        Restaurant restaurant = Restaurant.builder()
                .address(locality)
                .dish(dish)
                .quantity(quantity)
                .price(price)
                .name(restaurantName)
                .numberOfRatings(0)
                .totalRatingSum(0)
                .averageRating(0)
                .comments(Collections.emptyList())
                .build();
        restaurantNameWiseMap.put(restaurantName, restaurant);
        return restaurant;
    }

    public List<Restaurant> showRestaurants(String sortCriteria,Map<String, Restaurant> restaurantNameWiseMap ) {
        if (StringUtils.isBlank(sortCriteria)) {
            log.error("SortCriteria is blank");
            return null;
        }
        if (!sortCriteria.equals(SortCriteria.PRICE.name()) && !sortCriteria.equals(SortCriteria.RATING.name())) {
            log.error("SortCriteria is invalid");
            return null;
        }
        return sortCriteria.equals(SortCriteria.RATING.name()) ? restaurantNameWiseMap.values()
                .stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Restaurant::getAverageRating))
                .collect(Collectors.toList()) :
                restaurantNameWiseMap.values()
                        .stream()
                        .filter(Objects::nonNull)
                        .sorted(Comparator.comparing(Restaurant::getPrice))
                        .collect(Collectors.toList());

    }

    public Restaurant giveReview(String restaurantName, String review, String rating, Map<String, Restaurant> restaurantNameWiseMap) {

        if(StringUtils.isAnyBlank(restaurantName, rating)){
            log.error("Restaurant name, or rating are blank");
            return null;
        }
        Restaurant restaurant = restaurantNameWiseMap.get(restaurantName);
        int ratingInt = Integer.parseInt(rating);
        int newRatingCount = restaurant.getNumberOfRatings() +1;
        int newRatingSum = restaurant.getTotalRatingSum() + ratingInt;
        int newAverageRating = newRatingSum/newRatingCount;
        restaurant.setAverageRating(newAverageRating);
        restaurant.setNumberOfRatings(newRatingCount);
        restaurant.setTotalRatingSum(newRatingSum);

        if(!StringUtils.isEmpty(review)){
            List<String> reviews = restaurant.getComments();
            reviews.add(review);
            restaurant.setComments(reviews);
        }
        restaurantNameWiseMap.put(restaurantName, restaurant);
        return restaurant;
    }


    public Restaurant updatePrice(String restaurantName, int price, Map<String, Restaurant> restaurantNameWiseMap) {
        if(StringUtils.isAnyBlank(restaurantName)){
            log.error("Restaurant name is blank");
            return null;
        }
        Restaurant restaurant = restaurantNameWiseMap.get(restaurantName);
        restaurant.setPrice(price);
        restaurantNameWiseMap.put(restaurantName, restaurant);
        return restaurant;
    }
    public Restaurant updateQuantity(String restaurantName, int quantity, Map<String, Restaurant> restaurantNameWiseMap) {
        if(StringUtils.isAnyBlank(restaurantName)){
            log.error("Restaurant name is blank");
        }
        Restaurant restaurant = restaurantNameWiseMap.get(restaurantName);
        restaurant.setQuantity(quantity);
        restaurantNameWiseMap.put(restaurantName, restaurant);
        return restaurant;
    }
    }