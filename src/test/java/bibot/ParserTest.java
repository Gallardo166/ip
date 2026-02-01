package bibot;

import bibot.task.Deadline;
import bibot.command.AddCommand;
import bibot.command.MarkCommand;
import bibot.command.DeleteCommand;
import bibot.command.UnmarkCommand;
import bibot.command.ListCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;

public class ParserTest {
    @Test
    public void test1() {
        BibotException thrown = assertThrows(BibotException.class, () -> {
            new Parser().parse("todo");
        });
        assertEquals("Please write the task description!", thrown.getMessage());
    }

    @Test
    public void test2() {
        BibotException thrown = assertThrows(BibotException.class, () -> {
            new Parser().parse("todoo read");
        });
        assertEquals("I'm not familiar with that command...", thrown.getMessage());
    }

    @Test
    public void test3() {
        BibotException thrown = assertThrows(BibotException.class, () -> {
            new Parser().parse("todo ");
        });
        assertEquals("Please write the task description!", thrown.getMessage());
    }

    @Test
    public void test4() {
        BibotException thrown = assertThrows(BibotException.class, () -> {
            new Parser().parse("deadline ");
        });
        assertEquals("Please use this format:\n     deadline [description] /by [datetime]", thrown.getMessage());
    }

    @Test
    public void test5() {
        BibotException thrown = assertThrows(BibotException.class, () -> {
            new Parser().parse("event ");
        });
        assertEquals("Please use this format:\n     event [description] /from [datetime] /to [datetime]", thrown.getMessage());
    }

    @Test
    public void test6() throws BibotException {
        assertInstanceOf(AddCommand.class, new Parser().parse("deadline homework /by 02/02/2026 1300"));
        assertDoesNotThrow(() -> new Parser().parse("deadline homework /by 02/02/2026 1300"));
    }

    @Test
    public void test7() throws BibotException {
        assertInstanceOf(DeleteCommand.class, new Parser().parse("delete 2"));
        assertDoesNotThrow(() -> new Parser().parse("delete 2"));
    }

    @Test
    public void test8() throws BibotException {
        assertInstanceOf(MarkCommand.class, new Parser().parse("mark 1"));
        assertDoesNotThrow(() -> new Parser().parse("mark 1"));
    }

    @Test
    public void test9() throws BibotException {
        assertInstanceOf(UnmarkCommand.class, new Parser().parse("unmark 1"));
        assertDoesNotThrow(() -> new Parser().parse("unmark 1"));
    }

    @Test
    public void test10() throws BibotException {
        assertInstanceOf(ListCommand.class, new Parser().parse("list"));
        assertDoesNotThrow(() -> new Parser().parse("list"));
    }
}
