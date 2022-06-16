package com.omar.assignment.handlers.delete;


import com.omar.assignment.annotation.SrvHandler;
import com.omar.assignment.apisdata.delete.RemoveItemData;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.handlers.ISrvDeleteHandler;
import com.omar.assignment.handlers.Validator;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.logging.Logger;

@SrvHandler(name = "RemoveItem")
public class SrvHandlerRemoveItem implements ISrvDeleteHandler {

    protected Logger log = Logger.getLogger(SrvHandlerRemoveItem.class.getName());
    private static final String REMOVE_ITEM = "removeItem";
    private static final String OWNER = "owner";

    private JDBCTemplateExecutor jdbcTemplateExecutor;

    @Autowired
    public SrvHandlerRemoveItem(JDBCTemplateExecutor jdbcTemplateExecutor){
        this.jdbcTemplateExecutor = jdbcTemplateExecutor;
    }


    @Override
    public ServerResponse processRequest(DataHolder dataHolder) {
        ServerResponse serverResponse = new ServerResponse();

        if (dataHolder.getType() == null || !dataHolder.getType().equals(REMOVE_ITEM)){
            return null;
        }

        log.info("process remove item");

        RemoveItemData removeItemData = (RemoveItemData) dataHolder.getDataAPI();

        removeItem(removeItemData);

        log.info("finish remove item");
        serverResponse.setType(dataHolder.getType());
        serverResponse.setOk(true);
        serverResponse.setStatus(HttpStatus.OK);
        return serverResponse;
    }

    private void removeItem(RemoveItemData removeItemData){
        if (!Validator.valid(removeItemData.getOwnerId(), jdbcTemplateExecutor, OWNER)){
            log.info("ownerId " + removeItemData.getOwnerId() + " not exist");
            return;
        }
        jdbcTemplateExecutor.removeItem(removeItemData.getItemId());
    }
}
