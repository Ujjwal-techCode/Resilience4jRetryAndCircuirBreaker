package com.retry.examples.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class RestaurantCatalog {

    private Integer restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private Map<String,Integer> restaurantMenu;
    private String failureTranslatedMessage;

}
