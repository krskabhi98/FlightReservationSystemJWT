package com.project.FlightReservationSystem.service;

import com.project.FlightReservationSystem.model.Booking;
import com.project.FlightReservationSystem.model.Flight;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private final List<Flight> flights = new ArrayList<>();
    private final Map<String, List<Booking>> bookings = new HashMap<>();

    public FlightService() {
        flights.add(new Flight("F001", "Delhi", "Mumbai", "10:00 AM"));
        flights.add(new Flight("F002", "Pune", "Bangalore", "2:00 PM"));
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public List<Flight> searchFlights(String source, String destination) {
        return flights.stream()
                .filter(flight -> flight.getSource().equalsIgnoreCase(source) &&
                        flight.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }

    public boolean bookFlight(String username, String flightId) {
        if (flights.stream().noneMatch(f -> f.getId().equals(flightId))) return false;
        bookings.computeIfAbsent(username, k -> new ArrayList<>())
                .add(new Booking(username, flightId));
        return true;
    }

    public List<Booking> getBookings(String username) {
        return bookings.getOrDefault(username, Collections.emptyList());
    }
}

