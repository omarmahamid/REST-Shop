package com.omar.assignment.handlers;

import com.omar.assignment.request.DataHolder;
import com.omar.assignment.response.ServerResponse;

public interface ISrvHandler {
    ServerResponse processRequest(DataHolder dataHolder);

}
