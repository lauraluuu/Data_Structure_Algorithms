/**
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package MapImplementation;

import java.util.Map;

/**
 *
 * @author gkn3798
 */
public class Test {

    public static void main(String[] args) {
        Map<String, String> oneMap = new LinkedHashMapWithChaining<>();

        oneMap.put("1", "cat");
        oneMap.put("5", "dog");
        oneMap.put("25", "bird");
        oneMap.put("15", "frog");
        oneMap.put("19", "turtle");
        oneMap.put("13", "rabbit");
        oneMap.put("20", "fish");
        oneMap.put("30", "fox");
        System.out.println("\n-------------Testing the toString methods--------------\n");
        System.out.println("Printing the order and the map structure:");
        System.out.println(oneMap);
        System.out.println("\n-------------Testing the contains methods--------------\n");
        System.out.println("checking the existence of key 5: " + oneMap.containsKey("1"));
        System.out.println("checking the existence of value fox: " + oneMap.containsValue("fox"));
        System.out.println("checking a key which not in the map: " + oneMap.containsKey("99"));
        System.out.println("checking a value which not in the map: " + oneMap.containsKey("mouse"));
        System.out.println("The contain methods work out!");
        System.out.println("\n-------------Testing the remove methods--------------\n");
        System.out.println("Before removing any item, the size is: " + oneMap.size());
        oneMap.remove("1");
        System.out.println(oneMap);
        System.out.println("After removing an item, the size is: " + oneMap.size());
        System.out.println("The remove methods work out!");
        System.out.println("\n-------------Testing get methods--------------\n");
        System.out.println("The value paired with the key 20 is: "+oneMap.get("20"));
        System.out.println("\n-------------Testing other methods--------------\n");
        System.out.println("The map is empty? " + oneMap.isEmpty());
        System.out.println("calling the clear method");
        oneMap.clear();
        System.out.println("After calling the clear method, the map is empty? " + oneMap.isEmpty());
    }
}
