// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Alip Arslan (906347003)
package spacecolonies;

import java.util.Arrays;
import queue.EmptyQueueException;
import queue.QueueInterface;

/**
 * This data structure implements QueueInterface with a circular array
 * implementation. It provides default queue behavior, such as enqueue,
 * dequeue, getFront, and isEmpty.
 * 
 * @author aliparslan
 * @version 2021.04.07
 *
 * @param <T>
 *            type of object to fill the ArrayQueue object
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Fields .......................................................

    private T[] queue;

    /**
     * Default capacity for an ArrayQueue object
     */
    public static final int DEFAULT_CAPACITY = 20;

    private int enqueueIndex; // AKA backIndex
    private int dequeueIndex; // AKA frontIndex
    private int size;

    // Constructor ..................................................

    /**
     * Creates an ArrayQueue object with a specified capacity
     * 
     * @param capacity
     *            the capacity of the ArrayQueue
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        queue = (T[])new Object[capacity + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;
    }

    // Methods ......................................................


    /**
     * Returns length of the array
     * 
     * @return length of the queue field
     */
    public int getLengthOfUnderlyingArray() {
        return queue.length;
    }


    /**
     * Returns the size of the queue (number of elements in it)
     * 
     * @return the size field
     */
    public int getSize() {
        return this.size;
    }


    /**
     * Whether or not the queue has anything in it
     * 
     * @return if the queue has zero elements
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * If the queue is full (has one empty spot)
     * 
     * @return if the queue is full
     */
    private boolean isFull() {
        return this.size == this.getLengthOfUnderlyingArray() - 1;
    }


    /**
     * Adds a new entry to the "back" of the queue
     * 
     * @param data
     *            the data to be added
     */
    public void enqueue(T data) {
        ensureCapacity();
        queue[enqueueIndex] = data;
        enqueueIndex = this.incrementIndex(enqueueIndex);
        size++;
    }


    /**
     * Checks to see if the queue is full, and if it is,
     * creates a new queue with twice the capacity and the elements
     * of the old queue
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if (this.isFull()) {
            
            T[] newQ = (T[])new Object[this.size * 2 + 1];
            for (int i = this.dequeueIndex; i != this.enqueueIndex;
                 i = this.incrementIndex(i)) {
                newQ[i] = queue[i];
            }
            
            queue = newQ;
            dequeueIndex = 0;
            enqueueIndex = this.size;
        }
    }


    /**
     * Remove and returns the item at the front of the queue
     * 
     * @return the item at the front of the queue
     */
    public T dequeue() {
        T front = this.getFront();
        queue[dequeueIndex] = null;
        dequeueIndex = this.incrementIndex(dequeueIndex);
        size--;
        return front;
    }


    /**
     * Returns the item at the front of the queue
     * 
     * @return the item at the front of the queue
     */
    public T getFront() {
        if (this.isEmpty()) {
            throw new EmptyQueueException();
        }
        return queue[dequeueIndex];
    }


    /**
     * Resets the queue and its fields
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
        dequeueIndex = 0;
        enqueueIndex = 0;
        size = 0;
    }


    /**
     * 
     */
    private int incrementIndex(int index) {
        return (index + 1) % queue.length;
    }


    /**
     * Array representation of the ArrayQueue object
     * Index starts at 0 (not a circular array like the queue field)
     * 
     * @return an array representation of the object
     */
    public Object[] toArray() {
        if (this.isEmpty()) {
            throw new EmptyQueueException();
        }

        Object[] array = new Object[size];
        int start = dequeueIndex;
        for (int i = 0; i < size; i++) {
            array[i] = queue[start];
            start = this.incrementIndex(start);
        }

        return array;
    }


    /**
     * String representation of the ArrayQueue object
     * 
     * Example:
     * [Jane Doe A:3 M:2 T:1 Wants:Nars, No-Planet Jane Doe A:2 M:5 T:4]
     * 
     * @return string representation of the ArrayQueue object
     */
    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder s = new StringBuilder();
        s.append("[");
        Object[] temp = this.toArray();
        for (int i = 0; i < this.getSize(); i++) {
            if (temp[i] != null) {
                s.append(temp[i].toString());
            }
            if (i != this.getSize() - 1) {
                s.append(", ");
            }
            else {
                s.append("]");
            }
        }
        return s.toString();
    }


    /**
     * For two ArrayQueues to be equal, they have to contain
     * the same elements in the same order.
     * 
     * @param obj
     *            object to be compared to
     * @return the equality between the two objects
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        
        if (this.getClass() == obj.getClass()) {
            ArrayQueue<T> other = (ArrayQueue<T>)obj;
            if (this.getLengthOfUnderlyingArray() != 
                other.getLengthOfUnderlyingArray()) {
                return false;
            }
            return Arrays.equals(this.toArray(), other.toArray());
        }
        return false;
    }

}
