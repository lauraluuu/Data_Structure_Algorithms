/**
 * Student ID: 18008550
 * Student Name: Peifen Lu
 */
package DequeImplementation;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author gkn3798
 * @param <E>
 */
public class ArrayDeque<E> implements DequeADT<E> {

    private final int INITIAL_CAPACITY = 20;
    protected E[] things;
    protected int numOfThings;
    private int front, rear;
    private int capacity;

    //This is a default constructor that creates an array
    //that is initially empty
    public ArrayDeque() {
        this.numOfThings = 0;
        this.things = (E[]) (new Object[INITIAL_CAPACITY]);
        //   this.front = this.rear = -1;
        this.rear = -1;
        this.front = INITIAL_CAPACITY;
    }

    public ArrayDeque(int capacity) {
        this.numOfThings = 0;
        this.things = (E[]) (new Object[capacity]);
        //   this.front = this.rear = -1;
        this.capacity = capacity;
        this.rear = -1;
        this.front = capacity;
    }

    /**
     * The enqueueRear method adds an element from the rear O(1)
     *
     * @param element
     */
    @Override
    public void enqueueRear(E element) {
        if ((rear + 1) % things.length == front) {
            expandCapacity();
        } else if (isEmpty()) {
            front--;
        }
        numOfThings++;
        this.rear = (this.rear + 1) % things.length;
        things[rear] = element;
    }

    /**
     * The enqueueRear method adds an element from the front O(1)
     *
     * @param element
     */
    @Override
    public void enqueueFront(E element) {
        if (numOfThings == things.length) {
            expandCapacity();
        } else if (isEmpty()) {
            //front = elements.length;
            rear++;
        }
        numOfThings++;

        this.front = (this.front - 1) % things.length;

        things[front] = element;
    }

    /**
     * The dequeueRear method removes an element from the rear O(1)
     *
     * @return
     */
    @Override
    public E dequeueRear() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        E temp = things[rear];
        if (front == rear) {
            things[0] = null;
            front = things.length;
            rear = -1;
        } else {
            rear = (rear - 1) % things.length;
            things[rear + 1] = null;
        }
        numOfThings--;
        return temp;
    }

    /**
     * The dequeueFront method removes an element from the front O(1)
     *
     * @return
     */
    @Override
    public E dequeueFront() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        E temp = things[front];
        if (front == rear) {
            things[0] = null;
            front = things.length;
            rear = -1;
        } else {
            front = (front + 1) % things.length;
            things[front - 1] = null;
        }
        numOfThings--;
        return temp;
    }

    /**
     * The last method returns the element at the rear of the deque O(1)
     *
     * @return
     */
    @Override
    public E last() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return things[rear];
    }

    /**
     * The isEmpty method checks whether the deque is empty or not O(1)
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        if (numOfThings == 0) {
            return true;
        }
        return false;
    }

    /**
     * iterator iterates over the elements from the front to the rear
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayDequeIterator<E>(things, numOfThings); 
    }

    /**
     * The clear method clear out all elements in deque
     */
    @Override
    public void clear() {
        front = things.length;
        rear = -1;
        numOfThings = 0;
    }

    /**
     * The size method returns the number of elements in the deque O(1) O(1)
     *
     * @return
     */
    @Override
    public int size() {
        return numOfThings;
    }

    /**
     * The first method returns the element at the front O(1) O(1)
     *
     * @return
     */
    @Override
    public E first() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        return things[front];
    }

    public void expandCapacity() {
        E[] largerArray = (E[]) (new Object[things.length * 2]);
        int i = 0;
        int j = largerArray.length - 1;
        int k = things.length - 1;

        do {
            largerArray[j] = things[k];

            j = (j - 1) % largerArray.length;
            k = (k - 1) % things.length;

        } while (k >= front);

        front = (j + 1) % largerArray.length;
        
        j = 0;
        k = 0;
        do {
            largerArray[j] = things[k];
            j = (j + 1) % largerArray.length;
            k = (k + 1) % things.length;

        } while (k < rear);

        largerArray[j] = things[k];
        
        rear = k;
        things = largerArray;
    }

    //returns a string representation of the Queue from front to rear
    public String toString() {
        String output = "[";
        int i = front;

        if (numOfThings == 1) {
            output += things[0];
        } else {

            while (i != 0 && things[i] != null) {

                output += things[i];

                output += " , ";

                i = (i + 1) % things.length;
            }

            int j = 0;

            do {
                output += things[j];

                output += " , ";

                j = (j + 1) % things.length;
            } while (j < rear);

            output += things[rear];
        }

        output += "]";

        return output;
    }

    private class ArrayDequeIterator<E> implements Iterator<E> {
        
        private int numOfThings;
        private E[] things;
        private int nextThing;

        public ArrayDequeIterator(E[] items, int numOfItems) {
            this.things = items;
            this.numOfThings = numOfItems;
            this.nextThing = 0;
        }

        @Override
        public boolean hasNext() {
            if (nextThing < numOfThings) {
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return things[nextThing++];
        }

        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> oneDeque = new ArrayDeque<Integer>();

        for (int i = 0; i < 2; i++) {
            oneDeque.enqueueRear(i);
        }

        System.out.println("The size of the deque is: " + oneDeque.size());

        System.out.println(oneDeque.toString());

        oneDeque.enqueueFront(10);
        oneDeque.enqueueFront(11);
        oneDeque.enqueueFront(13);

        oneDeque.enqueueRear(3);

        oneDeque.enqueueFront(15);

        System.out.println("The size of the deque is: " + oneDeque.size());

        System.out.println(oneDeque.toString());

        System.out.println("The first item in the deque is: " + oneDeque.first());
        System.out.println("The last item in the deque is: " + oneDeque.last());

        System.out.println("The front item removed is: " + oneDeque.dequeueFront());
        System.out.println(oneDeque.toString());
        System.out.println("The rear item removed is: " + oneDeque.dequeueRear());
        System.out.println(oneDeque.toString());

        for (int i = 2; i < 15; i++) {
            oneDeque.enqueueRear(i);
        }

        System.out.println(oneDeque.toString());
        System.out.println("The size of the deque is: " + oneDeque.size());

    }
}
