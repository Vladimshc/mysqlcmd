package ua.com.juja.mysqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import ua.com.juja.mysqlcmd.model.DataSet;
import ua.com.juja.mysqlcmd.model.DatabaseManager;
import ua.com.juja.mysqlcmd.view.View;

import java.util.IllegalFormatCodePointException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Wallee on 20.04.2016.
 */
public class ClearTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Clear(manager, view);
    }

    @Test
    public void testClearTable() {
//        given
//        when
        command.process("clear|user");

//        then
        verify(manager).clear("user");
        verify(view).write("Table 'user' was cleaning successful!!!");
    }

    @Test
    public void testCanProcessClearWithParametersString() {
        //when
        boolean canProcess = command.canProcess("clear|user");

        //then
        assertTrue(canProcess);
    }

    @Test
    public void testCantProcessClearWithoutParametersString() {
        //when
        boolean canProcess = command.canProcess("clear");

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
    public void testValidationErrorWhenCountParametersIsLessThan2() {
        //when
        try {
            command.process("clear");
            fail();
        } catch (IllegalArgumentException e) {
            //then
            assertEquals("Command format 'clear|tableName', but you have: 'clear'", e.getMessage());

        }

    }

    @Test
    public void testValidationErrorWhenCountParametersIsMoreThan2() {
        //when
        try {
            command.process("clear|table|qwe");
            fail();
        } catch (IllegalArgumentException e) {
            //then
            assertEquals("Command format 'clear|tableName', but you have: 'clear|table|qwe'", e.getMessage());

        }

    }
}
