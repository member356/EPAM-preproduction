package com.epam.khrypushyna.shop.server;

import com.epam.khrypushyna.shop.Application;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) throws IOException {
        new Thread(() -> Application.main(new String[]{})).start();

        TCPClient client = new TCPClient();
        client.getMethod("localhost", 8081, "?getcount");
        client.getMethod("localhost", 8081, "?getitem=7");
    }

    public String getMethod(String host, int port, String path)
            throws IOException {

        try (Socket clientSocket = new Socket(host, port);
             PrintWriter request = new PrintWriter(clientSocket.getOutputStream(), true);
             Scanner response = new Scanner(new InputStreamReader(clientSocket.getInputStream()))) {
            request.print(clientSocket.getInetAddress().getHostAddress() +
                    ":" + clientSocket.getLocalPort() +
                    path + "\r\n\r\n");
            request.flush();

            StringBuilder resp = new StringBuilder();
            while (response.hasNextLine()) {
                resp.append(response.nextLine());
            }
            return resp.toString();
        }
    }

}
