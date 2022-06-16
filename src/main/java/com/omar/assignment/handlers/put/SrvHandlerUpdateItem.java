package com.omar.assignment.handlers.put;


import com.omar.assignment.annotation.SrvHandler;
import com.omar.assignment.apisdata.put.UpdateItemData;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.handlers.ISrvPutHandler;
import com.omar.assignment.handlers.Validator;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.logging.Logger;

@SrvHandler(name = "UpdateItem")
public class SrvHandlerUpdateItem implements ISrvPutHandler {

    protected Logger log = Logger.getLogger(SrvHandlerUpdateItem.class.getName());

    private static final String UPDATE_ITEM = "updateItem";
    private static final String OWNER = "owner";

    private JDBCTemplateExecutor jdbcTemplateExecutor;

    @Autowired
    public SrvHandlerUpdateItem(JDBCTemplateExecutor jdbcTemplateExecutor){
        this.jdbcTemplateExecutor = jdbcTemplateExecutor;
    }

    @Override
    public ServerResponse processRequest(DataHolder dataHolder) {
        ServerResponse serverResponse = new ServerResponse();

        if (dataHolder.getType() == null || !dataHolder.getType().equals(UPDATE_ITEM)){
            return null;
        }

        log.info("process update item ...");

        UpdateItemData updateItemData = (UpdateItemData) dataHolder.getDataAPI();

        updateItem(updateItemData);
        log.info("finish update item.");

        serverResponse.setType(dataHolder.getType());
        serverResponse.setOk(true);
        serverResponse.setStatus(HttpStatus.OK);
        return serverResponse;
    }

    private void updateItem(UpdateItemData updateItemData){
        if (!Validator.valid(updateItemData.getOwnerId(), jdbcTemplateExecutor, OWNER)){
            log.info("owner " + updateItemData.getOwnerId() + " doesn't exist");
            return;
        }
        if (!checkIfItemExistToShop(updateItemData.getItemId())){
            log.info("item " + updateItemData.getItemId() + " doesn't exist in the store");
            return;
        }
        if (updateItemData.getItemName() != null){
            jdbcTemplateExecutor.updateItemName(updateItemData.getItemId(), updateItemData.getItemName());
        }
        if (updateItemData.getPrice() != null){
            jdbcTemplateExecutor.updateItemPrice(updateItemData.getItemId(),updateItemData.getPrice());
        }
        if (updateItemData.getCatalogId() != null && checkIfCatalogExist(updateItemData.getCatalogId())){
            jdbcTemplateExecutor.updateItemCatalog(updateItemData.getItemId(),updateItemData.getCatalogId());
        }
    }


    private boolean checkIfItemExistToShop(long itemId){
        List<?> items = jdbcTemplateExecutor.getItemToShopById(itemId);
        return items != null && !items.isEmpty();
    }

    private boolean checkIfCatalogExist(long catalogId){
        List<?> catalog = jdbcTemplateExecutor.getCatalogById(catalogId);
        return catalog != null && !catalog.isEmpty();
    }
}
