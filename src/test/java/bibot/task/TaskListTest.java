package bibot.task;

import org.junit.jupiter.api.Test;

import bibot.BibotException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void test1() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        assertEquals(1, taskList.getLength());
    }

    @Test
    public void test2() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        assertDoesNotThrow(() -> taskList.markTask(0));
    }

    @Test
    public void test3() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        assertDoesNotThrow(() -> taskList.unmarkTask(0));
    }

    @Test
    public void test4() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        assertDoesNotThrow(() -> taskList.deleteTask(0));
    }

    @Test
    public void test5() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        BibotException thrown = assertThrows(BibotException.class,
                () -> taskList.markTask(-1));
        assertEquals("There's no such task!", thrown.getMessage());
    }

    @Test
    public void test6() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        BibotException thrown = assertThrows(BibotException.class,
                () -> taskList.unmarkTask(-1));
        assertEquals("There's no such task!", thrown.getMessage());
    }

    @Test
    public void test7() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        BibotException thrown = assertThrows(BibotException.class,
                () -> taskList.deleteTask(-1));
        assertEquals("There's no such task!", thrown.getMessage());
    }

    @Test
    public void test8() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        BibotException thrown = assertThrows(BibotException.class,
                () -> taskList.markTask(1));
        assertEquals("There's no such task!", thrown.getMessage());
    }

    @Test
    public void test9() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        BibotException thrown = assertThrows(BibotException.class,
                () -> taskList.unmarkTask(1));
        assertEquals("There's no such task!", thrown.getMessage());
    }

    @Test
    public void test10() {
        TaskList taskList = new TaskList();
        taskList.add(new ToDo("test"));

        BibotException thrown = assertThrows(BibotException.class,
                () -> taskList.deleteTask(1));
        assertEquals("There's no such task!", thrown.getMessage());
    }
}
