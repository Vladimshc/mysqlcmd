package ua.com.juja.mysqlcmd.controller;

import ua.com.juja.mysqlcmd.controller.command.*;
import ua.com.juja.mysqlcmd.model.DataSet;
import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 07.04.2016.
 */
public class MainController {

    private Command[] commands;
    private View view;
    private DatabaseManager manager;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
        this.commands = new Command[] {new Exit(view), new Help(view),
                new List(manager, view), new Find(manager,view)};
    }

    public void run(){
        connectToDb();

        while(true) {
            view.write("Wright command( or help)");
            String command = view.read();

            if (commands[2].canProcess(command)) {
                commands[2].process(command);
            } else if (commands[1].canProcess(command)) {
                commands[1].process(command);
            } else if (commands[0].canProcess(command)) {
                commands[0].process(command);
            } else if (commands[3].canProcess(command)) {
                commands[3].process(command);
            } else view.write("Command doesn't exist: " + command);
        }
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