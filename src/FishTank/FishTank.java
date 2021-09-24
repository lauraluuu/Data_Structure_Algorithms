/**
 * This FishTank class creates a GUI for all the fish in the fishShoal 
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package FishTank;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author gkn3798
 */
public class FishTank extends JPanel implements ActionListener {

    public final int PANEL_WIDTH = 600;
    public final int PANEL_HEIGHT = 600;
    private DrawingPanel drawPanel;
    private JButton add;
    private FishShoal fishShoal;
    private Timer timer;

    public FishTank() {
        super(new BorderLayout());

        fishShoal = new FishShoal();
        
        //add button to add a single fish in the shoal
        add = new JButton("Add Fish");
        add.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(add);

        drawPanel = new DrawingPanel();
        add(drawPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == add) {
            Fish aFish = new Fish(fishShoal);
            Thread fishThread = new Thread(aFish);
            fishThread.start();
            fishShoal.add(aFish);
        }

        drawPanel.repaint();
    }
    /**
     * This DrawingPanel is a seperate inner class for graphics
     * 
     */
    private class DrawingPanel extends JPanel {

        public DrawingPanel() {
            setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            setBackground(Color.WHITE);
        }
        
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            
            fishShoal.drawShoal(g);
        }
    }
    
        public static void main(String[] args) {
        FishTank fishPanel = new FishTank();
        JFrame fishFrame = new JFrame("Fish Bowl"); 	
        fishFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fishFrame.getContentPane().add(fishPanel);  
        fishFrame.pack(); 
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        fishFrame.setLocation(new Point((dimension.width / 2) - (fishFrame.getWidth() / 2), (dimension.height / 2) - (fishFrame.getHeight() / 2)));
        fishFrame.setVisible(true);
    }
}
