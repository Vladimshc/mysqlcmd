package ua.com.juja.mysqlcmd.controller.command;

import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.model.JDBCDatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

import java.util.Arrays;

/**
 * Created by Wallee on 16.04.2016.
 */
public class List implements Command {
    private DatabaseManager manager;
    private View view;

    public List(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("list");
    }

    @Override
    public void process(String command) {
        String[] tableNemes = manager.getTableNames();
        String message = Arrays.toString(tableNemes);
        view.write(message);
    }
}
