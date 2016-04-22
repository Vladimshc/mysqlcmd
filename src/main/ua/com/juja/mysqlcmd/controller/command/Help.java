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

        view.write("\t'connect|databaseName|userName|password'\r\n"+
                "\t\t- for get connection to database");

        view.write("\t'list'\r\n"+
                "\t\t- for print list of all tables on base there we connected");

        view.write("\t'clear|tableName'\r\n"+
                "\t\t- for cleaning all tables values"); //TODO ошибочнный ввод команды? Подтверждение ввода команды.

        view.write("\t'create|tableName|colum1|value1|colum2|value2|...|columN|valueN'\r\n"+
                "\t\t- to create a record in the table");

        view.write("\t'help'\r\n"+
                "\t\t- for print help list on screen");

        view.write("\t'exit'\r\n"+
                "\t\t- for exit from program");

        view.write("\t'find|tableName'\r\n"+
                "\t\t- for print the contents of the table 'tableName'");
    }
}
