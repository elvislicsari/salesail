package com.ace.salesail.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Store extends Persistent {

    private String name;
    private String address;
    private String phone;
    private String city;
    private String country;
    private String state;
    private StorePin storePin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @OneToOne
    public StorePin getStorePin() {
        return storePin;
    }

    public void setStorePin(StorePin storePin) {
        this.storePin = storePin;
    }
}