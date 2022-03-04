package com.upgrad.hirewheels.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fuel_type")
public class FuelType {

    @Id
    private int fuelTypeId;

    @Column(nullable = false, unique = true, length = 50)
    private String fuelType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "fuelType")
    private Set<Vehicle> vehicles;

    public FuelType(){}

    public FuelType(int fuelTypeId, String fuelType) {
        this.fuelTypeId = fuelTypeId;
        this.fuelType = fuelType;
    }

    public int getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(int fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "FuelType{" +
                "fuelTypeId=" + fuelTypeId +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}

