package ua.com.juja.mysqlcmd.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Walle on 01.03.2016.
 */
public abstract class DatabaseManagerTest {

    protected DatabaseManager manager;

    public abstract DatabaseManager getDatabaseManager();

    @Before
    public void setup() {
        manager = getDatabaseManager();
        manager.connect("mysqlcmd", "postgres", "12345");
    }


    @Test
    public abstract void testGetAllTableNames();

    @Test
    public void testGetTableData() {
        //given
        manager.clear("user");

        //when
        DataSet input = new DataSet();
        input.put("name", "Masha");
        input.put("password", "pass");
        input.put("id", 13);
        manager.create("user", input);


        //then
        DataSet[] users = manager.getTableData("user");
        assertEquals(1, users.length);

        DataSet user = users[0];
        assertEquals("[name, password, id]", Arrays.toString(user.getNames()));
        assertEquals("[Masha, pass, 13]", Arrays.toString(user.getValues()));
    }

    @Test
    public void testUpdateTableData() {
        //given
        manager.clear("user");
        DataSet input = new DataSet();
        input.put("name", "Masha");
        input.put("password", "pass");
        input.put("id", 13);
        manager.create("user", input);

        //when
        DataSet newValue = new DataSet();
        newValue.put("password", "pass2");
        manager.update("user", 13, newValue);

        //then
        DataSet[] users = manager.getTableData("user");
        assertEquals(1, users.length);
        DataSet user = users[0];
        assertEquals("[name, password, id]", Arrays.toString(user.getNames()));
        assertEquals("[Masha, pass2, 13]", Arrays.toString(user.getValues()));
    }

    @Test
    public void testGetColumNames() {
        //given
        manager.clear("user");

        //when
        String[] columNames = manager.getTableColums("user");

        //thern
        assertEquals("[name, password, id]", Arrays.toString(columNames));
    }
}
