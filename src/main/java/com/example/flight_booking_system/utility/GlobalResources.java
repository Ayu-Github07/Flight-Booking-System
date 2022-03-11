package com.example.flight_booking_system.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalResources {

    public static Logger getLogger(Class<?> classname) {
        return LoggerFactory.getLogger(classname);
    };

}
