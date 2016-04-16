package ua.com.juja.mysqlcmd.controller.command;

/**
 * Created by Wallee on 16.04.2016.
 */
public interface Command {

    boolean canProcess (String command);

    void process (String command);


}
