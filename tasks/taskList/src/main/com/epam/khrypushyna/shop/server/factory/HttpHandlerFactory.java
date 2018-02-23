package com.epam.khrypushyna.shop.server.factory;

import com.epam.khrypushyna.shop.server.handler.HttpHandler;
import com.epam.khrypushyna.shop.server.handler.Handler;
import com.epam.khrypushyna.shop.service.CatalogService;

import java.net.Socket;

public class HttpHandlerFactory implements HandlerFactory {

    @Override
    public Handler createHandlerInstance(Socket socket, CatalogService catalogService) {
        return new HttpHandler(socket, catalogService);
    }
}
