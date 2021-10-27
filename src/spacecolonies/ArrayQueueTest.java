// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Alip Arslan (906347003)

package spacecolonies;

import java.util.Arrays;
import queue.EmptyQueueException;
import student.TestCase;

/**
 * Tests methods in the ArrayQueue class.
 * 
 * @author aliparslan
 * @version 2021.04.07
 *
 */
public class ArrayQueueTest extends TestCase {

    // Fields ............................................

    private ArrayQueue<String> q;

    // Constructor .......................................

    /**
     * Empty constructor
     */
    public ArrayQueueTest() {
        // Intentionally left empty
    }

    // Methods ...........................................


    /**
     * The code to be run before each test is performed
     */
    public void setUp() {
        q = new ArrayQueue<String>(3);
    }


    /**
     * tests getter methods getLengthOfUnderlyingArray(), getSize(),
     * getFront(), isEmpty(),
     */
    public void testGetters() {
        assertTrue(q.isEmpty());
        assertEquals(4, q.getLengthOfUnderlyingArray());
        assertEquals(0, q.getSize());

        q.enqueue("A");
        assertEquals(1, q.getSize());
        assertFalse(q.isEmpty());

        assertEquals("A", q.getFront());
        q.enqueue("B");
        q.dequeue();
        assertEquals("B", q.getFront());
    }
    
    /**
     * tests getFront() when an exception is expected
     */
    public void testGetFrontException() {
        Exception exception = null;
        try {
            q.getFront();
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof EmptyQueueException);
    }


    /**
     * tests enqueue
     */
    public void testEnqueue() {
        assertEquals(0, q.getSize());
        q.enqueue("A");
        assertEquals(1, q.getSize());

        q.enqueue("A");
        q.enqueue("A");
        assertEquals(3, q.getSize());
        assertEquals(4, q.getLengthOfUnderlyingArray());

        // ensureCapacity() will run
        // has to hold 6 items instead of 3, so length of queue will be 7
        q.enqueue("A");
        assertEquals(4, q.getSize());
        assertEquals(7, q.getLengthOfUnderlyingArray());
    }
    
    /**
     * tests dequeue() when an EmptyQueueException is expected
     */
    public void testDequeueException() {
        Exception exception = null;
        try {
            q.dequeue();
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof EmptyQueueException);
    }
    
    /**
     * tests dequeue()
     */
    public void dequeue() {
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        
        assertEquals("A", q.dequeue());
        assertEquals("B", q.dequeue());
        assertEquals("C", q.dequeue());
    }
    
    /**
     * tests clear()
     */
    public void testClear() {
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        assertEquals(3, q.getSize());
        
        q.clear();
        assertEquals(0, q.getSize());
    }
    
    /**
     * tests toArray() when an exception is expected
     */
    public void testToArrayEmpty() {
        Exception exception = null;
        try {
            q.toArray();
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof EmptyQueueException);
    }
    
    
    /**
     * tests toArray()
     */
    public void testToArray() {
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        String[] compare = new String[3];
        compare[0] = "A";
        compare[1] = "B";
        compare[2] = "C";
        
        assertTrue(Arrays.equals(q.toArray(), compare));
        
        compare[2] = "1";
        assertFalse(Arrays.equals(q.toArray(), compare));
    }
    
    /**
     * tests toString()
     */
    public void testToString() {
        assertEquals("[]", q.toString());
        
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        assertEquals("[A, B, C]", q.toString());
    }
    
    /**
     * 
     */
    public void testEquals() {
        assertTrue(q.equals(q));
        
        Object n = null;
        assertFalse(q.equals(n));
        
        Object o = new Object();
        assertFalse(q.equals(o));
        
        ArrayQueue<String> a = new ArrayQueue<>(1);
        q.enqueue("A");
        a.enqueue("A");
        assertFalse(q.equals(a));
        
        ArrayQueue<String> b = new ArrayQueue<>(3);
        q.enqueue("B");
        q.enqueue("C");
        b.enqueue("A");
        b.enqueue("B");
        b.enqueue("C");
        assertTrue(q.equals(b));
    }
    
    
    
    
    
    
}
