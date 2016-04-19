package ua.com.juja.mysqlcmd.controller.command;

import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 16.04.2016.
 */
public class Exit implements Command {

    private View view;

    public Exit(View view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("exit");
    }

    @Override
    public void process(String command) {
        view.write("Bye");
        throw new ExitException();
    }
}
