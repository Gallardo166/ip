package bibot;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import bibot.command.AddCommand;
import bibot.command.DeleteCommand;
import bibot.command.ListCommand;
import bibot.command.MarkCommand;
import bibot.command.UnmarkCommand;
import bibot.task.Deadline;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parse_invalidTodoFormat_exceptionThrown() {
        BibotException thrown = assertThrows(BibotException.class, () -> {
            new Parser().parse("todo");
        });
        assertEquals("Please write the task description!", thrown.getMessage());

        thrown = assertThrows(BibotException.class, () -> {
            new Parser().parse("todo ");
        });
        assertEquals("Please write the task description!", thrown.getMessage());
    }

    @Test
    public void parse_invalidKeyword_exceptionThrown() {
        BibotException thrown = assertThrows(BibotException.class, () -> {
            new Parser().parse("todoo read");
        });
        assertEquals("I'm not familiar with that command...", thrown.getMessage());
    }

    @Test
    public void parse_invalidDeadlineFormat_exceptionThrown() {
        BibotException thrown = assertThrows(BibotException.class, () -> {
            new Parser().parse("deadline ");
        });
        assertEquals("Please use this format:\n     deadline [description] /by [datetime]", thrown.getMessage());
    }

    @Test
    public void parse_invalidEventFormat_exceptionThrown() {
        BibotException thrown = assertThrows(BibotException.class, () -> {
            new Parser().parse("event ");
        });
        assertEquals("Please use this format:\n     event [description] /from [datetime] /to [datetime]", thrown.getMessage());
    }

    @Test
    public void parse_validDeadlineFormat_addCommandReturned() throws BibotException {
        assertInstanceOf(AddCommand.class, new Parser().parse("deadline homework /by 02/02/2026 1300"));
        assertDoesNotThrow(() -> new Parser().parse("deadline homework /by 02/02/2026 1300"));
    }

    @Test
    public void parse_validDeleteFormat_deleteCommandReturned() throws BibotException {
        assertInstanceOf(DeleteCommand.class, new Parser().parse("delete 2"));
        assertDoesNotThrow(() -> new Parser().parse("delete 2"));
    }

    @Test
    public void parse_validMarkFormat_markCommandReturned() throws BibotException {
        assertInstanceOf(MarkCommand.class, new Parser().parse("mark 1"));
        assertDoesNotThrow(() -> new Parser().parse("mark 1"));
    }

    @Test
    public void parse_validUnmarkFormat_unmarkCommandReturned() throws BibotException {
        assertInstanceOf(UnmarkCommand.class, new Parser().parse("unmark 1"));
        assertDoesNotThrow(() -> new Parser().parse("unmark 1"));
    }

    @Test
    public void parse_validListFormat_listCommandReturned() throws BibotException {
        assertInstanceOf(ListCommand.class, new Parser().parse("list"));
        assertDoesNotThrow(() -> new Parser().parse("list"));
    }
}