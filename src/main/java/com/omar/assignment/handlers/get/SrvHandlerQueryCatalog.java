package com.omar.assignment.handlers.get;


import com.omar.assignment.annotation.SrvHandler;
import com.omar.assignment.apisdata.get.QueryCatalogData;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.entities.ItemToShop;
import com.omar.assignment.handlers.ISrvGetHandler;
import com.omar.assignment.handlers.Validator;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.logging.Logger;

@SrvHandler(name = "QueryCatalog")
public class SrvHandlerQueryCatalog implements ISrvGetHandler {

    protected Logger log = Logger.getLogger(SrvHandlerQueryCatalog.class.getName());
    private final static String QUERY_CATALOG = "queryCatalog";
    private final static String CUSTOMER = "customer";

    private JDBCTemplateExecutor jdbcTemplateExecutor;

    @Autowired
    public SrvHandlerQueryCatalog(JDBCTemplateExecutor jdbcTemplateExecutor){
        this.jdbcTemplateExecutor = jdbcTemplateExecutor;
    }

    @Override
    public ServerResponse processRequest(DataHolder dataHolder) {
        ServerResponse serverResponse = new ServerResponse();
        if (dataHolder.getType() == null || !dataHolder.getType().equals(QUERY_CATALOG)){
            return null;
        }

        QueryCatalogData catalogData = (QueryCatalogData)dataHolder.getDataAPI();

        if(!Validator.valid(catalogData.getCustomerId(), jdbcTemplateExecutor, CUSTOMER)){
            log.info("customer not found");
            return null;
        }

        log.info("process queryCatalog request ...");
        long catalogId = catalogData.getCatalogId();

        List<ItemToShop> items = jdbcTemplateExecutor.getItemsByCatalogId(catalogId);

        log.info("finish queryCatalog request");
        serverResponse.setStatus(HttpStatus.OK);
        serverResponse.setOk(true);
        serverResponse.setType(dataHolder.getType());
        serverResponse.setEntities(items);

        return serverResponse;
    }

}
