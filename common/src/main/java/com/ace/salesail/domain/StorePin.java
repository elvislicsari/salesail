package com.ace.salesail.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "store_pin")
public class StorePin extends Persistent {

    private double latitude;
    private double longitude;
    private double latitudeRadians;
    private double longitudeRadians;

    public double getLatitudeRadians() {
        return latitudeRadians;
    }

    public void setLatitudeRadians(double latitudeRadians) {
        this.latitudeRadians = latitudeRadians;
    }

    public double getLongitudeRadians() {
        return longitudeRadians;
    }

    public void setLongitudeRadians(double longitudeRadians) {
        this.longitudeRadians = longitudeRadians;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "StorePin: [id="+id+", latitude="+latitude+", longitude="+longitude+"]";
    }
}