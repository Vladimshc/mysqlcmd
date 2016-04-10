package ua.com.juja.mysqlcmd.model;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wallee on 07.04.2016.
 */
public class InMemoryDatabaseManagerTest extends DatabaseManagerTest {

    public DatabaseManager getDatabaseManager() {
        return new InMemoryDatabaseManager();
    }

    @Override
    public void testGetAllTableNames() {
        String[] tableNemes = manager.getTableNames();
        assertEquals("[user]", Arrays.toString(tableNemes));
    }
}
