package com.retry.examples.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class TableBooking {
    private int id;
    private String restaurantName;
    private Map<Integer, String> tableIdBookingMap;
    private String status;
}
