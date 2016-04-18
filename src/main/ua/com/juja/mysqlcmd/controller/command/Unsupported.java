package ua.com.juja.mysqlcmd.controller.command;

import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 18.04.2016.
 */
public class Unsupported implements Command {

    private View view;

    public Unsupported(View view) {

        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return true;
    }

    @Override
    public void process(String command) {
        view.write("Command doesn't exist: " + command);
    }
}
