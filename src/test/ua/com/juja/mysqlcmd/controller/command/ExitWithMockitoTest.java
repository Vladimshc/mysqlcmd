package ua.com.juja.mysqlcmd.controller.command;

import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.mysqlcmd.view.View;

import static org.junit.Assert.*;

/**
 * Created by Wallee on 20.04.2016.
 */
public class ExitWithMockitoTest {

    private View view = Mockito.mock(View.class);

    @Test
    public void testCanProcessExitString() {
        // given
        Command command = new Exit(view);

        //when
        boolean canProcess = command.canProcess("exit");

        //then
        assertTrue(canProcess);
    }

    @Test
    public void testCantProcessErrorString() {
        // given
        Command command = new Exit(view);

        //when
        boolean canProcess = command.canProcess("error");

        //then
        assertFalse(canProcess);
    }

    @Test
    public void testProcessExitCommand_throwsExitException() {
        // given
        Command command = new Exit(view);

        //when
        try {
            command.process("exit");
            fail("Expected ExitException");
        } catch (ExitException e) {
            //do nothing
        }

        //then
        Mockito.verify(view).write("Bye");
    }

}
