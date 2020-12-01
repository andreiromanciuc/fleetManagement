package com.andrei.fleetManagement.domain;

import javax.persistence.*;

@Entity
public class Contract {

    @Id
    @GeneratedValue
    private long id;
    private boolean finished;
    private boolean orderedParts;
    private String startDate;
    private String finishDate;
    private String startFixCarDate;
    private String arrivalPartsDate;


    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Partner partner;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    public Contract(Car car, Customer customer, Partner partner) {
        this.car = car;
        this.customer = customer;
        this.partner = partner;
    }

    public Contract() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    public String getStartFixCarDate() {
        return startFixCarDate;
    }

    public void setStartFixCarDate(String startFixCarDate) {
        this.startFixCarDate = startFixCarDate;
    }

    public boolean isOrderedParts() {
        return orderedParts;
    }

    public void setOrderedParts(boolean orderedParts) {
        this.orderedParts = orderedParts;
    }

    public String getArrivalPartsDate() {
        return arrivalPartsDate;
    }

    public void setArrivalPartsDate(String arrivalPartsDate) {
        this.arrivalPartsDate = arrivalPartsDate;
    }
}
