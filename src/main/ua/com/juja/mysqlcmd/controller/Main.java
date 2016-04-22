package ua.com.juja.mysqlcmd.controller;

import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.model.JDBCDatabaseManager;
import ua.com.juja.mysqlcmd.view.Console;
import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 07.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        View view = new Console();
        DatabaseManager manager = new JDBCDatabaseManager();
        MainController controller = new MainController(view, manager);

        controller.run();
//        connect|mysqlcmd|postgres|12345
//        connect|DBforTest|postgres|12345
    }
}
