package com.ace.salesail.manager.test;

import com.ace.salesail.domain.Category;
import com.ace.salesail.domain.Sale;
import com.ace.salesail.domain.StorePin;
import com.ace.salesail.manager.SaleManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/context-common.xml"})
public class SaleManagerTest {

    @Autowired
    @Qualifier("saleManager")
    private SaleManager saleManager;


    @Test
    public void testGetSalesForCategories() {
        List<Category> cats = new ArrayList<Category>();
        cats.add(saleManager.getOneByEqualProperty(Category.class, "name", "Shoes"));
        cats.add(saleManager.getOneByEqualProperty(Category.class, "name", "Other"));

//        System.out.println(saleManager.getSalesForCategoriesWithinDistance(cats));
    }

    @Test
    public void testGetStorePinsWithDistance() {

        List<StorePin> pins = saleManager.getStorePinsWithinDistance(45.755539, 26.237499, 150);
        System.out.println(pins);

    }

    @Test
    public void testGetSalesForCategoriesWithinDistance() {

        List<Category> cats = new ArrayList<Category>();
        cats.add(saleManager.getOneByEqualProperty(Category.class, "id", 1L));
        cats.add(saleManager.getOneByEqualProperty(Category.class, "id", 2L));
        cats.add(saleManager.getOneByEqualProperty(Category.class, "id", 3L));
        cats.add(saleManager.getOneByEqualProperty(Category.class, "id", 4L));

        List<Sale> sales = saleManager.getSalesForCategoriesWithinDistance(cats, 45.755539, 21.237499, 150);
        System.out.println(sales);

    }
}