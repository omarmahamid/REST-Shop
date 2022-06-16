package com.omar.assignment.handlers.delete;


import com.omar.assignment.annotation.SrvHandler;
import com.omar.assignment.apisdata.delete.RemoveCatalogData;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.handlers.ISrvDeleteHandler;
import com.omar.assignment.handlers.Validator;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.logging.Logger;

@SrvHandler(name = "removeCatalog")
public class SrvHandlerRemoveCatalog implements ISrvDeleteHandler {

    protected Logger log = Logger.getLogger(SrvHandlerRemoveCatalog.class.getName());
    private static final String REMOVE_CATALOG = "removeCatalog";
    private static final String OWNER = "owner";

    private JDBCTemplateExecutor jdbcTemplateExecutor;

    @Autowired
    public SrvHandlerRemoveCatalog(JDBCTemplateExecutor jdbcTemplateExecutor){
        this.jdbcTemplateExecutor = jdbcTemplateExecutor;
    }

    @Override
    public ServerResponse processRequest(DataHolder dataHolder) {
        ServerResponse serverResponse = new ServerResponse();

        if (dataHolder.getType() == null || !dataHolder.getType().equals(REMOVE_CATALOG)){
            log.info("type is not accepted");
            return null;
        }
        log.info("process remove catalog request...");

        RemoveCatalogData removeCatalogData = (RemoveCatalogData) dataHolder.getDataAPI();

        removeCatalog(removeCatalogData);

        log.info("finish remove catalog request");
        serverResponse.setOk(true);
        serverResponse.setType(dataHolder.getType());
        serverResponse.setStatus(HttpStatus.OK);

        return serverResponse;
    }

    private void removeCatalog(RemoveCatalogData removeCatalogData){

        if (!Validator.valid(removeCatalogData.getOwnerId(), jdbcTemplateExecutor, OWNER)){
            log.info("ownerId " + removeCatalogData.getOwnerId() + " not existed");
            return;
        }

        jdbcTemplateExecutor.removeCatalog(removeCatalogData.getCatalogId());
    }
}
