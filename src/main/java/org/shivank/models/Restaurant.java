package org.shivank.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    private String name;
    private String address;
    private String dish;
    private int price;
    private int quantity;
    private int totalRatingSum;
    private int numberOfRatings;
    private int averageRating;
    private List<String> comments;
}
