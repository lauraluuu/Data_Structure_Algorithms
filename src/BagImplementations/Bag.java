/**
 * This Bag interface represents an abstract data type for a Bag data structure
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package BagImplementations;

import java.util.Iterator;

/**
 *
 * @author gkn3798
 */
public interface Bag<E> {
    public boolean add(E item);
    
    public E grab();
    
    public boolean remove(E item);
    
    public int size();
    
    public int capacityRemaining();
    
    public boolean isFull();
    
    public boolean isEmpty();
    
    public void clear();
    
    public Iterator<E> iterator();
    
    public E[] toArray();
}
