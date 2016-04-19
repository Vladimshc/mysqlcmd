package ua.com.juja.mysqlcmd.integration;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Wallee on 18.04.2016.
 */
public class LogOutputStream extends OutputStream {

    private String log;

    @Override
    public void write(int b) throws IOException {
        byte[] bytes = new byte[] {(byte)( b & 0xFF00 >> 8 ) , (byte)(b & 0x00FF)};
        log += new String(bytes, "UTF-8");

    }

    public String getData() {
        return log;
    }
}
