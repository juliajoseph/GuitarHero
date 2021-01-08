import java.util.NoSuchElementException;

public class RingBuffer {

    private double buffer[];
    private int first;
    private int last;
    private int size;
    private int maxCapacity;

    // create an empty ring buffer, with given max capacity
    public RingBuffer(int capacity) {
        size = 0;
        maxCapacity = capacity;
        buffer = new double[capacity];
        first = 0;
        last = 0;
    }
    // return number of items currently in the buffer
    public int size() {
        return size;
    }

    // is the buffer empty (size equals zero)?
    public boolean isEmpty() {
        return size == 0;
    }

    // is the buffer full  (size equals capacity)?
    public boolean isFull() {
        return size == maxCapacity;
    }

    // add item x to the end (as long as the buffer is not full)
    public void enqueue(double x) {
        if (isFull()) {
            throw new IllegalStateException();
        }
        buffer[last] = x;
        size++;
        if ((last + 1) == buffer.length)
            last = 0;
        else
            last++;
    }

    // delete and return item from the front (as long as the buffer is not empty)
    public double dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        double item = buffer[first];
        if (first == buffer.length - 1)
            first = 0;
        else
            first++;
        size--;
        return item;
    }

    // return (but do not delete) item from the front of the buffer
    public double peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return buffer[first];
    }

    // override toString. Return a String of the form [front, next, next, last]
    public String toString() {
        int counter = 0, maxCount = 0;
        int i = first;
        String bufferString = "[";

        if ((first == last) && (size > 0))
            maxCount = maxCapacity;
        else if (first > last)
            maxCount = maxCapacity - (first - last);
        else
            maxCount= last - first;

        while (counter < maxCount) {
            bufferString += String.format("%.1f, ",buffer[i]);
            if (i == maxCapacity - 1)
                i = 0;
            else
                i++;
            counter++;
        }
        if ((bufferString.lastIndexOf(", ")) > -1)
            bufferString = bufferString.substring(0, bufferString.lastIndexOf(","));

        bufferString += "]";
        return bufferString;
    }
}