package edu.mansurov.storage.structures;

import edu.mansurov.storage.structues.Stack;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackTest {
    private final Stack<Integer> stack = new Stack<>();

    @Test
    public void testStack() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        int last = stack.pop();
        int middle = stack.pop();
        int first = stack.pop();

        Assertions.assertEquals(3, last);
        Assertions.assertEquals(2, middle);
        Assertions.assertEquals(1, first);
    }
}
