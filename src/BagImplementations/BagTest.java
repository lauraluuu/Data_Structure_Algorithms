/**
 * This BagApp class is to test both the bag implementations
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package BagImplementations;

/**
 *
 * @author gkn3798
 */
public class BagTest {

    public static void main(String[] args) {
        System.out.println("The first test for ArrayBag: ");
        ArrayBag<Integer> arrayTest = new ArrayBag<Integer>();

        for (int i = 0; i < 10; i++) {
            arrayTest.add(i);
        }

        System.out.println("The size of the bag is: " + arrayTest.size());
        System.out.println("Is the bag full? " + arrayTest.isFull());
        System.out.println("The random item we grab is: " + arrayTest.grab());
        System.out.print("The array in the bag before removing an item: " + arrayTest.toString() + " \n");
        arrayTest.remove(2);
        System.out.println("The size in the bag after removing an item: " + arrayTest.size());
        System.out.print("The array in the bag after removing an item: " + arrayTest.toString() + "\n");
        System.out.println("The capacity remaining is: " + arrayTest.capacityRemaining());
        System.out.println("The size of the bag is: " + arrayTest.size());
        System.out.println("Is the bag empty? " + arrayTest.isEmpty());
        System.out.println("Is the bag full? " + arrayTest.isFull());
        arrayTest.clear();
        System.out.println("The size of the bag after calling the clear method is: " + arrayTest.size());
        System.out.println("Is the bag empty? " + arrayTest.isEmpty());

        System.out.println("\n\n--------------------------------------------------------\n\n");
        System.out.println("The second test for ArrayBag: ");
        ArrayBag<Integer> arrayTest2 = new ArrayBag<Integer>(20);
        for (int i = 0; i < 10; i++) {
            arrayTest2.add(i);
        }

        System.out.println("The size of the bag is: " + arrayTest2.size());
        System.out.println("Is the bag full? " + arrayTest2.isFull());
        System.out.println("The random item we grab is: " + arrayTest2.grab());
        System.out.print("The array in the bag before removing an item: " + arrayTest2.toString() + " \n");
        arrayTest2.remove(2);
        System.out.println("The size in the bag after removing an item: " + arrayTest2.size());
        System.out.print("The array in the bag after removing an item: " + arrayTest2.toString() + "\n");
        System.out.println("The capacity remaining is: " + arrayTest2.capacityRemaining());
        System.out.println("The size of the bag is: " + arrayTest2.size());
        System.out.println("Is the bag empty? " + arrayTest2.isEmpty());
        System.out.println("Is the bag full? " + arrayTest2.isFull());
        arrayTest2.clear();
        System.out.println("The size of the bag after calling the clear method is: " + arrayTest2.size());
        System.out.println("Is the bag empty? " + arrayTest2.isEmpty());
        
        System.out.println("\n\n--------------------------------------------------------\n\n");
        System.out.println("The first test for LinkedBag: ");
        LinkedBag<Integer> linkedTest1 = new LinkedBag<Integer> ();
        for (int i = 10; i < 20; i++) {
            linkedTest1.add(i);
        }
        System.out.println("The size of the bag is: " + linkedTest1.size());
        System.out.println("Is the bag full? " + linkedTest1.isFull());
        System.out.println("The random item we grab is: " + linkedTest1.grab());
        System.out.print("The array in the bag before removing an item: " + linkedTest1.toString() + " \n");
        linkedTest1.remove(19);
        System.out.println("The size in the bag after removing an item: " + linkedTest1.size());
        System.out.print("The array in the bag after removing an item: " + linkedTest1.toString() + "\n");
        System.out.println("The capacity remaining is: " + linkedTest1.capacityRemaining());
        System.out.println("The size of the bag is: " + linkedTest1.size());
        System.out.println("Is the bag empty? " + linkedTest1.isEmpty());
        System.out.println("Is the bag full? " + linkedTest1.isFull());
        linkedTest1.clear();
        System.out.println("The size of the bag after calling the clear method is: " + linkedTest1.size());
        System.out.println("Is the bag empty? " + linkedTest1.isEmpty());
        
        System.out.println("\n\n--------------------------------------------------------\n\n");
        System.out.println("The second test for LinkedBag: ");
        LinkedBag<Integer> linkedTest2 = new LinkedBag<Integer> (30);
        for (int i = 10; i < 20; i++) {
            linkedTest2.add(i);
        }
        System.out.println("The size of the bag is: " + linkedTest2.size());
        System.out.println("Is the bag full? " + linkedTest2.isFull());
        System.out.println("The random item we grab is: " + linkedTest2.grab());
        System.out.print("The array in the bag before removing an item: " + linkedTest2.toString() + " \n");
        linkedTest2.remove(19);
        System.out.println("The size of the bag after removing is: " + linkedTest2.size());
        System.out.print("The array in the bag after removing an item: " + linkedTest2.toString() + "\n");
        System.out.println("The capacity remaining is: " + linkedTest2.capacityRemaining());
        System.out.println("The size of the bag is: " + linkedTest2.size());
        System.out.println("Is the bag empty? " + linkedTest2.isEmpty());
        System.out.println("Is the bag full? " + linkedTest2.isFull());
        linkedTest2.clear();
        System.out.println("The size of the bag after calling the clear method is: " + linkedTest2.size());
        System.out.println("Is the bag empty? " + linkedTest2.isEmpty());
    }
}
