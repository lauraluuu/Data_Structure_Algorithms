/**
 * This Fish class demonstrates a fish swimming in a tank
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package FishTank;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author gkn3798
 */
public class Fish implements Runnable {

    private double x;
    private double y;
    private double dx;
    private double dy;
    private double size;
    private boolean isAlive;
    private int world_width;
    public int world_height;
    private Color[] colour;
    private FishShoal shoal;

    private final Random generator = new Random();
    private final int minSize = 3;
    private final int maxSize = 150;

    //Constructor takes FishShoal as a parameter
    public Fish(FishShoal shoal) {
        this.shoal = shoal;
        this.world_height = 600;
        this.world_width = 600;
        this.x = (double) generator.nextInt(world_width);
        this.y = (double) generator.nextInt(world_height);
        this.size = generator.nextInt(80) + minSize;
        if (this.x <= this.world_width / 2 && this.y <= this.world_height / 2) {
            this.dx = generator.nextInt(10) + 1;
            this.dy = generator.nextInt(8) + 1;
        } else if (this.x <= this.world_width / 2 && this.y > this.world_height / 2) {
            this.dx = generator.nextInt(10) + 1;
            this.dy = -(generator.nextInt(8) + 1);
        } else if (this.x > this.world_width / 2 && this.y <= this.world_height / 2) {
            this.dx = -(generator.nextInt(10) + 1);
            this.dy = generator.nextInt(8) + 1;
        } else {
            this.dx = -(generator.nextInt(10) + 1);
            this.dy = -(generator.nextInt(8) + 1);
        }

        this.isAlive = true;
        this.colour = new Color[3];
        for (int i = 0; i < 3; i++) {
            colour[i] = new Color(generator.nextInt(256), generator.nextInt(256), generator.nextInt(256));
        }
    }

    public void run() {
        while (this.isAlive) {
            try {
                move();
                Thread.sleep(30);
            } catch (InterruptedException ex) {

            }
        }
        System.out.println("the target fish thread stopped!");
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getSize() {
        return this.size;
    }

    /**
     * This kill method kills the fish by stopping its thread from running
     */
    public void kill() {
        this.setIsAlive(false);
        this.shoal.remove(this);
    }

    /**
     * This move method moves a fish around the world
     * and change the direction of its movement when it reach the edge of
     * the world. It also calls the eat method to eat the target fish
     */
    private void move() {
        double noiseFactor = generator.nextGaussian() * Math.sqrt(0.5) + 0;
        
        int random = generator.nextInt(10);
        
        
        //add a small randomized noise factor after several movements
        if (random % 3 == 0){
            
            if (dx+noiseFactor <= 10 && dx+noiseFactor >= 1){
                dx += noiseFactor;
            }
            
            if (dy+noiseFactor <= 8 && dy+noiseFactor >= 1){
                dy += noiseFactor;
            }
        }
        
        this.x += dx;
        this.y += dy;

        if (x <= 0) {
            x = this.size;
            dx = -dx;
        }
        if (x + size >= this.world_width) {
            x = this.world_width - this.size;
            dx = -dx;
        }
        if (y <= 0) {
            y = this.size;
            dy = -dy;
        }
        if (y + this.size >= this.world_height) {
            y = this.world_height - size;
            dy = -dy;
        }

        Fish targetFish = this.shoal.canEat(this);

        if (targetFish != null) {
            this.eat(targetFish);
        }
    }

    /**
     * This eat method feeds the fish with a target fish and increase
     * the fish's size.
     * It also calls the kill method to stop the target thread
     * @param target 
     */
    public synchronized void eat(Fish target) {
        if (this.size >= (target.size * (1 + 0.4))) {
            if (this.size+target.size>this.maxSize){
                this.size = this.maxSize;
            } else{
                this.size += target.size;
            }
            
            target.kill();
        }
    }

    /**
     * This draw method draws a three coloured fish
     * @param g 
     */
    public void draw(Graphics g) {
        double speed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        double velX = this.size * dx / (2 * speed);
        double velY = this.size * dy / (2 * speed);
        g.setColor(colour[0]);
        g.drawLine((int) x, (int) y, (int) (x - velX + velY), (int) (y - velX - velY));
        g.setColor(colour[1]);
        g.drawLine((int) x, (int) y, (int) (x - velX - velY), (int) (y + velX - velY));
        g.setColor(colour[2]);
        g.drawLine((int) x, (int) y, (int) (x - 2 * velX), (int) (y - 2 * velY));
    }
}
