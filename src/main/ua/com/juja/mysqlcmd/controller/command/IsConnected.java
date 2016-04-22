package ua.com.juja.mysqlcmd.controller.command;

import ua.com.juja.mysqlcmd.controller.command.Command;
import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 18.04.2016.
 */
public class IsConnected implements Command {
    private DatabaseManager manager;
    private View view;

    public IsConnected(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return !manager.isConnected();
    }

    @Override
    public void process(String command) {
        view.write(String.format("You can not use the command '%s' " +
                "has not yet connect with the command: " +
                "'connect|databaseName|userName|password'", command));

    }
}
