package com.epam.khrypushyna.shop.server.factory;

import com.epam.khrypushyna.shop.server.handler.Handler;
import com.epam.khrypushyna.shop.service.CatalogService;

import java.net.Socket;

public interface HandlerFactory {

    Handler createHandlerInstance(Socket socket, CatalogService catalogService);
}
