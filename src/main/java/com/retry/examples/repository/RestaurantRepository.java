package com.retry.examples.repository;

import com.retry.examples.dto.response.RestaurantCatalog;
import com.retry.examples.dto.response.TableBooking;
import com.retry.examples.exception.RetryPredicate;
import org.springframework.stereotype.Service;

import java.net.http.HttpConnectTimeoutException;
import java.util.Map;

@Service
public class RestaurantRepository {

    public RestaurantCatalog findById(int id){
        RestaurantCatalog restaurantCatalog = new RestaurantCatalog();
        if(id == 10){
            restaurantCatalog.setRestaurantId(10);
            restaurantCatalog.setRestaurantName("Kritunga");
            restaurantCatalog.setRestaurantAddress("Marathalli");
            Map<String,Integer> itemAndPrice = Map.of("Biryani",200,"Pulav", 400, "Paneer",350);
            restaurantCatalog.setRestaurantMenu(itemAndPrice);
        }
        else{
            throw new ArrayIndexOutOfBoundsException("The Restaurant Id doesn't exist");
        }
        return restaurantCatalog;
    }

    public TableBooking getBookingStatus(int restaurantId) throws RuntimeException {
        TableBooking tableBooking = new TableBooking();
        System.out.println("RestaurantRepository.getBookingStatus() called for Restaurant Id : "+restaurantId);
        switch (restaurantId) {
            case 1 -> throw new NullPointerException("null value sent");
            case 2 -> throw new ArrayIndexOutOfBoundsException("Array Index not correct");
            case 3 -> throw new NumberFormatException("number is not correct");
            case 4 -> throw new IllegalArgumentException("Illegal Argument sent");
            case 5 -> throw new IndexOutOfBoundsException("Index not correct");
        }
        return tableBooking;
    }
}