package com.andrei.fleetManagement.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String plateNumber;
    @NotNull
    private String vinNumber;
    private String model;
    private long mileage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getMileage() {
        return mileage;
    }

    public void setMileage(long mileage) {
        this.mileage = mileage;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
