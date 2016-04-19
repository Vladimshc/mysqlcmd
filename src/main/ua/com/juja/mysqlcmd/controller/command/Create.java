package ua.com.juja.mysqlcmd.controller.command;

import ua.com.juja.mysqlcmd.model.DataSet;
import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

/**
 * Created by Wallee on 19.04.2016.
 */
public class Create implements Command {
    private DatabaseManager manager;
    private View view;

    public Create(DatabaseManager manager, View view) {

        this.manager = manager;
        this.view = view;
    }

    @Override
    public boolean canProcess(String command) {
        return command.startsWith("create|");
    }

    @Override
    public void process(String command) {
        String[] data = command.split("[|]");
        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Должно быть четное количество параметров в формате: " +
                    "'create|tableName|colum1|value1|colum2|value2|...|columN|valueN' .");
        }

        DataSet dataSet = new DataSet();
        String tableName = data[1];

        for (int index = 1; index < data.length / 2 ; index++) {
            String columnName =  data[index*2];
            String value = data[index*2 + 1];

            dataSet.put(columnName, value);
        }
        manager.create(tableName, dataSet);

        view.write(String.format("Record %s was created successfully in table %s!!!", dataSet, tableName ));

    }
}
