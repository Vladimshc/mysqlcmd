package ua.com.juja.mysqlcmd.model;

import java.util.Arrays;

/**
 * Created by Wallee on 05.04.2016.
 */
public  class InMemoryDatabaseManager implements DatabaseManager {

    public static final String TABLE_NAME = "user";

    private DataSet[] data = new DataSet[1000];
    private int freeIndex = 0;

    @Override
    public DataSet[] getTableData(String tableName) {
        return Arrays.copyOf(data, freeIndex);
    }

    @Override
    public String[] getTableNames() {
        return new String[]{TABLE_NAME};
    }

    @Override
    public void connect(String database, String userName, String password) {
        // do nothing
    }

    @Override
    public void clear(String tableName) {
        data = new DataSet[1000];
        freeIndex = 0;
    }

    @Override
    public void create(String tableName, DataSet input) {
        data[freeIndex] = input;
        freeIndex++;
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        for (int index = 0; index < freeIndex; index++) {
            if (Integer.valueOf(id).equals(data[index].get("id"))) {
                data[index].updateFrom(newValue);
            }

        }

    }

    @Override
    public String[] getTableColums(String tableName) {
        return new String[] {"name", "password", "id"};
    }
}

//2- 0:24