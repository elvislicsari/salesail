package com.ace.salesail.web.controller.mobile;

import com.ace.salesail.domain.Category;
import com.ace.salesail.domain.Sale;
import com.ace.salesail.domain.User;
import com.ace.salesail.manager.SaleManager;
import com.ace.salesail.manager.UserManager;
import com.ace.salesail.util.Constants;
import com.ace.salesail.util.EncryptionUtil;
import com.ace.salesail.web.adaptor.JsonAdaptor;
import com.ace.salesail.web.domain.json.LoginJsonResponse;
import com.ace.salesail.web.domain.json.SaleJsonResponse;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/mobile/")
public class MobileController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    @Qualifier("saleManager")
    protected SaleManager saleManager;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    @Qualifier("userManager")
    protected UserManager userManager;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    @Qualifier("jsonAdaptor")
    private JsonAdaptor jsonAdaptor;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody LoginJsonResponse login(@RequestParam("email") String email,
                                         @RequestParam("password") String password) {

        User user = userManager.getUser(email, password);
        if (user != null) {
            return new LoginJsonResponse(true, EncryptionUtil.getAppKey(email));
        } else {
            return new LoginJsonResponse(false, "INVALID");
        }
    }

    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategories() {

        List<Category> categories = saleManager.getAll(Category.class);
        return categories;
    }

    @RequestMapping(value = "sales", method = RequestMethod.POST)
    public @ResponseBody List<SaleJsonResponse> getSalesForCategoriesWithinDistanceFromLocation(
            @RequestParam("categoryIds") String categoryIds,
            @RequestParam("distance") double distance,
            @RequestParam("measureUnit") String measureUnit,
            @RequestParam("latitude") double latitude,
            @RequestParam("longitude") double longitude) {
        String[] categoryIdsArr = categoryIds.split(",");
        Long[] categoryIntIdsArr = new Long[categoryIdsArr.length];
        for (int i = 0; i < categoryIdsArr.length; i++) {
            categoryIntIdsArr[i] = Long.parseLong(categoryIdsArr[i]);
        }
        DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
        dc.add(Restrictions.in("id", categoryIntIdsArr));
        List<Category> searchCategories = saleManager.getByCriteria(dc);

        if (measureUnit.equals("miles")) {
            distance = Constants.MILES_TO_KM_RATIO * distance;
        }

        List<Sale> salesWithinDistance = saleManager.getSalesForCategoriesWithinDistance(searchCategories,
                                                                                         latitude,
                                                                                         longitude,
                                                                                         distance);
        List<SaleJsonResponse> jsonSales = jsonAdaptor.getJsonSales(salesWithinDistance);
        return jsonSales;
    }

}