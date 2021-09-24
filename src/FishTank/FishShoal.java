/**
 * This FishShoal class represents a shoal of fish
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package FishTank;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author gkn3798
 */
public class FishShoal {

    private ArrayList<Fish> fishList;

    //constructor
    public FishShoal() {
        this.fishList = new ArrayList<Fish>();
    }

    public ArrayList<Fish> getFishList() {
        return fishList;
    }

    /**
     * This size method is to obtain the number of fish in the shoal
     * @return 
     */
    public int size() {
        return fishList.size();
    }

    public void setFishList(ArrayList<Fish> aList) {
        this.fishList = aList;
    }

    public void add(Fish fish) {
        this.fishList.add(fish);
    }

    public synchronized void remove(Fish fish) {
        this.fishList.remove(fish);
    }

    /**
     * The drawShoal method calls the draw method to draw all fish
     * in the shoal
     * @param g 
     */
    public void drawShoal(Graphics g) {
        for (Fish oneFish : fishList) {
            oneFish.draw(g);
        }
    }

    /**
     * This canEat method checks if the parameter fish can eat any other fish
     * in the shoal. It returns a target fish if meets the rules
     * @param fish
     * @return 
     */
    public synchronized Fish canEat(Fish fish) {
        for (Fish targetFish : fishList) {
            if (fish.getSize() >= (targetFish.getSize() * 1.4)) {
                boolean condition1 = (fish.getX() >= targetFish.getX()
                        && fish.getX() <= targetFish.getX() + targetFish.getSize())
                        && (fish.getY() >= targetFish.getY()
                        && fish.getY() <= targetFish.getY() + targetFish.getSize());
                boolean condition2 = (fish.getX() <= targetFish.getX()
                        && fish.getX() >= targetFish.getX() + targetFish.getSize())
                        && (fish.getY() >= targetFish.getY()
                        && fish.getY() <= targetFish.getY() + targetFish.getSize());
                boolean condition3 = (fish.getX() <= targetFish.getX()
                        && fish.getX() >= targetFish.getX() + targetFish.getSize())
                        && (fish.getY() <= targetFish.getY()
                        && fish.getY() >= targetFish.getY() + targetFish.getSize());
                boolean condition4 = (fish.getX() >= targetFish.getX()
                        && fish.getX() <= targetFish.getX() + targetFish.getSize())
                        && (fish.getY() <= targetFish.getY()
                        && fish.getY() >= targetFish.getY() + targetFish.getSize());

                if (condition1 || condition2 || condition3 || condition4){
                    return targetFish;
                }
            }
        }
        return null;
    }
}
