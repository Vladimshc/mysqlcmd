package ua.com.juja.mysqlcmd.controller.command;

import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 20.04.2016.
 */
public class FakeView implements View {
    private  String messages = "";

    @Override
    public void write(String message) {
        messages += message + "\n";

    }

    @Override
    public String read() {
        return null;
    }

    public String getContent() {
        return messages;
    }
}
