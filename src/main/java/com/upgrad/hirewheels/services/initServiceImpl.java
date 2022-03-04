package com.upgrad.hirewheels.services;

import com.upgrad.hirewheels.dao.*;
import com.upgrad.hirewheels.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class initServiceImpl implements initService{

    @Autowired
    RoleDao roleDao;
    
    @Autowired
    UserDao userDao;

    @Autowired
    VehicleCategoryDao vehicleCategoryDao;

    @Autowired
    VehicleSubcategoryDao vehicleSubcategoryDao;

    @Autowired
    CityDao cityDao;

    @Autowired
    FuelTypeDao fuelTypeDao;
    
    @Autowired
    LocationDao locationDao;


    @Override
    public void start() {
        addUserRole();
        addUsers();
        addVehicleCategory();
        addVehicleSubCategory();
        addCity();
        addFuelType();
        addLocation();
        
    }

    private void addUserRole() {
        List<Role> userRoleList = Arrays.asList(new Role(1, "Admin"), new Role(2, "User"));
        roleDao.saveAll(userRoleList);
    }

    private void addUsers() {
        User adminUser = new User("upGrad","Admin","admin@123","upgrad@gmail.com",
                "9999999999",10000, roleDao.findById(1).get());
        userDao.save(adminUser);
     }

    private void addVehicleCategory() {

        List<VehicleCategory> vehicleCategoryList = Arrays.asList(new VehicleCategory(10, "Car"), new VehicleCategory(11, "Bike"));
        vehicleCategoryDao.saveAll(vehicleCategoryList);
    }

    private void addVehicleSubCategory() {
        List<VehicleSubcategory> vehicleSubcategories = new ArrayList<>();
        vehicleSubcategories.add(new VehicleSubcategory(1, "SUV",
                300, vehicleCategoryDao.findById(10).get()));
        vehicleSubcategories.add(new VehicleSubcategory(2, "SEDAN",
                350, vehicleCategoryDao.findById(10).get()));
        vehicleSubcategories.add(new VehicleSubcategory(3, "HATCHBACK",
                250, vehicleCategoryDao.findById(10).get()));
        vehicleSubcategories.add(new VehicleSubcategory(4,"CRUISER",
                200, vehicleCategoryDao.findById(11).get()));
        vehicleSubcategories.add(new VehicleSubcategory(5, "DIRT BIKE",
                200, vehicleCategoryDao.findById(11).get()));
        vehicleSubcategories.add(new VehicleSubcategory(6, "SPORTS BIKE", 
                150, vehicleCategoryDao.findById(11).get()));
        
        vehicleSubcategoryDao.saveAll(vehicleSubcategories);
    }

    private void addCity() {
        cityDao.save( new City(1, "Mumbai"));
    }

    private void addFuelType() {
        List<FuelType> fuelTypeList = Arrays.asList(new FuelType(1, "Petrol"),new FuelType(2, "Diesel"));
        fuelTypeDao.saveAll(fuelTypeList);
    }

    private void addLocation() {

        Location location = new Location(1, "Worli", "Dr E Moses Rd, Worli Naka, Upper Worli","400018",cityDao.findById(1).get() );
        locationDao.save(location);
        location = new Location(2, "chembur", "Optic Complex", "400019", cityDao.findById(1).get());
        locationDao.save(location);
        location = new Location(3,"Powai", "Hiranandani Towers", "400020" , cityDao.findById(1
        ).get());
        locationDao.save(location);
    }
}