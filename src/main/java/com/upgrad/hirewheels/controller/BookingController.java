package com.upgrad.hirewheels.controller;

import com.upgrad.hirewheels.dto.BookingDTO;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.BookingService;
import com.upgrad.hirewheels.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/hirewheels/v1")
@RestController
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    VehicleService vehicleService;

    @PostMapping(value = "/bookings" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity addBooking(@RequestBody BookingDTO bookingDTO){
        Booking newBooking = modelMapper.map(bookingDTO, Booking.class);
        settingVehicle(bookingDTO, newBooking);

        Booking savedBooking = bookingService.addBooking(newBooking);

        BookingDTO savedBookingDto = modelMapper.map(savedBooking, BookingDTO.class);

        return new ResponseEntity<>(savedBookingDto, HttpStatus.CREATED);
    }

    public void settingVehicle(BookingDTO bookingDTO, Booking booking){
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(bookingDTO.getVehicleId());
        booking.setVehicleWithBooking(vehicle);
    }
}
