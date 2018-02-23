package com.epam.khrypushyna.shop.server;

import com.epam.khrypushyna.shop.Application;
import com.epam.khrypushyna.shop.server.client.HTTPClient;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerStartTest {

    private HTTPClient HttpClient = new HTTPClient();
    private TCPClient TCPClient = new TCPClient();

    @Before
    public void before() {
        new Thread(() -> Application.main(new String[]{})).start();
    }

    @Test
    public void shouldReturnAmountOfCatalogWhenGetCountHttpServer() throws IOException {
        String resp = HttpClient.getMethod("localhost", 8080, "shop/count");
        assertTrue(resp.endsWith("{\"count\":\"18\"}"));
    }

    @Test
    public void shouldReturnPriceOfItemWhenGetItemHttpServer() throws IOException {
        String resp = HttpClient.getMethod("localhost", 8080, "shop/item?get_info=7");
        assertTrue(resp.endsWith("{\"price\":13043,\"name\":\"Desk\"}"));
    }

    @Test
    public void shouldReturnAmountOfCatalogWhenGetCountTCPServer() throws IOException {
        String resp = TCPClient.getMethod("localhost", 8081, "?getcount");
        assertEquals(new Integer(18), Integer.valueOf(resp));
    }

    @Test
    public void shouldReturnPriceOfItemWhenGetItemTCPServer() throws IOException {
        String resp = TCPClient.getMethod("localhost", 8081, "?getitem=4");
        assertEquals("Couch id = 4 | price = 10864", resp);
    }

}