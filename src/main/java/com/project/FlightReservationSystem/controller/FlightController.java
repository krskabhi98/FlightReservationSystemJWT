package com.project.FlightReservationSystem.controller;

import com.project.FlightReservationSystem.model.Booking;
import com.project.FlightReservationSystem.model.Flight;
import com.project.FlightReservationSystem.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.project.FlightReservationSystem.model.UriConst.*;

@RestController
@RequestMapping(FLIGHT)
public class FlightController {
    private final String FLIGHT_BOOKED = "Flight booked";
    private final String INVALID_FLIGHT_ID = "Invalid flight ID";

    @Autowired
    FlightService flightService;

    @GetMapping(FLIGHT_LIST)
    public List<Flight> getAllFlights() {
        return flightService.getFlights();
    }

    @GetMapping(SEARCH)
    public ResponseEntity<List<Flight>> searchFlight(@RequestParam String source,
                                                     @RequestParam String destination) {
        {
            List<Flight> result = flightService.searchFlights(source, destination);
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping(BOOK_FLIGHT)
    public ResponseEntity<?> book(@PathVariable String flightId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return flightService.bookFlight(username, flightId)
                ? ResponseEntity.ok(FLIGHT_BOOKED)
                : ResponseEntity.badRequest().body(INVALID_FLIGHT_ID);
    }

    @GetMapping(CHECK_BOOKING)
    public List<Booking> myBookings() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return flightService.getBookings(username);
    }
}

