package com.epam.khrypushyna.shop.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Utils {

    public static byte[] readRequest(InputStream inputStream) throws IOException {
        byte[] buff = new byte[1024];
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        while (true) {
            int count = inputStream.read(buff);
            result.write(buff, 0, count);
            if (isRequestEnd(buff, count)) {
                return result.toByteArray();
            }
        }
    }

    public static byte[] createHttpResponse(String response) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        Writer writer = new OutputStreamWriter(result, "ISO-8859-1");
        writer.write(response);
        writer.flush();

        return result.toByteArray();
    }


    private static boolean isRequestEnd(byte[] request, int length) {
        return length >= 4 && request[length - 4] == '\r' && request[length - 3] == '\n' && request[length - 2] == '\r' && request[length - 1] == '\n';
    }

}
