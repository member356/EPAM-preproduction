package com.epam.khrypushyna.shop.server;

import com.epam.khrypushyna.shop.server.factory.HandlerFactory;
import com.epam.khrypushyna.shop.service.CatalogService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerStart {

    private static final int CORE_POOL_SIZE = 4;
    private static final int MAXIMUM_POOL_SIZE = 64;
    private static final long KEEP_ALIVE = 60L;
    private static final int CAPACITY = 4;
    private CatalogService catalogService;
    private ExecutorService threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
            KEEP_ALIVE, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(CAPACITY));

    public ServerStart(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public void run(int port, HandlerFactory handlerFactory) {

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println(handlerFactory.getClass().getSimpleName() + " starts on port " + port + System.lineSeparator());

                while (true) {
                    final Socket socket = serverSocket.accept();
                    threadPool.submit(handlerFactory.createHandlerInstance(socket, catalogService));
                }
            } catch (IOException e) {
                System.err.println("Exception while using connection");
            }
        }).start();
    }

}

