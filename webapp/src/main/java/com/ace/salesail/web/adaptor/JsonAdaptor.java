package com.ace.salesail.web.adaptor;

import com.ace.salesail.domain.Sale;
import com.ace.salesail.web.domain.json.SaleJsonResponse;

import java.util.ArrayList;
import java.util.List;

public class JsonAdaptor {

    public List<SaleJsonResponse> getJsonSales(List<Sale> sales) {
        List<SaleJsonResponse> salesJson = new ArrayList<SaleJsonResponse>();
        for (Sale sale : sales) {
            SaleJsonResponse saleJ = new SaleJsonResponse();
            saleJ.setEndTime(sale.getEndTime());
            saleJ.setPercent(sale.getPercent());
            saleJ.setStoreAddress(sale.getStore().getAddress());
            saleJ.setStoreName(sale.getStore().getName());
            saleJ.setStorePinLatitude(sale.getStore().getStorePin().getLatitude());
            saleJ.setStorePinLongitude(sale.getStore().getStorePin().getLongitude());

            salesJson.add(saleJ);
        }

        return salesJson;
    }

}