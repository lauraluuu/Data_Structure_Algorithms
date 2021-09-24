/**
 * This ArrayBag class implements a bag with an underlying array
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package BagImplementations;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 *
 * @author gkn3798
 */
public class ArrayBag<E> implements Bag<E> {

    private int numOfThings;
    private E[] things;
    private final int FIXED_CAPACITY = 10;
    private final Random random = new Random();

    //empty constructor
    public ArrayBag() {
        this.things = (E[]) (new Object[FIXED_CAPACITY]);
        this.numOfThings = 0;
    }

    public ArrayBag(int Capacity) {
        this.things = (E[]) (new Object[Capacity]);
        this.numOfThings = 0;
    }

    /**
     * The add method is to add a new item to the array and return true if added
     * or return false if the array is full
     *
     * @param item
     * @return
     */
    @Override
    public boolean add(E item) {
        if (this.numOfThings < this.things.length) {
            this.things[numOfThings] = item;
            numOfThings++;
            return true;
        }
        return false;
    }

    /**
     * The grab method is to give back a random item without removing it
     *
     * @return
     */
    @Override
    public E grab() {
        int randomIndex = random.nextInt(numOfThings);

        E item = things[randomIndex];

        return item;
    }

    /**
     * The remove method removes a parameter item from the bag
     *
     * @param item
     * @return
     */
    @Override
    public boolean remove(E item) {
        boolean isFound = false;
        int index = 0;

        for (int i = 0; i < numOfThings && !isFound; i++) {
            if ((things[i] == null && item == null)
                    || (things[i] != null && things[i].equals(item))) {
                index = i;
                isFound = true;
            }
        }

        if (isFound) {
            things[index] = things[numOfThings - 1];
            things[numOfThings - 1] = null;
            numOfThings--;
        }
        return isFound;
    }

    @Override
    public int size() {
        return numOfThings;
    }

    /**
     * The capacityRemaining method checks how many items that can still be
     * added to bag before it is full
     *
     * @return
     */
    @Override
    public int capacityRemaining() {
        return (this.things.length - this.numOfThings);
    }

    /**
     * The isEmpty method checks whether the bag is full
     *
     * @return
     */
    @Override
    public boolean isFull() {
        return (numOfThings == this.things.length);
    }

    /**
     * The isEmpty method checks whether the bag is empty
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return (numOfThings == 0);
    }

    /**
     * The clear method removes all items from the array
     */
    @Override
    public void clear() {
        for (int i = numOfThings; i >= 0; i--) {
            things[i] = null;
        }
        numOfThings = 0;
    }

    public Iterator<E> iterator() {
        return new BagArrayIterator<E> (things, numOfThings);
    }

    /**
     * The toArray method returns an array of the same type of all items left in
     * the bag
     *
     * @return
     */
    @Override
    public E[] toArray() {
        E[] newArray = (E[]) (new Object[this.numOfThings]);

        for (int i = 0; i < this.numOfThings; i++) {
            newArray[i] = this.things[i];
        }
        return newArray;
    }

    public String toString() {
        String things = "[ ";

        for (int i = 0; i < this.numOfThings - 1; i++) {
            things += this.things[i];
            things += " , ";
        }
        things += this.things[this.numOfThings - 1];
        things += " ]";
        return things;
    }

    private class BagArrayIterator<E> implements Iterator<E> {

        private int numOfThings;
        private E[] things;
        private int nextThing;

        public BagArrayIterator(E[] things, int numOfThings) {
            this.things = things;
            this.numOfThings = numOfThings;
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
}
