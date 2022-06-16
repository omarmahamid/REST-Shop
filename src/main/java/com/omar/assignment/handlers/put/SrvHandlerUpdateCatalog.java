package com.omar.assignment.handlers.put;


import com.omar.assignment.annotation.SrvHandler;
import com.omar.assignment.apisdata.put.UpdateCatalogData;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.handlers.ISrvPutHandler;
import com.omar.assignment.handlers.Validator;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.logging.Logger;

@SrvHandler(name = "UpdateCatalog")
public class SrvHandlerUpdateCatalog implements ISrvPutHandler {

    protected Logger log = Logger.getLogger(SrvHandlerUpdateCatalog.class.getName());

    private static final String UPDATE_CATALOG = "updateCatalog";
    private static final String OWNER = "owner";
    private JDBCTemplateExecutor jdbcTemplateExecutor;

    @Autowired
    public SrvHandlerUpdateCatalog(JDBCTemplateExecutor jdbcTemplateExecutor){
        this.jdbcTemplateExecutor = jdbcTemplateExecutor;
    }


    @Override
    public ServerResponse processRequest(DataHolder dataHolder) {

        ServerResponse serverResponse = new ServerResponse();

        if (dataHolder.getType() == null || !dataHolder.getType().equals(UPDATE_CATALOG)) {
            return null;
        }
        log.info("process update catalog ...");

        UpdateCatalogData updateCatalogData = (UpdateCatalogData) dataHolder.getDataAPI();

        updateCatalog(updateCatalogData);

        log.info("finish update catalog.");
        serverResponse.setType(dataHolder.getType());
        serverResponse.setOk(true);
        serverResponse.setStatus(HttpStatus.OK);
        return serverResponse;
    }

    private void updateCatalog(UpdateCatalogData updateCatalogData){
        if (!Validator.valid(updateCatalogData.getOwnerId(),jdbcTemplateExecutor, OWNER)){
            log.info("ownerId " + updateCatalogData.getOwnerId() + " not found");
            return;
        }
        if (!checkIfCatalogExist(updateCatalogData.getCatalogId())){
            log.info("catalogId " + updateCatalogData.getCatalogId() + " not found");
            return;
        }
        jdbcTemplateExecutor.updateCatalog(updateCatalogData.getCatalogId(),updateCatalogData.getCatalogName());
    }

    private boolean checkIfCatalogExist(long catalogId){
        List<?> catalogs = jdbcTemplateExecutor.getCatalogById(catalogId);
        return catalogs != null && !catalogs.isEmpty();
    }
}
