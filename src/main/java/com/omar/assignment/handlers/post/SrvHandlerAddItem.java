package com.omar.assignment.handlers.post;


import com.omar.assignment.annotation.SrvHandler;
import com.omar.assignment.apisdata.post.AddItemData;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.entities.dto.Item;
import com.omar.assignment.handlers.ISrvPostHandler;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.logging.Logger;

@SrvHandler(name = "AddItem")
public class SrvHandlerAddItem implements ISrvPostHandler {

    protected Logger log = Logger.getLogger(SrvHandlerAddItem.class.getName());
    private final static String ADD_ITEM = "addItem";
    private JDBCTemplateExecutor jdbcTemplateExecutor;

    @Autowired
    public SrvHandlerAddItem(JDBCTemplateExecutor jdbcTemplateExecutor){
        this.jdbcTemplateExecutor = jdbcTemplateExecutor;
    }

    // the logic for adding item to the store
    @Override
    public ServerResponse processRequest(DataHolder dataHolder) {
        ServerResponse serverResponse = new ServerResponse();
        if (dataHolder.getType() == null || !dataHolder.getType().equals(ADD_ITEM)){
            return null;
        }
        log.info("process addItem request...");

        AddItemData addItemData = (AddItemData) dataHolder.getDataAPI();

        saveItem(addItemData);

        log.info("finish addItem request");
        serverResponse.setType(dataHolder.getType());
        serverResponse.setOk(true);
        serverResponse.setStatus(HttpStatus.OK);

        return serverResponse;
    }

    private void saveItem(AddItemData addItemData){
        Item item = addItemData.getItem();
        if (!checkIfSellerExist(item.getSellerId())){
            log.info("Hello seller, need to register : look at the api for register sellers");
            return;
        }
        jdbcTemplateExecutor.insertIntoItemToShop(item.getId(),
                item.getCatalogId(),
                item.getSellerId(),
                item.getName(),
                item.getPrice());

    }

    private boolean checkIfSellerExist(long sellerId){
        List<?> sellers = jdbcTemplateExecutor.getSellerById(sellerId);
        return sellers != null && !sellers.isEmpty();
    }
}
