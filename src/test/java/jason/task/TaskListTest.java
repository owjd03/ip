package jason.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import jason.exception.JasonException;

public class TaskListTest {
    private TaskList taskList;
    private Task todo;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        todo = new Todo("Eat breakfast");
    }

    @Test
    public void add_validTask_success() {
        taskList.add(todo);

        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.getTask(0));
    }

    @Test
    public void delete_validTask_success() throws JasonException{
        taskList.add(todo);

        Task delete = taskList.delete(0);

        assertEquals(0, taskList.size());
        assertEquals(todo, delete);
    }

    @Test
    public void delete_invalidTask_exceptionThrown() {
        taskList.add(todo);

        try {
            assertEquals(todo, taskList.delete(5));
            fail();
        } catch (Exception e) {
            assertEquals("Give me a valid task number", e.getMessage());
        }
    }
}

