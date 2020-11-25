package com.andrei.fleetManagement.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Contract {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    private boolean finished;
    private String startDate;
    private Date finishDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Car car;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Partner partner;

    public Contract(Car car, Customer customer, Partner partner) {
        this.car = car;
        this.customer = customer;
        this.partner = partner;
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

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
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
}
