package ua.com.juja.mysqlcmd.controller.command;

import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 19.04.2016.
 */
public class Clear implements Command {
    private DatabaseManager manager;
    private View view;

    public Clear(DatabaseManager manager, View view) {

        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("clear");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("[|]");
        if (data.length != 2 ) {
            throw new IllegalArgumentException(String.format("Command format 'clear|tableName', but you have: '%s'", command));
        }
        manager.clear(data[1]);

        view.write(String.format("Table '%s' was cleaning successful!!!", data[1]));
    }
}
