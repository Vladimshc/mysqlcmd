package ua.com.juja.mysqlcmd.controller.command;

        import org.junit.Before;
        import org.junit.Test;
        import org.mockito.ArgumentCaptor;

        import static org.junit.Assert.assertFalse;
        import static org.junit.Assert.assertTrue;
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
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Find(manager, view);
    }

    @Test
    public void testPrintTableData() {
//        given
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
        shouldPrint("[--------------, " +
                    "|id|name|password|, " +
                    "--------------, " +
                    "|12|Arnold|2222222|, " +
                    "|13|Tom|3333333|]");
    }

    public void shouldPrint(String expected) {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(view, atLeastOnce()).write(captor.capture());
        assertEquals(expected, captor.getAllValues().toString());
    }

    @Test
    public void testCanProcessFindWithParametersString() {
        //when
        boolean canProcess = command.canProcess("find|user");

        //then
        assertTrue(canProcess);
    }

    @Test
    public void testCantProcessFindWithoutParametersString() {
        //when
        boolean canProcess = command.canProcess("find");

        //then
        assertFalse(canProcess);
    }

    @Test
    public void testCantProcessQweString() {
        //when
        boolean canProcess = command.canProcess("qwe|user");

        //then
        assertFalse(canProcess);
    }

    @Test
    public void testPrintEmptyTableData() {
//        given
        when(manager.getTableColums("user"))
                .thenReturn(new String[]{"id", "name", "password"});

        DataSet[] data = new DataSet[0];
        when(manager.getTableData("user"))
                .thenReturn(data);

//        when
        command.process("find|user");

//        then
        shouldPrint("[--------------, " +
                    "|id|name|password|, " +
                    "--------------]");
    }
}
