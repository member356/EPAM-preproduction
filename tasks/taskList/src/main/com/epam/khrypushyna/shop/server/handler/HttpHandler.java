package com.epam.khrypushyna.shop.server.handler;

import com.epam.khrypushyna.shop.server.Utils;
import com.epam.khrypushyna.shop.server.adapter.HttpParametersAdapter;
import com.epam.khrypushyna.shop.service.CatalogService;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpHandler implements Handler {

    private static final String REQUEST_LINE_REGEX = "GET\\s\\/shop\\/(item\\?get_info=\\d+|count)\\sHTTP\\/1.1";
    private static final Pattern REQUEST_LINE_PATTERN = Pattern.compile(REQUEST_LINE_REGEX);

    private final Socket socket;
    private HttpParametersAdapter adapter;

    private static final String RESPONSE_200 = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html; charset=ISO-8859-1\r\n" +
            "Content-Language: en\r\n" +
            "Connection: close\r\n\r\n";

    private static final String RESPONSE_404 = "HTTP/1.1 404 Not Found\r\n" +
            "Content-Type: text/html; charset=ISO-8859-1\r\n" +
            "Content-Language: en\r\n" +
            "Connection: close\r\n\r\n" +
            "Wrong request, not found";

    public HttpHandler(Socket socket, CatalogService catalogService) {
        this.socket = socket;
        adapter = new HttpParametersAdapter(catalogService);
    }

    @Override
    public void run() {
        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()) {

            byte[] request = Utils.readRequest(in);
            String req = new String(request, "ISO-8859-1");
            System.out.println(req);

            String parameters = null;
            Matcher headerMatcher = REQUEST_LINE_PATTERN.matcher(req);
            if (headerMatcher.find()) {
                parameters = headerMatcher.group(1);
            }
            out.write(getResponse(parameters));
        } catch (IOException e) {
            System.err.println("Exception during input or output stream working");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Failed while closing socket connection");
            }
        }
    }

    private byte[] getResponse(String parameters) throws IOException {
        byte[] response;
        try {
            response = Utils.createHttpResponse(RESPONSE_200 + adapter.readParameters(parameters));
        } catch (RuntimeException e) {
            response = Utils.createHttpResponse(RESPONSE_404);
        }
        System.out.println(new String(response));
        return response;
    }
}
