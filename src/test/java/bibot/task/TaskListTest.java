package bibot.task;

import org.junit.jupiter.api.Test;

import bibot.BibotException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void add_singleTask_noExceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        assertEquals(1, taskList.getLength());
    }

    @Test
    public void markTask_validIndex_noExceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        assertDoesNotThrow(() -> taskList.markTask(0));
    }

    @Test
    public void unmarkTask_validIndex_noExceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        assertDoesNotThrow(() -> taskList.unmarkTask(0));
    }

    @Test
    public void deleteTask_validIndex_noExceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        assertDoesNotThrow(() -> taskList.deleteTask(0));
    }

    @Test
    public void markTask_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        BibotException thrown = assertThrows(BibotException.class,
                () -> taskList.markTask(-1));
        assertEquals("There's no such task!", thrown.getMessage());

        thrown = assertThrows(BibotException.class,
                () -> taskList.markTask(1));
        assertEquals("There's no such task!", thrown.getMessage());
    }

    @Test
    public void unmarkTask_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        BibotException thrown = assertThrows(BibotException.class,
                () -> taskList.unmarkTask(-1));
        assertEquals("There's no such task!", thrown.getMessage());

        thrown = assertThrows(BibotException.class,
                () -> taskList.unmarkTask(1));
        assertEquals("There's no such task!", thrown.getMessage());
    }

    @Test
    public void deleteTask_invalidIndex_exceptionThrown() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        BibotException thrown = assertThrows(BibotException.class,
                () -> taskList.deleteTask(-1));
        assertEquals("There's no such task!", thrown.getMessage());

        thrown = assertThrows(BibotException.class,
                () -> taskList.deleteTask(1));
        assertEquals("There's no such task!", thrown.getMessage());
    }
}