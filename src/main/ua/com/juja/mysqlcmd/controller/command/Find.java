package ua.com.juja.mysqlcmd.controller.command;

import ua.com.juja.mysqlcmd.controller.command.Command;
import ua.com.juja.mysqlcmd.model.DataSet;
import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 18.04.2016.
 */
public class Find implements Command {
    private DatabaseManager manager;
    private View view;

    public Find(DatabaseManager manager, View view) {
        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("find|");
    }

    @Override
    public void process(String command) {
            String[] data = command.split("[|]");
            String tableName = data[1];
            DataSet[] tableData = manager.getTableData(tableName);
            String[] tableColums = manager.getTableColums(tableName);
            printHeader(tableColums);
            printTable(tableData);
    }

    private void printTable(DataSet[] tableData) {
        for (DataSet row : tableData) {
            printRow(row);
        }
    }
    private void printHeader(String[] tableColums) {
        String result = "|";
        for (String name : tableColums) {
            result +=name + "|";
        }
        view.write("--------------");
        view.write(result);
        view.write("--------------");
    }
    private void printRow(DataSet row) {
        Object[] values = row.getValues();
        String result = "|";
        for (Object value : values) {
            result +=value + "|";
        }
        view.write(result);
    }


}
