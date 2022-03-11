package com.example.flight_booking_system.repository;

import com.example.flight_booking_system.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {

    public User findByUsername(String username);
}
