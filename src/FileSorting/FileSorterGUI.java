/**
 * Student Name:Peifen Lu
 * Student ID:18008550
 * The FileSorterGUI runs FileSorting tasks.
 * To test the functions, please enter the file "girlnames.txt"
 */
package FileSorting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author gkn3798
 */
public class FileSorterGUI extends JPanel implements ActionListener {

    public final int PANEL_WIDTH = 360;
    public final int PANEL_HEIGHT = 360;
    private JButton queue;
    private JButton execute;
    private JTextField taskField;
    private JTextField memoryField;
    private JLabel inputLabel;
    private JLabel limitLabel;
    private Queue<String> tasks;

    public FileSorterGUI() {
        super(new BorderLayout());

        inputLabel = new JLabel("file for sorted: ");

        taskField = new JTextField(10);

        limitLabel = new JLabel("memory limit: ");
        memoryField = new JTextField(10);

        queue = new JButton("Queue new task");

        queue.addActionListener(this);

        execute = new JButton("Execute task");

        execute.addActionListener(this);

        JPanel memoryPanel = new JPanel();
        memoryPanel.add(limitLabel);
        memoryPanel.add(memoryField);

        JPanel inputPanel = new JPanel();
        inputPanel.add(inputLabel);
        inputPanel.add(taskField);

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(queue);
        buttonPanel.add(execute);

        add(memoryPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        tasks = new LinkedList<String>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == queue) {
            String line1 = memoryField.getText();
            String line2 = taskField.getText();

            if (!line1.equals("") && !line2.equals("")) {
                tasks.add(line1 + " " + line2);

                JOptionPane.showMessageDialog(null, "task is in the queue", "Process", JOptionPane.PLAIN_MESSAGE);
                memoryField.setText("");
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "input cannot be null", "Error", JOptionPane.PLAIN_MESSAGE);
            }

        } else if (source == execute) {
            BufferedReader in = null;

            try {
                if (tasks.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "no tasks in the queue", "Error", JOptionPane.PLAIN_MESSAGE);
                } else {
                    String temps = tasks.remove();

                    String[] tokens = temps.split(" ");
                    int memory = Integer.parseInt(tokens[0]);
                    File fileName = new File(tokens[1]);

                    in = new BufferedReader(new FileReader(fileName));

                    FileSorter sorter = new FileSorter(memory, fileName);
                    Thread thread = new Thread(sorter);
                    thread.start();
                    System.out.println("Execute successfully!");
                    JOptionPane.showMessageDialog(null, "task has been executed successfully", "Process", JOptionPane.PLAIN_MESSAGE);
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileSorterGUI.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "cannot find the file in the path", "Process", JOptionPane.PLAIN_MESSAGE);
            } finally {
                try {
                    if(in!=null){
                        in.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FileSorterGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        FileSorterGUI fishPanel = new FileSorterGUI();
        JFrame fileFrame = new JFrame("FileSorter App");
        fileFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fileFrame.getContentPane().add(fishPanel);
        fileFrame.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        fileFrame.setLocation(new Point((dimension.width / 2) - (fileFrame.getWidth() / 2), (dimension.height / 2) - (fileFrame.getHeight() / 2)));
        fileFrame.setVisible(true);
    }
}
