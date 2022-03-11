package com.example.flight_booking_system.repository;

import java.util.List;

import com.example.flight_booking_system.models.Flight;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepo extends MongoRepository<Flight, Long> {

    public Flight findById(long id);

    public Flight findByFlightNumber(String flightNumber);

    public List<Flight> findBySourceAndDestination(String source, String destination);

    public Flight deleteByFlightNumber(String flightNumber);

}
