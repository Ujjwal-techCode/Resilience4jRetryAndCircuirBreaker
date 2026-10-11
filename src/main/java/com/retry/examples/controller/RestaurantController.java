package com.retry.examples.controller;

import com.retry.examples.dto.response.RestaurantCatalog;
import com.retry.examples.dto.response.TableBooking;
import com.retry.examples.service.RestaurantService;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    CircuitBreakerRegistry registry;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantCatalog> getCatalog(@PathVariable Integer restaurantId){
        return ResponseEntity.ok(restaurantService.getRestaurantCatalog(restaurantId));
    }

    @GetMapping("/checkRegisteredCB")
    public List<String> check() {
        List<String> registeredCircuitBreakerNames = registry.getAllCircuitBreakers().stream().map(cb -> cb.getName()).toList();
        return registeredCircuitBreakerNames;
    }

    @GetMapping("/tableBookingStatus/{restaurantId}")
    public ResponseEntity<TableBooking> getTableBookedStatus(@PathVariable Integer restaurantId) {
        return switch(restaurantId) {
            case 1 -> ResponseEntity.ok(restaurantService.checkTableBookedStatus1(restaurantId));
            case 2 -> ResponseEntity.ok(restaurantService.checkTableBookedStatus2(restaurantId));
            case 3 -> ResponseEntity.ok(restaurantService.checkTableBookedStatus3(restaurantId));
            case 4 -> ResponseEntity.ok(restaurantService.checkTableBookedStatus4(restaurantId));
            case 5 -> ResponseEntity.ok(restaurantService.checkTableBookedStatus5(restaurantId));
            default -> ResponseEntity.notFound().build();
        };
    }
}