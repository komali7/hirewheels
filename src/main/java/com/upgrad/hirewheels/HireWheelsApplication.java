package com.upgrad.hirewheels;

import com.upgrad.hirewheels.dao.*;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.User;
import com.upgrad.hirewheels.entities.Vehicle;
import com.upgrad.hirewheels.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Date;


@SpringBootApplication
public class HireWheelsApplication {

	public static void main(String[] args) {

		ApplicationContext context= SpringApplication.run(HireWheelsApplication.class, args);
		UserService userService = context.getBean(UserService.class);
		RoleDao roleDao = context.getBean(RoleDao.class);
		LocationDao locationDao = context.getBean(LocationDao.class);

		User user1 = new User();
		user1.setEmail("abc@xyz");
		user1.setFirstName("Yatin");
		user1.setLastName("Tendulkar");
		user1.setMobileNo("0123456789");
		user1.setPassword("testpassword");
		user1.setRole(roleDao.findById(2).get());
		userService.createUser(user1);


		AdminService adminService = context.getBean(AdminService.class);
		VehicleSubcategoryDao vehicleSubcategoryDao = context.getBean(VehicleSubcategoryDao.class);
		FuelTypeDao fuelTypeDao = context.getBean(FuelTypeDao.class);

		Vehicle vehicle = new Vehicle("AWG","IND 345","Black", 1, "https://www.mercedes-amg.com/en/world-of-amg/news/press-information/mercedes-amg-gt-black-series-record-lap.html",
				vehicleSubcategoryDao.findById(2).get(), locationDao.findById(1).get(), fuelTypeDao.findById(1).get());
		adminService.registerVehicle(vehicle);



		BookingService bookingService = context.getBean(BookingService.class);

		Booking booking = new Booking();
		booking.setUser(user1);
		booking.setBookingDate(new Date());
		booking.setDropoffDate(new Date());
		booking.setLocation(locationDao.findById(1).get());
		booking.setAmount(200);
		booking.setPickupDate(new Date());
		booking.setVehicleWithBooking(vehicle);

		adminService.changeAvailability(vehicle.getVehicleId(),0);

		bookingService.addBooking(booking);


	}

	@Bean
	CommandLineRunner init(initService initService) {
		return args -> {
			initService.start();
		};
	}

}
