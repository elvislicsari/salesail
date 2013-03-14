package com.ace.salesail.manager.test;

import com.ace.salesail.domain.Category;
import com.ace.salesail.domain.Sale;
import com.ace.salesail.domain.Store;
import com.ace.salesail.domain.StorePin;
import com.ace.salesail.manager.SaleManager;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context-common.xml"})
public class MockObjectsGeneratorTest {

    @Autowired
    @Qualifier("saleManager")
    private SaleManager saleManager;

    @Test
    public void testAddStoreAndPins() {

        for (int i = 0; i<=4; i++) {
            StorePin sPin = new StorePin();
            sPin.setLatitude(45.755539 + i);
            sPin.setLongitude(21.237499 + i);
            sPin.setLatitudeRadians(Math.toRadians(sPin.getLatitude()));
            sPin.setLongitudeRadians(Math.toRadians(sPin.getLongitude()));
            saleManager.saveOrUpdate(sPin);

            Store s = new Store();
            s.setAddress("address store "+i);
            s.setCity("Timisoara");
            s.setCountry("Romania");
            s.setName("Store "+i);
            s.setPhone("123-333-2-"+i);
            s.setStorePin(sPin);
            saleManager.saveOrUpdate(s);
        }
    }

    @Test
    public void testAddSales() {

        List<Category> cats = saleManager.getAll(Category.class);
        List<Store> stores = saleManager.getAll(Store.class);

        for (int i = 1; i<=10; i++) {
            Sale s = new Sale();
            s.setCategory(cats.get(getRandomInteger(0, cats.size()-1)));
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 2*i);
            s.setEndTime(c.getTime());
            s.setPercent(30+i);
            s.setStore(stores.get(getRandomInteger(0, stores.size() - 1)));
            saleManager.saveOrUpdate(s);
        }
    }

    private static int getRandomInteger(int aStart, int aEnd){
        Random random = new Random();
        if ( aStart > aEnd ) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * random.nextDouble());
        int randomNumber =  (int)(fraction + aStart);

        return randomNumber;
    }
}