package com.epam.khrypushyna.shop.server.handler;

import com.epam.khrypushyna.shop.server.Utils;
import com.epam.khrypushyna.shop.server.adapter.TCPParametersAdapter;
import com.epam.khrypushyna.shop.service.CatalogService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TCPHandler implements Handler {

    private static final String REQUEST_LINE_REGEX = "\\d+\\.\\d+\\.\\d+\\.\\d+:\\d+\\?(getitem=\\d+|getcount)";
    private static final Pattern REQUEST_LINE_PATTERN = Pattern.compile(REQUEST_LINE_REGEX);
    private static final String NOT_FOUND_MESSAGE = "Wrong request, not found";

    private final Socket socket;
    private TCPParametersAdapter adapter;

    public TCPHandler(Socket socket, CatalogService catalogService) {
        this.socket = socket;
        adapter = new TCPParametersAdapter(catalogService);
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

    private byte[] getResponse(String parameters) {
        byte[] response;
        try{
            response = adapter.readParameters(parameters).getBytes();
        }
        catch (RuntimeException e){
            response = NOT_FOUND_MESSAGE.getBytes();
        }
        System.out.println(new String(response));
        return response;
    }
}
