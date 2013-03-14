package com.ace.salesail.manager;

import com.ace.salesail.domain.Category;
import com.ace.salesail.domain.Sale;
import com.ace.salesail.domain.Store;
import com.ace.salesail.domain.StorePin;
import com.ace.salesail.geo.GeoLocation;
import com.ace.salesail.util.Constants;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class SaleManager extends GenericManager {

    public List<Store> getStoresForPins(List<StorePin> storePins) {

        DetachedCriteria dc = DetachedCriteria.forClass(Store.class);
        dc.add(Restrictions.in("storePin", storePins));
        return this.getByCriteria(dc);
    }

    public List<Sale> getSalesForCategoriesAndStores(List<Category> categories,
                                                     List<Store> stores) {

        DetachedCriteria dc = DetachedCriteria.forClass(Sale.class);
        dc.add(Restrictions.in("category", categories));
        dc.add(Restrictions.in("store", stores));
        return this.getByCriteria(dc);
    }

    public List<StorePin> getStorePinsWithinDistance(double latitude, double longitude, double distance) {

        double earthRadius = Constants.EARTH_RADIUS;
        GeoLocation location = GeoLocation.fromDegrees(latitude, longitude);

        GeoLocation[] boundingCoordinates =
                location.boundingCoordinates(distance, earthRadius);
        boolean meridian180WithinDistance =
                boundingCoordinates[0].getLongitudeInRadians() >
                boundingCoordinates[1].getLongitudeInRadians();

        List<StorePin> pins = getByQuery("FROM StorePin WHERE (latitudeRadians >= ? AND latitudeRadians <= ?) AND " +
                                                          "(longitudeRadians >= ? " + (meridian180WithinDistance ? "OR" : "AND") +
                                                          " longitudeRadians <= ?) AND acos(sin(?) * sin(latitudeRadians) + cos(?) * cos(latitudeRadians)" +
                                                          " * cos(longitudeRadians - ?)) <= ?",
                                                          boundingCoordinates[0].getLatitudeInRadians(),
                                                          boundingCoordinates[1].getLatitudeInRadians(),
                                                          boundingCoordinates[0].getLongitudeInRadians(),
                                                          boundingCoordinates[1].getLongitudeInRadians(),
                                                          location.getLatitudeInRadians(),
                                                          location.getLatitudeInRadians(),
                                                          location.getLongitudeInRadians(),
                                                          distance / earthRadius);

        return pins;
    }

    public List<Sale> getSalesForCategoriesWithinDistance(List<Category> categories,
                                                          double latitute,
                                                          double longitude,
                                                          double distance) {

        List<StorePin> storePins = getStorePinsWithinDistance(latitute, longitude, distance);
        if (storePins.size() > 0) {
            List<Store> stores = getStoresForPins(storePins);
            return getSalesForCategoriesAndStores(categories, stores);
        } else {
            return null;
        }
    }
}