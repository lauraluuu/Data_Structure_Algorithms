package ExpressionTree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Almost Complete GUI - just need to finish the code when pressing the buttons
 * and updating the number of nodes in the tree.. WIll only build once ExpNode
 * subclasses are made
 *
 * @author sehall
 */
public class ExpressionTreeGUI extends JPanel implements ActionListener {

    private final JButton evaluateButton, buildTreeButton;

    private DrawPanel drawPanel;
    private ExpNode root;
    private int numberNodes = 0;
    private JTextField postFixField;
    public static int PANEL_H = 500;
    public static int PANEL_W = 700;
    private JLabel nodeCounterLabel;
    private final int BOX_SIZE = 40;

    public ExpressionTreeGUI() {
        super(new BorderLayout());
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        root = null;
        super.setPreferredSize(new Dimension(PANEL_W, PANEL_H + 30));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(PANEL_W, 30));
        drawPanel = new DrawPanel();

        evaluateButton = new JButton("Evaluate to infix");
        buildTreeButton = new JButton("Build Expression Tree");

        evaluateButton.addActionListener((ActionListener) this);
        buildTreeButton.addActionListener((ActionListener) this);

        postFixField = new JTextField(40);

        buttonPanel.add(postFixField);
        buttonPanel.add(buildTreeButton);
        buttonPanel.add(evaluateButton);

        super.add(drawPanel, BorderLayout.CENTER);
        super.add(buttonPanel, BorderLayout.SOUTH);

        nodeCounterLabel = new JLabel("Number of Nodes: " + 0);
        super.add(nodeCounterLabel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        ExpressionTreeBuilder builder = new ExpressionTreeBuilder();

        if (source == evaluateButton) {   //finish this button event to handle the evaluation and output to infix of the tree 
            if (root == null) {
                JOptionPane.showMessageDialog(this, "Tree is null, not built", "INFO",
                        JOptionPane.ERROR_MESSAGE);
            } else {

                String infixString = ExpressionTreeBuilder.toInfixString(root);

                JOptionPane.showMessageDialog(this, infixString+" = " + ExpressionTreeBuilder.calculationResult(root), "INFO",
                        JOptionPane.ERROR_MESSAGE);
                postFixField.setText("");
                // COMPLETE ME!!!!!!!!!!
                //OUTPUT THE INFIX EXPRESSION AND RESULT HERE - Use ExpressionTreeBuilder
            }

        } else if (source == buildTreeButton && postFixField.getText() != null) {

            String line = postFixField.getText();
            String[] tokens = line.split(" ");
            root = ExpressionTreeBuilder.buildExpressionTree(tokens);
            numberNodes = ExpressionTreeBuilder.countNodes(root);
            
            if(numberNodes!=tokens.length){
                JOptionPane.showMessageDialog(this, "Tree is not valid", "INFO",
                        JOptionPane.ERROR_MESSAGE);
                postFixField.setText("");
            }

            // COMPLETE ME!!!!!!!!!!
            //Use ExpressionTreeBuilder to build the tree
        }

        nodeCounterLabel.setText("Number of Nodes: " + numberNodes);
        //COMPLETE ME!!!
        //Update the number of nodes label in the tree - if any use ExpressionTreeBuilder
        drawPanel.repaint();
    }

    private class DrawPanel extends JPanel {

        public DrawPanel() {
            super();
            super.setBackground(Color.WHITE);
            super.setPreferredSize(new Dimension(PANEL_W, PANEL_H));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (root != null) {
                drawTree(g, getWidth());
            }
        }

        public void drawTree(Graphics g, int width) {
            drawNode(g, root, BOX_SIZE, 0, 0, new HashMap<>());
        }

        private int drawNode(Graphics g, ExpNode current,
                int x, int level, int nodeCount, Map<ExpNode, Point> map) {

            if (current.leftChild != null) {
                nodeCount = drawNode(g, current.leftChild, x, level + 1, nodeCount, map);
            }

            int currentX = x + nodeCount * BOX_SIZE;
            int currentY = level * 2 * BOX_SIZE + BOX_SIZE;
            nodeCount++;
            map.put(current, new Point(currentX, currentY));

            if (current.rightChild != null) {
                nodeCount = drawNode(g, current.rightChild, x, level + 1, nodeCount, map);
            }

            g.setColor(Color.red);
            if (current.leftChild != null) {
                Point leftPoint = map.get(current.leftChild);
                g.drawLine(currentX, currentY, leftPoint.x, leftPoint.y - BOX_SIZE / 2);
            }
            if (current.rightChild != null) {
                Point rightPoint = map.get(current.rightChild);
                g.drawLine(currentX, currentY, rightPoint.x, rightPoint.y - BOX_SIZE / 2);

            }
            if (current instanceof OperandNode) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.YELLOW);
            }

            Point currentPoint = map.get(current);
            g.fillRect(currentPoint.x - BOX_SIZE / 2, currentPoint.y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
            g.setColor(Color.BLACK);
            g.drawRect(currentPoint.x - BOX_SIZE / 2, currentPoint.y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
            Font f = new Font("courier new", Font.BOLD, 16);
            g.setFont(f);
            if (current instanceof OperandNode) {
                g.drawString(current.toString(), currentPoint.x - current.toString().length() * 4, currentPoint.y);
            } else {
                g.drawString(current.toString(), currentPoint.x - 3, currentPoint.y);
            }
            return nodeCount;

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Expression Tree GUI builder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ExpressionTreeGUI());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int screenHeight = dimension.height;
        int screenWidth = dimension.width;
        frame.pack();             //resize frame apropriately for its content
        //positions frame in center of screen
        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2),
                (screenHeight / 2) - (frame.getHeight() / 2)));
        frame.setVisible(true);
    }
}
