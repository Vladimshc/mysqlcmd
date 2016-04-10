package ua.com.juja.mysqlcmd.controller;

import ua.com.juja.mysqlcmd.model.DataSet;
import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

import java.util.Arrays;

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


        while(true) {
            view.write("Wright command( or help)");
            String command = view.read();

            if (command.equals("list")) {
                doList();
            } else if (command.equals("help")) {
                doHelp();
            } else if (command.equals("exit")) {
                view.write("Bye");
                System.exit(0);
            } else if (command.startsWith("find|")) {
                doFind(command);
            } else view.write("Command doesn't exist: " + command);
        }
    }

    private void doFind(String command) {
        String[] data = command.split("[|]");
        String tableName = data[1];
        DataSet[] tableData = manager.getTableData(tableName);
        String[] tableColums = manager.getTableColums(tableName);
        printHeader(tableColums);
        printTable(tableData);
    }

    private void printTable(DataSet[] tableData) {
        for (DataSet row : tableData) {
           printRow(row);
        }
    }

    private void printRow(DataSet row) {
        Object[] values = row.getValues();
        String result = "|";
        for (Object value : values) {
            result +=value + "|";
        }
        view.write(result);
    }

    private void printHeader(String[] tableColums) {
        String result = "|";
        for (String name : tableColums) {
            result +=name + "|";
        }
        view.write("--------------");
        view.write(result);
        view.write("--------------");
    }

    private void doHelp() {
        view.write("Command for wright:");
        view.write("\t list " +
                "\n\t\t - for print list of oll tables on base there we connected");
        view.write("\t help " +
                "\n\t\t - for print help list on screen ");
        view.write("\t exit " +
                "\n\t\t - for exit from program ");
        view.write("\t find|tableName " +
                "\n\t\t - for print the contents of the table 'tableName' ");

    }

    private void doList() {
        String[] tableNemes = manager.getTableNames();
        String message = Arrays.toString(tableNemes);
        view.write(message);
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