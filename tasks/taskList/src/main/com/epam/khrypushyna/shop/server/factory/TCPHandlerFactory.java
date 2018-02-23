package com.epam.khrypushyna.shop.server.factory;

import com.epam.khrypushyna.shop.server.handler.Handler;
import com.epam.khrypushyna.shop.server.handler.TCPHandler;
import com.epam.khrypushyna.shop.service.CatalogService;

import java.net.Socket;

public class TCPHandlerFactory implements HandlerFactory {

    @Override
    public Handler createHandlerInstance(Socket socket, CatalogService catalogService) {
        return new TCPHandler(socket, catalogService);
    }
}
