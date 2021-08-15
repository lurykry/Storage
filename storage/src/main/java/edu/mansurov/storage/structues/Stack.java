package edu.mansurov.storage.structues;

import edu.mansurov.storage.annotations.NotThreadSafe;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Not thread safe impl of Stack
 * @param <T> type parameter
 */
@NotThreadSafe
public class Stack<T> implements Iterable<T> {
    private final List<T> stack = new ArrayList<>();
    private int tail = -1;

    /**
     * Adds element to the top of the stack
     */
    public void push(T element) {
        stack.add(element);
        tail++;
    }

    /**
     * Removes element from the top of the stack
     */
    public T pop() {
        return stack.remove(tail--);
    }

    public int size() {
        return stack.size();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return stack.iterator();
    }
}
