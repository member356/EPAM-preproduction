package com.epam.khrypushyna.shop.server.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class HTTPClient {

    public String getMethod(String host, int port, String path)
            throws IOException {

        Socket clientSocket = null;
        PrintWriter request = null;
        Scanner response = null;

        try {
            clientSocket = new Socket(host, port);
            request = new PrintWriter(clientSocket.getOutputStream(), true);
            response = new Scanner(new InputStreamReader(clientSocket.getInputStream()));

            request.print("GET /" + path + " HTTP/1.1\r\n");
            request.print("Host: " + host + "\r\n");
            request.print("Connection: close\r\n");
            request.print("Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\r\n");
            request.print("\r\n");
            request.flush();

            StringBuilder resp = new StringBuilder();
            while (response.hasNextLine()) {
                resp.append(response.nextLine());
            }
            return resp.toString();
        } finally {
            response.close();
            request.close();
            clientSocket.close();
        }
    }
}
