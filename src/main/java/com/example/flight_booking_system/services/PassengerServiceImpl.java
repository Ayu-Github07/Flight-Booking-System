package com.example.flight_booking_system.services;

import java.util.List;

import com.example.flight_booking_system.models.Passenger;
import com.example.flight_booking_system.repository.PassengerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerRepo passengerRepo;

    @Override
    public void setAllPassengers(List<Passenger> passengerList) {
        passengerRepo.saveAll(passengerList);
    }

    @Override
    public void setPassengers(Passenger passenger) {
        passengerRepo.save(passenger);
    }

    @Override
    public List<Passenger> getAllPassengers() {

        return passengerRepo.findAll();
    }

    @Override
    public Passenger getPassengersById(long id) {

        return passengerRepo.findById(id);
    }

    @Override
    public List<Passenger> getPassengerByFirstNameAndLastName(String firstName, String lastName) {

        return passengerRepo.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Passenger> getPassengerByFirstName(String firstName) {

        return passengerRepo.findByFirstName(firstName);
    }

    @Override
    public Passenger updateById(Passenger passenger) {

        return passengerRepo.save(passenger);
    }

    @Override
    public Passenger updateByFirstName(Passenger passenger) {

        return passengerRepo.save(passenger);
    }

    @Override
    public void deleteById(long id) {

        passengerRepo.deleteById(id);
    }

}
