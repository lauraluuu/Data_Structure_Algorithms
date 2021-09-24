/**
 * This LinkedBag class implements a bag with an underlying doubly linked list
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
public class LinkedBag<E> implements Bag<E> {

    protected int numOfThings;
    protected Node<E> firstNode;
    protected Node<E> lastNode;
    private int capacity;

    /**
     * default constructor that creates an empty linkedbag
     */
    public LinkedBag() {
        this.numOfThings = 0;
        this.firstNode = null;
        this.lastNode = null;
        this.capacity = 10;
    }

    /**
     * constructor that allows a using program to specify capacity
     */
    public LinkedBag(int capacity) {
        this.numOfThings = 0;
        this.firstNode = null;
        this.lastNode = null;
        this.capacity = capacity;
    }

    /**
     * The add method add a new item if the capacity is not full
     *
     * @param item
     * @return
     */
    @Override
    public boolean add(E item) {
        if (numOfThings < capacity) {
            Node<E> newNode = new Node<E>(item);

            if (firstNode == null) {
                this.firstNode = newNode;
                this.lastNode = newNode;
                this.firstNode.previous = null;
                this.lastNode.next = null;
            } else {
                this.lastNode.next = newNode;
                newNode.previous = this.lastNode;
                lastNode = newNode;
                lastNode.next = null;
            }
            numOfThings++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * The remove method removes a parameter item from the bag
     *
     * @param item
     * @return boolean indicates whether the item has been removed or not
     */
    public boolean remove(E item) {
        boolean hasFound = false;
        
        if (firstNode == null || item == null){
            return true;
        }
        
        if (firstNode != null) {
            //check if the firstNode is the item that needs to be removed
            if ( firstNode.thing.equals(item)) {
                hasFound = true;
                firstNode = firstNode.next;
                numOfThings--;
            } else {

                Node<E> current = firstNode.next;

                while (current != null && !hasFound) {
                    if (current.thing.equals(item)) {
                        hasFound = true;
                        current.previous.next = current.next;
                        numOfThings--;
                    } else {
                        current = current.next;
                    }
                }
            }
        }
        return hasFound;
    }

    /**
     * The size method checks the the number of items in the bag
     * @return numOfItems
     */
    @Override
    public int size() {
        return numOfThings;
    }

    /**
     * The capacityRemaining checks how many items that can still be 
     * added to the bag until it is full
     * @return 
     */
    @Override
    public int capacityRemaining() {
        return (capacity - numOfThings);
    }

    /**
     * The isFull method checks whether the bag has no capacity
     * @return 
     */
    @Override
    public boolean isFull() {
        return (numOfThings == capacity);
    }

    /**
     * The isEmpty method checks whether the bag is empty or not
     * @return 
     */
    @Override
    public boolean isEmpty() {
        return (numOfThings == 0);
    }

    /**
     * The clear method removes all the items from the bag
     */
    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        numOfThings = 0;
    }

    /**
     * The iterator allows the items to be iterated over
     * @return 
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedBagIterator<E>(firstNode);
    }

    /**
     * The toArray method returns an array of the same type of all items left in
     * the bag
     *
     * @return newArray
     */
    @Override
    public E[] toArray() {
        E[] newArray = (E[]) (new Object[this.numOfThings]);

        Node<E> current = firstNode;
        int index = 0;

        while (current != null) {
            newArray[index] = current.thing;

            current = current.next;
            index++;
        }

        return newArray;
    }

    /**
     * The toString method prints out the items in the bag
     * @return 
     */
    public String toString() {
        String things = "[ ";

        Node<E> current = firstNode;

        while (current.next != null) {
            things += current.thing;
            things += ", ";
            current = current.next;
        }
        things += current.thing;
        things += " ]";
        return things;
    }

    /**
     * The grad method returns an item in the bag at random without removing it
     *
     * @return E
     */
    @Override
    public E grab() {
        Random random = new Random();

        E[] tempArray = this.toArray();

        E result = tempArray[random.nextInt(tempArray.length)];

        return result;
    }

    private class LinkedBagIterator<E> implements Iterator<E> {

        private Node<E> nextNode;

        public LinkedBagIterator(Node<E> firstNode) {
            nextNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return (nextNode != null);
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                E item = nextNode.thing;
                nextNode = nextNode.next;
                return item;
            }
        }

        @Override
        public void remove() {
            Iterator.super.remove(); 
        }
    }

    private class Node<E> {

        public E thing;
        public Node<E> next;
        public Node<E> previous;

        public Node(E item) {
            this.thing = item;
            this.next = null;
            this.previous = null;
        }
    }
}
