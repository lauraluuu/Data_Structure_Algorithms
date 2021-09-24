/**
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package MapImplementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author gkn3798
 */
public class LinkedHashMapWithChaining<K, V> implements Map<K, V> {

    private final int LENGTH = 15;
    protected Node<K, V>[] table;
    private int numOfItems;
    private Node<K, V> first;
    private Node<K, V> last;

    public LinkedHashMapWithChaining() {
        table = new Node[LENGTH];
        numOfItems = 0;
        first = null;
        last = null;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int size() {
        return numOfItems;
    }

    @Override
    public boolean isEmpty() {
        return numOfItems == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        boolean isFound = false;

        int targetIndex = this.getIndex((K) key);

        Node<K, V> current = table[targetIndex];
        if (current != null) {
            if (current.key.equals(key)) {
                isFound = true;
                return isFound;
            }
            current = current.nextChain;
        }
        return isFound;
    }

    /**
     * This expandCapacity method expand the space of the hashtable when a 
     * load factor is less than 75%
     */
    public void expandCapacity() {
        int largerCapacity = numOfItems * 2;
        Node<K, V>[] newTable = new Node[largerCapacity];

        for (int index = 0; index < table.length; index++) {
            Node<K, V> current = table[index];
            if (current != null) {
                int newIndex = table[index].hashCode() % largerCapacity;

                if (newIndex < 0) {
                    index = index * (-1);
                }
                newTable[newIndex] = current;
            }
        }
        this.table = newTable;
    }
    
    @Override
    public boolean containsValue(Object value) {
        boolean isFound = false;

        Node<K, V> current = this.first;

        while (current != null) {
            if (current.value.equals(value)) {
                isFound = true;
            }
            current = current.nextNode;
        }
        return isFound;
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        int index = hashCode % LENGTH;
        if (index < 0) {
            index = index * (-1);
        }
        return index;
    }
    
    @Override
    public V get(Object key) {
        int index = this.getIndex((K) key);
        Node<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.nextChain;
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        if ((numOfItems) / table.length >= 0.75f) {
            expandCapacity();
        }

        if (key != null) {
            int targetIndex = this.getIndex(key);
            //  System.out.println(targetIndex);

            Node<K, V> node = new Node(key, value);

            if (table[targetIndex] == null) {
                table[targetIndex] = node;
            } else {
                //    Node<K, V> current = table[targetIndex];

                node.nextChain = table[targetIndex];
                table[targetIndex] = node;
            }

            if (first == null) {
                first = last = node;
                first.previousNode = null;
                last.nextNode = null;
            } else {
                last.nextNode = node;
                node.previousNode = last;
                last = node;
                last.nextNode = null;
            }
            numOfItems++;
        }
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> pair : m.entrySet()) {
            this.put(pair.getKey(), pair.getValue());
        }
    }
    
    @Override
    public V remove(Object key) {

        if (key != null) {
            int targetIndex = this.getIndex((K) key);

            Node<K, V> current = table[targetIndex];

            while (current != null) {
                if (current.key.equals(key)) {

                    table[targetIndex] = current.nextChain;

                    if (first.key.equals(key)) {
                        numOfItems--;
                        first = first.nextNode;
                        return first.value;
                    } else {
                        Node<K, V> node = first.nextNode;

                        while (node != null) {
                            if (node.key.equals(key)) {
                                node.previousNode.nextNode = node.nextNode;
                                numOfItems--;
                                return node.value;
                            }
                            node = node.nextNode;
                        }
                    }
                    //   return table[targetIndex].value;
                }
                current = current.nextChain;
            }
        }

        return null;
    }

    @Override
    public Set<K> keySet() {

        if (numOfItems > 0) {
            Set<K> oneSet = new LinkedHashSet<K>();
            Node<K, V> oneNode = last;

            while (oneNode != null) {
                oneSet.add(oneNode.getKey());
                oneNode = oneNode.previousNode;
            }
            return oneSet;
        }
        return null;
    }
    
    @Override
    public void clear() {
        this.table = new Node[this.LENGTH];
        this.first = this.last = null;
        this.numOfItems = 0;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        if (numOfItems > 0) {
            Set<Entry<K, V>> oneSet = new LinkedHashSet<>();
            Node<K, V> aNode = last;

            while (aNode != null) {
                oneSet.add(aNode);
                aNode = aNode.previousNode;
            }
            return oneSet;
        }
        return null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> list = new ArrayList<V>();

        Node<K, V> node = first;

        while (node != null) {
            list.add(node.getValue());
            node = node.nextNode;
        }
        return list;
    }
    
    public String toString() {
        String result = "";
        Node<K, V> current = first;

        result += "Order:\n";
        while (current != null) {
            result += "[" + current.getKey() + " : " + current.value + "]";
            result += " ";
            current = current.nextNode;
        }

        result += "\n";

        result += "Map:\n";

        for (int index = 0; index < table.length; index++) {
            if (table[index] == null) {
                result += "null\n";
            } else {
                result += "[" + table[index].getKey() + " : "
                        + table[index].value + "]";

                Node<K, V> currentChain = table[index].nextChain;

                while (currentChain != null) {
                    result += "->" + "[" + table[index].nextChain.key + " : "
                            + table[index].nextChain.value + "]";
                    currentChain = currentChain.nextChain;
                }

                result += "\n";
            }

        }
        return result;
    }

    private class Node<K, V> implements Map.Entry<K, V> {

        public Node<K, V> nextChain;
        public Node<K, V> nextNode;
        public Node<K, V> previousNode;
        public K key;
        public V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.nextNode = null;
            this.previousNode = null;
        }


        @Override
        public V setValue(V value) {
            this.value = value;
            return this.value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }
        
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (getClass() != o.getClass()) {
                return false;
            }
            Node<K, V> secondNode = (Node<K, V>) o;
            if (!key.equals(secondNode.key)) {
                return false;
            }
            return true;
        }
    }
}
