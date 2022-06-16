package com.omar.assignment.handlers.get;


import com.omar.assignment.annotation.SrvHandler;
import com.omar.assignment.apisdata.get.PurchaseData;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.handlers.ISrvGetHandler;
import com.omar.assignment.handlers.Validator;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.logging.Logger;

@SrvHandler(name = "Purchase")
public class SrvHandlerPurchase implements ISrvGetHandler {

    protected Logger log = Logger.getLogger(SrvHandlerPurchase.class.getName());
    private final static String PURCHASE = "purchase";
    private final static String CUSTOMER = "customer";


    private JDBCTemplateExecutor jdbcTemplateExecutor;

    @Autowired
    public SrvHandlerPurchase(JDBCTemplateExecutor jdbcTemplateExecutor){
        this.jdbcTemplateExecutor = jdbcTemplateExecutor;
    }

    @Override
    public ServerResponse processRequest(DataHolder dataHolder) {
        ServerResponse serverResponse = new ServerResponse();

        if (dataHolder.getType() == null || !dataHolder.getType().equals(PURCHASE)){
            log.info("type is not accepted");
            return null;
        }
        log.info("process purchase request");
        PurchaseData purchaseData = (PurchaseData) dataHolder.getDataAPI();

        //check whether the customer is register
        if (!Validator.valid(purchaseData.getCustomerId(), jdbcTemplateExecutor, CUSTOMER)){
            log.info("customer not register");
            return null;
        }

        save(purchaseData);

        log.info("finish purchase request");
        serverResponse.setOk(true);
        serverResponse.setType(dataHolder.getType());
        serverResponse.setStatus(HttpStatus.OK);

        return serverResponse;
    }

    private void save(PurchaseData purchaseData){
        double total = 0;
        double discount = 0;
        long bundleSize = 0;
        // get the max id
        Integer max = jdbcTemplateExecutor.getMaxIdForPurchase();
        long purchaseId = max != null ? max + 1 : 1;
        for (Long id : purchaseData.getItemIds()){
            if (jdbcTemplateExecutor.getItemsInShopById(id).isEmpty()){
                log.info("id " + id + " doesn't exist in items table");
                continue;
            }
            // update total and bundleSize
            total += jdbcTemplateExecutor.getItemToShopById(id).iterator().next().getPrice();
            bundleSize++;
            //jdbcTemplateExecutor.insertIntoItemPurchased(id, purchaseId);
            jdbcTemplateExecutor.setSold(id);
        }
        if (bundleSize == 0){
            log.info("no item purchased");
            return;
        }
        // discount = (1/2 + 1/4 + 1/8 + 1/16 + .... ) * 100%
        discount = 1 - Math.pow(0.5,bundleSize);

        jdbcTemplateExecutor.insertIntoPurchases(purchaseId,purchaseData.getCustomerId(),purchaseData.getPaymentMethod(),total,discount);
    }
}
