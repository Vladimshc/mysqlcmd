package ua.com.juja.mysqlcmd.controller.command;

import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 16.04.2016.
 */
public class Help implements Command {
    private View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.equals("help");
    }

    @Override
    public void process(String command) {
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
}
