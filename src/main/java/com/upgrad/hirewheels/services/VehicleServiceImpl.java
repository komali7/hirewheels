package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.BookingDao;
import com.upgrad.hirewheels.dao.VehicleCategoryDao;
import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.entities.Booking;
import com.upgrad.hirewheels.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    VehicleCategoryDao vehicleCategoryDao;

    @Autowired
    BookingDao bookingDao;

    @Override
    public List<Vehicle> getAvailableVehicles(String categoryName, Date pickUpDate, Date dropDate, int locationId) {
        List<Vehicle> returnedVehicleList = new ArrayList<>();
        vehicleCategoryDao.findByVehicleCategoryName(categoryName).getVehicleSubcategories()
                .forEach(a ->a.getVehicles()
                        .forEach(b->{
                            if(b.getLocation().getLocationId() == locationId && b.getAvailabilityStatus() == 1)
                                returnedVehicleList.add(b);
                        }));

        List<Vehicle> bookedVehicleIdList = new ArrayList<>();
        returnedVehicleList.forEach(a-> {
            List<Booking> bookedVehicleList = bookingDao.findByVehicleWithBooking(a);
            bookedVehicleList.forEach(b -> {
                if ((pickUpDate.after(b.getPickupDate()) && pickUpDate.before(b.getDropoffDate()))
                        || (dropDate.after(b.getPickupDate()) && dropDate.before(b.getDropoffDate()))
                        || (pickUpDate.after(b.getPickupDate()) && dropDate.before(b.getDropoffDate()))
                        || pickUpDate.equals(b.getDropoffDate())
                        || dropDate.equals(b.getPickupDate())
                        || pickUpDate.equals(b.getPickupDate())
                        || dropDate.equals(b.getDropoffDate()) ) {
                    bookedVehicleIdList.add(b.getVehicleWithBooking());
                }
            });

        });

            List<Vehicle> availableVehicle = new ArrayList<>();
            returnedVehicleList.forEach(a->{
                if(!bookedVehicleIdList.contains(a.getVehicleId())){
                    availableVehicle.add(a);
                }
            });
        return availableVehicle;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
    List<Vehicle> registeredVehicleList = vehicleDao.findAll();
        return registeredVehicleList;
    }
}
