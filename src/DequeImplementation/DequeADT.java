/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DequeImplementation;

import java.util.Iterator;

/**
 *
 * @author sehall
 */
interface DequeADT<E> {
    public void enqueueRear(E element);
    
    public E dequeueFront();
    
    public E first();
    
    public E last();
    
    public void enqueueFront(E element);
    
    public E dequeueRear();
    
    public boolean isEmpty();
    
    public int size();
    
    public Iterator<E> iterator();
    
    public void clear();
}
