package com.ace.salesail.web.domain.json;

import com.ace.salesail.web.adaptor.JsonDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Used to map only the important sale information for a json request
 */
public class SaleJsonResponse {

    private int percent;
    private Date endTime;
    private String storeName;
    private String storeAddress;
    private double storePinLatitude;
    private double storePinLongitude;

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public double getStorePinLatitude() {
        return storePinLatitude;
    }

    public void setStorePinLatitude(double storePinLatitude) {
        this.storePinLatitude = storePinLatitude;
    }

    public double getStorePinLongitude() {
        return storePinLongitude;
    }

    public void setStorePinLongitude(double storePinLongitude) {
        this.storePinLongitude = storePinLongitude;
    }
}