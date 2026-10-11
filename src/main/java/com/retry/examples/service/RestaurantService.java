package com.retry.examples.service;

import com.retry.examples.dto.response.RestaurantCatalog;
import com.retry.examples.dto.response.TableBooking;

public interface RestaurantService {

    RestaurantCatalog getRestaurantCatalog(int restaurantId);
    TableBooking checkTableBookedStatus1(int restaurantId);
    TableBooking checkTableBookedStatus2(int restaurantId);
    TableBooking checkTableBookedStatus3(int restaurantId);
    TableBooking checkTableBookedStatus4(int restaurantId);
    TableBooking checkTableBookedStatus5(int restaurantId);
}
