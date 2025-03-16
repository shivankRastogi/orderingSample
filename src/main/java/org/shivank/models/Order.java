package org.shivank.models;


import lombok.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    String restaurantName;
    Integer quantity;
    Integer orderId;
    String userMobileNumber;
}
