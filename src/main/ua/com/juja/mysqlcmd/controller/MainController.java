package ua.com.juja.mysqlcmd.controller;

import ua.com.juja.mysqlcmd.controller.command.*;
import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 07.04.2016.
 */
public class MainController {

    private Command[] commands;
    private View view;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.commands = new Command[] {
                new Connect(manager, view),
                new Help(view),
                new Exit(view),
                new IsConnected(manager, view),
                new List(manager, view),
                new Clear(manager, view),
                new Create(manager, view),
                new Find(manager,view),
                new Unsupported(view)};
    }

    public void run(){
        view.write("Привет!!! Hi, user!!!");
        view.write("Write base name and password in format: connect|database|userName|password");

        try {
            doWork();
        } catch (ExitException e) {
//            do nothing
        }
    }

    public void doWork() {
        while (true) {
            String input = view.read();
            if (input == null) { //null if close application
                new Exit(view).process(input);
            }
            for (Command command : commands) {
                if (command.canProcess(input)) {
                    command.process(input);
                    break;
                }
            }
            view.write("Wright command (or help)");
        }
    }
}
