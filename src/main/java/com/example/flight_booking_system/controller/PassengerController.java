package com.example.flight_booking_system.controller;

import java.util.List;
import java.util.Optional;

import com.example.flight_booking_system.exceptions.PassengerNotFoundException;
import com.example.flight_booking_system.models.Passenger;
import com.example.flight_booking_system.services.PassengerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerServiceImpl passengerServiceImpl;

    @GetMapping("/")
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        try {
            List<Passenger> passengersList = passengerServiceImpl.getAllPassengers();
            return ResponseEntity.of(Optional.of(passengersList));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengersById(@PathVariable("id") long id) {
        try {
            Passenger passenger = passengerServiceImpl.getPassengersById(id);
            return ResponseEntity.of(Optional.of(passenger));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/FirstName")
    public ResponseEntity<List<Passenger>> getPassengerByFirstName(
            @RequestParam(value = "firstName") String firstName) {
        try {
            List<Passenger> passengerList = passengerServiceImpl.getPassengerByFirstName(firstName);
            return ResponseEntity.of(Optional.of(passengerList));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/FirstName-and-LastName")
    public ResponseEntity<List<Passenger>> getPassengerByFirstNameAndLastName(
            @RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName) {
        try {
            List<Passenger> passengerList = passengerServiceImpl.getPassengerByFirstNameAndLastName(firstName,
                    lastName);
            return ResponseEntity.of(Optional.of(passengerList));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> setPassengers(@RequestBody Passenger passenger) {
        try {
            passengerServiceImpl.setPassengers(passenger);
            return ResponseEntity.ok("Passenger Added Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePassengerById(@RequestBody Passenger passenger, @PathVariable("id") long id)
            throws PassengerNotFoundException {

        try {
            List<Passenger> passengersList = passengerServiceImpl.getAllPassengers();
            boolean flag = false;

            for (Passenger p : passengersList) {
                if (p.getPassengerId() == id) {
                    flag = true;
                    break;
                }
            }

            if (flag == false) {
                throw new PassengerNotFoundException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warning: Passenger does not exists");
        }

        Passenger passenger2 = passengerServiceImpl.updateById(passenger);

        return ResponseEntity.ok("Passenger Updated Successfully\nPassenger Updated Details: \n" + passenger2);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePassengerById(@PathVariable("id") long id) throws PassengerNotFoundException {
        try {
            List<Passenger> passengersList = passengerServiceImpl.getAllPassengers();
            boolean flag = false;

            for (Passenger p : passengersList) {
                if (p.getPassengerId() == id) {
                    flag = true;
                    break;
                }
            }

            if (flag == false) {
                throw new PassengerNotFoundException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Warning: Passenger does not exists");
        }
        passengerServiceImpl.deleteById(id);
        return ResponseEntity.ok("Passengers Details Deleted Successfully!");
    }
}
