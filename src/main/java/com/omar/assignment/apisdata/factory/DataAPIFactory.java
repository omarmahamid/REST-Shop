package com.omar.assignment.apisdata.factory;

import com.omar.assignment.apisdata.*;
import com.omar.assignment.apisdata.delete.RemoveCatalogData;
import com.omar.assignment.apisdata.delete.RemoveItemData;
import com.omar.assignment.apisdata.get.PurchaseData;
import com.omar.assignment.apisdata.get.QueryCatalogData;
import com.omar.assignment.apisdata.post.AddCatalogData;
import com.omar.assignment.apisdata.post.AddItemData;
import com.omar.assignment.apisdata.post.AddItemIntoShopData;
import com.omar.assignment.apisdata.post.register.RegisterCustomerData;
import com.omar.assignment.apisdata.post.register.RegisterOwnerData;
import com.omar.assignment.apisdata.post.register.RegisterSellerData;
import com.omar.assignment.apisdata.put.UpdateCatalogData;
import com.omar.assignment.apisdata.put.UpdateItemData;

import java.util.logging.Logger;

public class DataAPIFactory {

    private static Logger log = Logger.getLogger(DataAPIFactory.class.getName());

    public static IDataAPI getInstance(String type){

        switch (type){
            case "addItem":
                return new AddItemData();
            case "addCatalog":
                return new AddCatalogData();
            case "queryCatalog":
                return new QueryCatalogData();
            case "purchase":
                return new PurchaseData();
            case "registerCustomer":
                return new RegisterCustomerData();
            case "registerOwner":
                return new RegisterOwnerData();
            case "registerSeller":
                return new RegisterSellerData();
            case "addItemIntoShop":
                return new AddItemIntoShopData();
            case "updateCatalog":
                return new UpdateCatalogData();
            case "updateItem":
                return new UpdateItemData();
            case "removeItem":
                return new RemoveItemData();
            case "removeCatalog":
                return new RemoveCatalogData();
        }

        log.info("error in type request");
        return null;
    }
}
