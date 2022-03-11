package com.example.flight_booking_system.controller;

import java.util.List;

import com.example.flight_booking_system.exceptions.FlightNotFoundException;
import com.example.flight_booking_system.models.Flight;
import com.example.flight_booking_system.services.FlightServiceImpl;
import com.example.flight_booking_system.utility.GlobalResources;

import org.slf4j.Logger;
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
@RequestMapping("/flights")
public class FlightController {

    private Logger logger = GlobalResources.getLogger(FlightController.class);

    @Autowired
    private FlightServiceImpl flightServiceImpl;

    @GetMapping("/")
    public ResponseEntity<List<Flight>> getAllFlights() {

        String methodName = "getAllFlights()";
        logger.info(methodName + "called");

        try {
            List<Flight> list = flightServiceImpl.getAllFlights();
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {

            logger.error(methodName + "Something went wrong");

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{flightNumber}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("flightNumber") String flightNumber) {

        String methodName = "getFlightById";
        logger.info(methodName + "called");

        try {
            Flight flight = flightServiceImpl.getFlightByFlightNumber(flightNumber);
            return ResponseEntity.ok().body(flight);
        } catch (Exception e) {

            logger.error(methodName + "Something went wrong");

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/source-to-destination")
    public ResponseEntity<List<Flight>> getFlightBySourceAndDestination(@RequestParam(value = "source") String source,
            @RequestParam(value = "destination") String destination) {

        String methodName = "getFlightBySourceAndDestination";
        logger.info(methodName + "called");

        try {
            List<Flight> flight = flightServiceImpl.getFlightBySourceAndDestination(source, destination);

            return ResponseEntity.ok().body(flight);
        } catch (Exception e) {

            logger.error(methodName + "Something went wrong");

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> setFlight(@RequestBody Flight flight) {

        String methodName = "setFlight";
        logger.info(methodName + "called");

        try {
            flightServiceImpl.setFlightDetails(flight);
            return ResponseEntity.ok().body("Flight Added Successfully");
        } catch (Exception e) {

            logger.error(methodName + "Something went wrong");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{flightNumber}")
    public ResponseEntity<String> updateFlight(@RequestBody Flight flight,
            @PathVariable("flightNumber") String flightNumber) throws FlightNotFoundException {

        String methodName = "updateFlight";
        logger.info(methodName + "called");

        try {
            List<Flight> list = flightServiceImpl.getAllFlights();
            boolean flag = false;
            for (Flight f : list) {
                if (f.getFlightNumber().equals(flightNumber)) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                throw new FlightNotFoundException("FLight Does Not Exist!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Warning: Flight wiht flight number " + flightNumber + " does not exists!");
        }
        Flight flight2 = flightServiceImpl.updateFlightByFlightNumber(flight);
        return ResponseEntity.ok().body("Flight Updated Successfully\nFlight Details are: \n" + flight2);
    }

    @DeleteMapping("/delete/{flightNumber}")
    public ResponseEntity<String> deleteFlightByFlightNumber(@PathVariable("flightNumber") String flightNumber)
            throws FlightNotFoundException {

        String methodName = "deleteFlightByFlightNumber";
        logger.info(methodName + "called");

        try {
            List<Flight> list = flightServiceImpl.getAllFlights();
            boolean flag = false;
            for (Flight f : list) {
                if (f.getFlightNumber().equals(flightNumber)) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                throw new FlightNotFoundException("FLight Does Not Exist!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Warning: Flight wiht flight number " + flightNumber + " does not exists!");
        }

        flightServiceImpl.deleteFlightByFlightNumber(flightNumber);
        return ResponseEntity.ok("Flight Deleted Successfully");
    }
}
