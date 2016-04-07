package ua.com.juja.mysqlcmd.view;

/**
 * Created by Wallee on 07.04.2016.
 */
public interface View {
    void write(String message);

    String read();
}
