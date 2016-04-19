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
        view.write("Command for wright:\r\n");

        view.write("\tconnect|databaseName|userName|password\r\n"+
                "\t\t- for get connection to database\r\n");

        view.write("\tlist\r\n"+
                "\t\t- for print list of all tables on base there we connected\r\n");

        view.write("\tclear|tableName\r\n"+
                "\t\t- for cleaning all tables values\r\n"); //TODO ошибочнный ввод команды? Подтверждение ввода команды.

        view.write("\tcreate|tableName|colum1|value1|colum2|value2|...|columN|valueN\r\n"+
                "\t\t- to create a record in the table\r\n");

        view.write("\thelp\r\n"+
                "\t\t- for print help list on screen\r\n");

        view.write("\texit\r\n"+
                "\t\t- for exit from program\r\n");

        view.write("\tfind|tableName\r\n"+
                "\t\t- for print the contents of the table 'tableName'\r\n");
    }
}
