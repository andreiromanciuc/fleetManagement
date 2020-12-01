package com.andrei.fleetManagement.domain;

import javax.persistence.*;

@Entity
public class Workmanship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String code;
    private String name;
    private double timing;
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTiming() {
        return timing;
    }

    public void setTiming(double timing) {
        this.timing = timing;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
