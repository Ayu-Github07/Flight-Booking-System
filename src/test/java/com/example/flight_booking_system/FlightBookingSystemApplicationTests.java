package com.example.flight_booking_system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;

import com.example.flight_booking_system.models.Passenger;
import com.example.flight_booking_system.repository.PassengerRepo;
import com.example.flight_booking_system.services.PassengerServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class FlightBookingSystemApplicationTests {

	@Autowired
	private PassengerServiceImpl passengerServiceImpl;

	@MockBean
	private PassengerRepo passengerRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void getAllPassengersTest() {
		when(passengerRepo.findAll())
				.thenReturn(java.util.stream.Stream
						.of(new Passenger(123, "Ayush", "Agrawal", "male"),
								new Passenger(456, "Ritik", "Dixit", "male"))
						.collect(Collectors.toList()));

		assertEquals(2, passengerServiceImpl.getAllPassengers().size());
	}

	@Test
	public void setPassengerTest() {
		Passenger passenger = new Passenger(789, "Abhya", "Malviya", "female");

		when(passengerRepo.save(passenger)).thenReturn(passenger);

		assertEquals(passenger, passenger);
	}
}
