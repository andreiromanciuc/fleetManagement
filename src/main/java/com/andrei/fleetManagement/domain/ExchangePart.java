package com.andrei.fleetManagement.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class ExchangePart {

    @Id
    @GeneratedValue
    private long id;
    private String code;
    private String name;
    private double quantity;
    private double price;
    private String createdBy;
    @ElementCollection
    private List<String> updatedBy;

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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<String> getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(List<String> updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
