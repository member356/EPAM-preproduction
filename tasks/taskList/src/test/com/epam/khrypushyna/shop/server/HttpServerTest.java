package com.epam.khrypushyna.shop.server;

import com.epam.khrypushyna.shop.entity.Desk;
import com.epam.khrypushyna.shop.entity.Furniture;
import com.epam.khrypushyna.shop.server.handler.HttpHandler;
import com.epam.khrypushyna.shop.service.CatalogService;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HttpServerTest {

    private static final String REQUEST_COUNT = "GET /shop/count HTTP/1.1\n" +
            "Host: localhost:8080\n" +
            "Connection: keep-alive\r\n\r\n";

    private static final String REQUEST_ITEM = "GET /shop/item?get_info=1 HTTP/1.1\n" +
            "Host: localhost:8080\n" +
            "Connection: keep-alive\r\n\r\n";

    private InputStream in;
    private OutputStream out;

    private Socket socketMock = mock(Socket.class);
    private CatalogService catalogMock = mock(CatalogService.class);
    private HttpHandler handler = new HttpHandler(socketMock, catalogMock);

    @Before
    public void before() throws IOException {
        Map<Integer, Furniture> furnitureMap = new HashMap<>();
        furnitureMap.put(1, new Desk(900, true, 110));
        furnitureMap.put(2, new Desk(980, true, 90));

        when(catalogMock.getSize()).thenReturn(furnitureMap.size());
        when(catalogMock.getById(1)).thenReturn(furnitureMap.get(1));
    }

    @Test
    public void shouldReturnCountOfCatalogWhenGetCount() throws IOException {

        in = new ByteArrayInputStream(REQUEST_COUNT.getBytes());
        out = new ByteArrayOutputStream();
        when(socketMock.getInputStream()).thenReturn(in);
        when(socketMock.getOutputStream()).thenReturn(out);
        new Thread(handler).run();

        verify(socketMock).getInputStream();
        verify(socketMock).getOutputStream();
        String resp = String.valueOf(socketMock.getOutputStream());
        assertTrue(resp.endsWith("{\"count\":\"2\"}"));
    }

    @Test
    public void shouldReturnPriceOfItemWhenGetItem() throws IOException {

        in = new ByteArrayInputStream(REQUEST_ITEM.getBytes());
        out = new ByteArrayOutputStream();
        when(socketMock.getInputStream()).thenReturn(in);
        when(socketMock.getOutputStream()).thenReturn(out);
        new Thread(handler).run();

        verify(socketMock).getInputStream();
        verify(socketMock).getOutputStream();
        String resp = String.valueOf(socketMock.getOutputStream());
        assertTrue(resp.endsWith("{\"price\":900,\"name\":\"Desk\"}"));
    }

}