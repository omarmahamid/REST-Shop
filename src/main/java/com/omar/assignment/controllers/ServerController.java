package com.omar.assignment.controllers;

import com.omar.assignment.common.SpringInjector;
import com.omar.assignment.dal.JDBCTemplateExecutor;
import com.omar.assignment.exception.TypeAPINotFoundException;
import com.omar.assignment.handlers.*;
import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * A RESTFul controller providing the whole server, not providing for all api the adjust controller
 */
@RestController
@RequestMapping(path = "/server", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServerController {

	protected Logger log = Logger.getLogger(ServerController.class.getName());

	@Autowired
	protected JDBCTemplateExecutor jdbcTemplateExecutor;


	@GetMapping("/execute")
	public ServerResponse get(@RequestBody final String body, @RequestHeader final Map<String,String> headers) throws IOException {
		log.info("received GET request");
		DataHolder dataHolder = createDataHolder(body);
		ServerResponse serverResponse;
		try {
			// SrvGetHandlers
			serverResponse = createResponse(dataHolder, ISrvGetHandler.class);
		}catch (TypeAPINotFoundException e){
			throw new TypeAPINotFoundException(dataHolder.getType());
		}

		return serverResponse;
	}


    @PostMapping("/execute")
    public ServerResponse create(@RequestBody final String body, @RequestHeader final Map<String,String> headers) throws IOException{
    	log.info("received POST request ");
		DataHolder dataHolder = createDataHolder(body);
		ServerResponse serverResponse;
		try {
			// SrvPostHandlers
			serverResponse = createResponse(dataHolder, ISrvPostHandler.class);
		}catch (TypeAPINotFoundException e){
			throw new TypeAPINotFoundException(dataHolder.getType());
		}
        return serverResponse;
    }

    @PutMapping("/execute")
	public ServerResponse put(@RequestBody final String body, @RequestHeader final Map<String,String> headers) throws IOException {
		log.info("received PUT request ");
		DataHolder dataHolder = createDataHolder(body);
		ServerResponse serverResponse;
		try {
			// SrvPostHandlers
			serverResponse = createResponse(dataHolder, ISrvPutHandler.class);
		}catch (TypeAPINotFoundException e){
			throw new TypeAPINotFoundException(dataHolder.getType());
		}
		return serverResponse;
	}


    @DeleteMapping("/execute")
	public ServerResponse delete(@RequestBody final String body, @RequestHeader final Map<String, String> headers) throws IOException {
    	log.info("received DELETE request");
		DataHolder dataHolder = createDataHolder(body);
		ServerResponse serverResponse;
		try {
			// SrvPostHandlers
			serverResponse = createResponse(dataHolder, ISrvDeleteHandler.class);
		}catch (TypeAPINotFoundException e){
			throw new TypeAPINotFoundException(dataHolder.getType());
		}
    	return serverResponse;
	}

	private DataHolder createDataHolder(String body){
		DataHolder dataHolder = new DataHolder(body);
		dataHolder.parse();
		return dataHolder;
	}


	private ServerResponse createResponse(DataHolder dataHolder, Class<?> clazz) throws IOException {
		ServerResponse serverResponse = null;
		List<?> handlers = new ArrayList<>(SpringInjector.getAllInstancesOfType(clazz));
		for(Object handler : handlers){
			serverResponse = ((ISrvHandler)handler).processRequest(dataHolder);
			if(serverResponse != null){
				return serverResponse;
			}
		}
		log.info("cannot process this api");
		log.info("please make sure that the api is suitable for REST doc, or make sure with typeId");
		return null;
	}

}
