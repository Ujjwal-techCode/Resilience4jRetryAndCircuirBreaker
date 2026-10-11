package com.retry.examples.service.impl;

import com.retry.examples.dto.response.TableBooking;
import com.retry.examples.repository.RestaurantRepository;
import com.retry.examples.dto.response.RestaurantCatalog;
import com.retry.examples.service.RestaurantService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @CircuitBreaker(name = "catalogNotFound" , fallbackMethod = "circuitBreakerGracefullTermination")
    public RestaurantCatalog getRestaurantCatalog(int restaurantId){
        RestaurantCatalog restaurantCatalog = restaurantRepository.findById(restaurantId);
        return restaurantCatalog;
    }

    public RestaurantCatalog circuitBreakerGracefullTermination(Throwable t){
        RestaurantCatalog restaurantCatalog = new RestaurantCatalog();
        System.out.println("Circuit breaker's fallback Method Triggered");
        restaurantCatalog.setFailureTranslatedMessage("Our Services are busy right now, please try after sometime");
        return restaurantCatalog;
    }

    @Retry(name = "simpleRetry", fallbackMethod = "gracefullTableBookingStatus")
    public TableBooking checkTableBookedStatus1(int restaurantId){
        return restaurantRepository.getBookingStatus(restaurantId);
    }

    @Retry(name = "retryOnException", fallbackMethod = "gracefullTableBookingStatus")
    public TableBooking checkTableBookedStatus2(int restaurantId){
        return restaurantRepository.getBookingStatus(restaurantId);
    }

    @Retry(name = "retryBasedOnConditionalPredicate", fallbackMethod = "gracefullTableBookingStatus")
    public TableBooking checkTableBookedStatus3(int restaurantId){
        return restaurantRepository.getBookingStatus(restaurantId);
    }

    @Retry(name = "retryBasedOnExceptionPredicate", fallbackMethod = "gracefullTableBookingStatus")
    public TableBooking checkTableBookedStatus4(int restaurantId){
        return restaurantRepository.getBookingStatus(restaurantId);
    }

    @Retry(name = "retryUsingExponentialBackoff", fallbackMethod = "gracefullTableBookingStatus")
    public TableBooking checkTableBookedStatus5(int restaurantId){
        return restaurantRepository.getBookingStatus(restaurantId);
    }


    public TableBooking gracefullTableBookingStatus(Throwable t){
        System.out.println("Entered gracefullTableBookingStatus method");
        TableBooking tableBooking = new TableBooking();
        tableBooking.setStatus(t.getLocalizedMessage());
        return tableBooking;
    }
}