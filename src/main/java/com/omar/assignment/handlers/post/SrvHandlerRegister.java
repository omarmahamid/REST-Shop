package com.omar.assignment.handlers.post;

import com.omar.assignment.annotation.SrvHandler;
import com.omar.assignment.apisdata.post.register.RegisterCustomerData;
import com.omar.assignment.apisdata.post.register.RegisterOwnerData;
import com.omar.assignment.apisdata.post.register.RegisterSellerData;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.handlers.ISrvPostHandler;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.logging.Logger;


@SrvHandler(name = "Register")
public class SrvHandlerRegister implements ISrvPostHandler {

    protected Logger log = Logger.getLogger(SrvHandlerRegister.class.getName());


    private static final String REGISTER_SELLER = "registerSeller";
    private static final String REGISTER_OWNER = "registerOwner";
    private static final String REGISTER_CUSTOMER = "registerCustomer";

    private JDBCTemplateExecutor jdbcTemplateExecutor;

    @Autowired
    public SrvHandlerRegister(JDBCTemplateExecutor jdbcTemplateExecutor){
        this.jdbcTemplateExecutor = jdbcTemplateExecutor;
    }

    @Override
    public ServerResponse processRequest(DataHolder dataHolder) {

        String type = dataHolder.getType();
        log.info("process " + type + " request...");
        switch (type){
            case REGISTER_CUSTOMER:
                return handlerRegisterCustomer(dataHolder);
            case REGISTER_SELLER:
                return handleRegisterSeller(dataHolder);
            case REGISTER_OWNER:
                return handlerRegisterOwner(dataHolder);
        }
        return null;
    }


    private ServerResponse handleRegisterSeller(DataHolder dataHolder){
        ServerResponse serverResponse = new ServerResponse();

        RegisterSellerData registerSellerData = (RegisterSellerData) dataHolder.getDataAPI();

        jdbcTemplateExecutor.insertIntoSellers(registerSellerData.getId(), registerSellerData.getName(),
                registerSellerData.getEmail());

        log.info("finish " + dataHolder.getType() + " request");
        serverResponse.setType(dataHolder.getType());
        serverResponse.setStatus(HttpStatus.OK);
        serverResponse.setOk(true);
        return serverResponse;
    }

    private ServerResponse handlerRegisterOwner(DataHolder dataHolder){
        ServerResponse serverResponse = new ServerResponse();

        RegisterOwnerData registerOwnerData = (RegisterOwnerData) dataHolder.getDataAPI();

        jdbcTemplateExecutor.insertIntoOwners(registerOwnerData.getId(),registerOwnerData.getName(),
                registerOwnerData.getEmail());

        log.info("finish " + dataHolder.getType() + " request");
        serverResponse.setType(dataHolder.getType());
        serverResponse.setStatus(HttpStatus.OK);
        serverResponse.setOk(true);
        return serverResponse;
    }

    private ServerResponse handlerRegisterCustomer(DataHolder dataHolder){
        ServerResponse serverResponse = new ServerResponse();

        RegisterCustomerData registerCustomerData = (RegisterCustomerData) dataHolder.getDataAPI();

        jdbcTemplateExecutor.insertIntoCustomers(registerCustomerData.getId(),
                registerCustomerData.getName(), registerCustomerData.getEmail());

        log.info("finish " + dataHolder.getType() + " request");
        serverResponse.setType(dataHolder.getType());
        serverResponse.setStatus(HttpStatus.OK);
        serverResponse.setOk(true);
        return serverResponse;
    }
}
