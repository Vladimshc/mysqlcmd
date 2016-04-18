package ua.com.juja.mysqlcmd.controller.command;

import ua.com.juja.mysqlcmd.controller.command.Command;
import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 18.04.2016.
 */
public class Connect implements Command {
    private DatabaseManager manager;
    private View view;

    public Connect(DatabaseManager manager, View view) {

        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("connect|");
    }

    @Override
    public void process(String command) {
        try {
            String[] data = command.split("[|]");
            if (data.length != 4) {
                throw new IllegalArgumentException("Missing parameters '|', need 4 but wright: " + data.length + " ");
            }
            String databaseName = data[1];
            String userName = data[2];
            String password = data[3];
            manager.connect(databaseName, userName, password);
            view.write("Ok! Connect successful.");
        } catch (Exception e) {
            printError(e);
        }
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