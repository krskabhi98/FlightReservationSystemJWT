package com.project.FlightReservationSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private String id;
    private String source;
    private String destination;
    private String departureTime;
}
