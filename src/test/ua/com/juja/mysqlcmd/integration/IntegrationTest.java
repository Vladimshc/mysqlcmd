package ua.com.juja.mysqlcmd.integration;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.com.juja.mysqlcmd.controller.Main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Wallee on 18.04.2016.
 */
public class IntegrationTest {

    private ConfigurableInputStream in;
    private ByteArrayOutputStream out;

    @Before
    public void setup() {
        out = new ByteArrayOutputStream();
        in = new ConfigurableInputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));
    }


    public String getData() {
        try {
            String result = new String(out.toByteArray(), "UTF-8");
            out.reset();
            return result;
        } catch (UnsupportedEncodingException e) {
            return  e.getMessage();
        }
    }

    @Test
    public void testHelp() {
        //given
        in.add("help");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "Command for wright:\r\n" +

                "\t'connect|databaseName|userName|password'\r\n" +
                "\t\t- for get connection to database\r\n" +

                "\t'list'\r\n" +
                "\t\t- for print list of all tables on base there we connected\r\n" +

                "\t'clear|tableName'\r\n" +
                "\t\t- for cleaning all tables values\r\n" +

                "\t'create|tableName|colum1|value1|colum2|value2|...|columN|valueN'\r\n" +
                "\t\t- to create a record in the table\r\n" +

                "\t'help'\r\n" +
                "\t\t- for print help list on screen\r\n" +

                "\t'exit'\r\n" +
                "\t\t- for exit from program\r\n" +

                "\t'find|tableName'\r\n" +
                "\t\t- for print the contents of the table 'tableName'\r\n" +

                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testExit() {
        //given
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testListWithoutConnect() {
        //given
        in.add("list");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "You can not use the command 'list' has not yet connect with the command: 'connect|databaseName|userName|password'\r\n" +
                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testFindWithoutConnect() {
        //given
        in.add("find|user");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "You can not use the command 'find|user' has not yet connect with the command: 'connect|databaseName|userName|password'\r\n" +
                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testUnsupportedWithoutConnect() {
        //given
        in.add("unsupported");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "You can not use the command 'unsupported' has not yet connect with the command: 'connect|databaseName|userName|password'\r\n" +
                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testUnsupportedAfterConnect() {
        //given
        in.add("connect|mysqlcmd|postgres|12345");
        in.add("unsupported");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "Ok! Connect successful.\r\n" +
                "Wright command (or 'help')\r\n" +
                "Command 'unsupported' doesn't exist!!!\r\n" +
                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testListAfterConnect() {
        //given
        in.add("connect|mysqlcmd|postgres|12345");
        in.add("list");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "Ok! Connect successful.\r\n" +
                "Wright command (or 'help')\r\n" +
                "[user2, user]\r\n" +
                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testFindAfterConnect() {
        //given
        in.add("connect|mysqlcmd|postgres|12345");
        in.add("clear|user");
        in.add("find|user");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "Ok! Connect successful.\r\n" +
                "Wright command (or 'help')\r\n" +
                "Table 'user' was cleaning successful!!!\r\n" +
                "Wright command (or 'help')\r\n" +
                "--------------\r\n" +
                "|name|password|id|\r\n" +
                "--------------\r\n" +
                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testConnectAfterConnect() {
        //given
        in.add("connect|mysqlcmd|postgres|12345");
        in.add("list");
        in.add("connect|mysqlcmd|postgres|12345");
        in.add("list");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "Ok! Connect successful.\r\n" +
                "Wright command (or 'help')\r\n" +
                "[user2, user]\r\n" +
                "Wright command (or 'help')\r\n" +
                "Ok! Connect successful.\r\n" +
                "Wright command (or 'help')\r\n" +
                "[user2, user]\r\n" +
                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testConnectWithError() {
        //given
        in.add("connect|mysqlcmd|");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "No connect!!! Details: \r\n" +
                "Missing parameters '|', need '4' but wright: '2'\r\n" +
                "Please try again.\r\n" +
                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testFindAfterConnect_withData() {
        //given
        in.add("connect|mysqlcmd|postgres|12345");
        in.add("clear|user");
        in.add("create|user|id|13|name|Vasya|password|88888");
        in.add("create|user|id|14|name|Vasilisa|password|77777");
        in.add("find|user");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "Ok! Connect successful.\r\n" +
                "Wright command (or 'help')\r\n" +
                "Table 'user' was cleaning successful!!!\r\n" +
                "Wright command (or 'help')\r\n" +
                "Record '{ names:[id, name, password], values:[13, Vasya, 88888] }' was created successfully in table 'user'!!!\r\n" +
                "Wright command (or 'help')\r\n" +
                "Record '{ names:[id, name, password], values:[14, Vasilisa, 77777] }' was created successfully in table 'user'!!!\r\n" +
                "Wright command (or 'help')\r\n" +
                "--------------\r\n" +
                "|name|password|id|\r\n" +
                "--------------\r\n" +
                "|Vasya|88888|13|\r\n" +
                "|Vasilisa|77777|14|\r\n" +
                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testClear_withError() {
        //given
        in.add("connect|mysqlcmd|postgres|12345");
        in.add("clear|");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "Ok! Connect successful.\r\n" +
                "Wright command (or 'help')\r\n" +

                "No connect!!! Details: \r\n" +
                "Command format 'clear|tableName', but you have: 'clear|'\r\n" +
                "Please try again.\r\n" +

                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }

    @Test
    public void testCreate_withError() {
        //given
        in.add("connect|mysqlcmd|postgres|12345");
        in.add("create|user|id|1111|2222");
        in.add("exit");

        //when
        Main.main(new String[0]);

        //then
        assertEquals("Hi, user!!!\r\n" +
                "Write base name and password in format: 'connect|database|userName|password'\r\n" +
                "Ok! Connect successful.\r\n" +
                "Wright command (or 'help')\r\n" +
                "No connect!!! Details: \r\n" +
                "Must be an even number of parameters in the following format: " +
                    "'create|tableName|colum1|value1|colum2|value2|...|columN|valueN', but you have: " +
                    "'create|user|id|1111|2222'\r\n" +
                "Please try again.\r\n" +
                "Wright command (or 'help')\r\n" +
                "Bye\r\n", getData());
    }
}
