package jason.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void constructor_validContent_success() {
        Todo todo = new Todo("Eat breakfast");

        assertEquals("Eat breakfast", todo.getContents());
    }

    @Test
    public void toString_validContent_success() {
        Todo todo = new Todo("Drink coffee");

        assertEquals("[T][ ] Drink coffee", todo.toString());
    }

    @Test
    public void markDone_updateToString_success() {
        Todo todo = new Todo("Drink coffee");

        todo.markDone();

        assertEquals("[T][X] Drink coffee", todo.toString());
    }

}