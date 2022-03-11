package com.example.flight_booking_system.services;

import java.util.List;

import com.example.flight_booking_system.models.Flight;

public interface FlightService {
    public void setFlightDetails(Flight flight);

    public Flight getFlightById(long id);

    public Flight getFlightByFlightNumber(String flightNumber);

    public List<Flight> getFlightBySourceAndDestination(String source, String destination);

    public List<Flight> getAllFlights();

    public Flight updateFlightByFlightNumber(Flight flight);

    public void deleteFlightByFlightNumber(String flightNumber);

}
