package ua.com.juja.mysqlcmd.controller;

import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 07.04.2016.
 */
public class MainController {

    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public void run(){
        connectToDb();
    }

    private void connectToDb() {
        view.write("Привет - Hi, user");
        view.write("Write base name and password in format: database|userName|password ");
        while (true) {
            try {
                String string = view.read();
                String[] data = string.split("[|]");
                if (data.length != 3) {
                    throw new IllegalArgumentException("Missing parameters '|', need 3 but wright: " + data.length +" ");
                }
                String databaseName = data[0];
                String userName = data[1];
                String password = data[2];
                manager.connect(databaseName, userName, password);
                break;
            } catch (Exception e) {
                printError(e);
            }
        }
        view.write("Ok! Connect successful.");
    }

    private void printError(Exception e) {
        String message = e.getMessage();
        if (e.getCause() != null) {
            message += " " + e.getCause().getMessage();
        }
        view.write("No connect!!! Details: ");
        view.write(e.getMessage() + message);
        view.write("Please try again.");
    }


}
// 2- 1:40