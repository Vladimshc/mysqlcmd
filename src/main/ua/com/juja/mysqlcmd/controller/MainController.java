package ua.com.juja.mysqlcmd.controller;

import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.model.JDBCDatabaseManager;
import ua.com.juja.mysqlcmd.view.Console;
import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 07.04.2016.
 */
public class MainController {
    public static void main(String[] args) {
        View viev = new Console();
        DatabaseManager manager = new JDBCDatabaseManager();

        viev.write("Привет - Hi, user");
        viev.write("Write base name and password in format: database|userName|password ");
        while (true) {
            String string = viev.read();
            String[] data = string.split("[|]");
            String databaseName = data[0];
            String userName = data[1];
            String password = data[2];
            try {
                manager.connect(databaseName, userName, password);
                break;
            } catch (Exception e) {
                String message = e.getMessage();
                if (e.getCause() != null) {
                    message += " " + e.getCause().getMessage();
                }
                viev.write("No connect!!! Details: ");
                viev.write(e.getMessage() + message);
                viev.write("Please try again.");
            }
        }
        viev.write("Ok! Connect successful.");
    }


}
