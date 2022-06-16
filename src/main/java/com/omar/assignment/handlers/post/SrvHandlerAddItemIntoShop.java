package com.omar.assignment.handlers.post;

import com.omar.assignment.annotation.SrvHandler;
import com.omar.assignment.apisdata.post.AddItemIntoShopData;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.handlers.ISrvPostHandler;
import com.omar.assignment.handlers.Validator;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.logging.Logger;


@SrvHandler(name = "AddItemIntoShop")
public class SrvHandlerAddItemIntoShop implements ISrvPostHandler {


    protected Logger log = Logger.getLogger(SrvHandlerAddItemIntoShop.class.getName());
    private static final String ADD_ITEM_INTO_SHOP = "addItemIntoShop";
    private static final String OWNER = "owner";

    private JDBCTemplateExecutor jdbcTemplateExecutor;

    @Autowired
    public SrvHandlerAddItemIntoShop(JDBCTemplateExecutor jdbcTemplateExecutor){
        this.jdbcTemplateExecutor = jdbcTemplateExecutor;
    }

    @Override
    public ServerResponse processRequest(DataHolder dataHolder) {
        ServerResponse serverResponse = new ServerResponse();

        if (dataHolder.getType() == null || !dataHolder.getType().equals(ADD_ITEM_INTO_SHOP)){
            return null;
        }

        log.info("process add item into shop request...");
        AddItemIntoShopData addItemIntoShopData = (AddItemIntoShopData) dataHolder.getDataAPI();

        saveItemIntoShop(addItemIntoShopData);

        log.info("finish add item into shop request");
        serverResponse.setOk(true);
        serverResponse.setStatus(HttpStatus.OK);
        serverResponse.setType(dataHolder.getType());
        return serverResponse;
    }

    private void saveItemIntoShop(AddItemIntoShopData addItemIntoShopData){

        if (!Validator.valid(addItemIntoShopData.getOwnerId(),jdbcTemplateExecutor, OWNER)){
            log.info("ownerId " + addItemIntoShopData.getOwnerId() + " not registered");
            return;
        }

        jdbcTemplateExecutor.insertIntoItemInShop(addItemIntoShopData.getItemId(),
                addItemIntoShopData.getOwnerId(), 0);

    }
}
