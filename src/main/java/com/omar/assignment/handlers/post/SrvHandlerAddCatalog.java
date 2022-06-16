package com.omar.assignment.handlers.post;


import com.omar.assignment.annotation.SrvHandler;
import com.omar.assignment.apisdata.post.AddCatalogData;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.entities.Catalog;
import com.omar.assignment.handlers.ISrvPostHandler;
import com.omar.assignment.handlers.Validator;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.logging.Logger;

@SrvHandler(name = "AddCatalog")
public class SrvHandlerAddCatalog implements ISrvPostHandler {

    protected Logger log = Logger.getLogger(SrvHandlerAddCatalog.class.getName());
    private static final String ADD_CATALOG = "addCatalog";
    private static final String OWNER = "owner";
    private JDBCTemplateExecutor jdbcTemplateExecutor;

    @Autowired
    public SrvHandlerAddCatalog(JDBCTemplateExecutor jdbcTemplateExecutor){
        this.jdbcTemplateExecutor = jdbcTemplateExecutor;
    }

    // the logic for the addCatalog request
    @Override
    public ServerResponse processRequest(DataHolder dataHolder) {
        ServerResponse serverResponse = new ServerResponse();

        if (dataHolder.getType() == null || !dataHolder.getType().equals(ADD_CATALOG)){
            return null;
        }

        log.info("process add catalog request...");
        AddCatalogData addCatalogData = (AddCatalogData) dataHolder.getDataAPI();

        saveCatalog(addCatalogData);

        log.info("finish add catalog request");
        serverResponse.setOk(true);
        serverResponse.setStatus(HttpStatus.OK);
        serverResponse.setType(dataHolder.getType());
        return serverResponse;
    }

    private void saveCatalog(AddCatalogData addCatalogData){

        if (!Validator.valid(addCatalogData.getOwnerId(),jdbcTemplateExecutor, OWNER)){
            log.info("owner " + addCatalogData.getOwnerId() + " not registered");
            return;
        }

        Catalog catalog = addCatalogData.getCatalog();
        jdbcTemplateExecutor.insertIntoCatalog(catalog.getCatalogId(),catalog.getCatalogName());
    }
}
