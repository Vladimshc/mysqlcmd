package ua.com.juja.mysqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;

import ua.com.juja.mysqlcmd.model.DataSet;
import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wallee on 20.04.2016.
 */
public class FindTest {

    private DatabaseManager manager;
    private View view;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
    }

    @Test
    public void testPrintTableData() {
//        given
        Command command = new Find(manager, view);
        when(manager.getTableColums("user"))
                .thenReturn(new String[]{"id", "name", "password"});

        DataSet user1 = new DataSet();
        user1.put("id", 12);
        user1.put("name", "Arnold");
        user1.put("password", "2222222");

        DataSet user2 = new DataSet();
        user2.put("id", 13);
        user2.put("name", "Tom");
        user2.put("password", "3333333");

        DataSet[] data = new DataSet[]{user1, user2};
        when(manager.getTableData("user"))
                .thenReturn(data);

//        when
        command.process("find|user");

//        then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view, atLeastOnce()).write(captor.capture());
        assertEquals(
                        "[--------------, " +
                        "|id|name|password|, " +
                        "--------------, " +
                        "|12|Arnold|2222222|, " +
                        "|13|Tom|3333333|]", captor.getAllValues().toString());
    }

}
