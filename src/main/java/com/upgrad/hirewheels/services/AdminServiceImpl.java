package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.VehicleDao;
import com.upgrad.hirewheels.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    VehicleDao vehicleDao;
    @Override
    public Vehicle registerVehicle(Vehicle vehicle) {
        boolean testVehicleNumber = vehicleDao.existsByVehicleNumber(vehicle.getVehicleNumber());
        if(testVehicleNumber){
            System.out.println("Vehicle number already exists");
        }
        Vehicle savedVehicle = vehicleDao.save(vehicle);
        savedVehicle.setAvailabilityStatus(1);
        return savedVehicle;
    }

    @Override
    public Vehicle changeAvailability(int vehicleId, int availabilityStatus) {
        Vehicle vehicle = vehicleDao.findById(vehicleId).get();

        vehicle.setAvailabilityStatus(availabilityStatus);
        return vehicleDao.save(vehicle);
    }
}
