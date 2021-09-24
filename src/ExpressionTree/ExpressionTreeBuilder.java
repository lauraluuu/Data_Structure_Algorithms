/**
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package ExpressionTree;

import java.util.Stack;

/**
 *
 * @author gkn3798
 */
public class ExpressionTreeBuilder {
    /**
     * This method builds an expression tree comprised of operator and operand
     * nodes
     *
     * @param postfixStrings
     * @return
     */
    public static ExpNode buildExpressionTree(String[] postfixStrings) {
        ExpNode parent = null;
        Stack<ExpNode> stack = new Stack<ExpNode>();

        for (String symbol : postfixStrings) {

            boolean isOperator = isOperator(symbol);
            //        System.out.println(isOperator);
            if (!isOperator) {
                ExpNode newNode = new OperandNode(symbol);
                //             System.out.print(newNode.symbol);
                stack.push(newNode);
            } else {
                parent = new OperatorNode(symbol);
                //              System.out.print(p.symbol);
                if (parent.symbol.equals("~")) {

                    parent.rightChild = stack.pop();
                } else {

                    parent.rightChild = stack.pop();
                    parent.leftChild = stack.pop();
                }
                stack.push(parent);
            }
        }
        return parent;
    }

    public static boolean isOperator(String symbol) {
        if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*")
                || symbol.equals("/") || symbol.equals("~")) {
            return true;
        }
        return false;
    }

    //This method countNodes count the number of nodes down the left and right
    //subtrees
    public static int countNodes(ExpNode node) {
        int count =0;
        
        if (node != null) {
            count++;
            if (node.leftChild != null) {
                count += countNodes(node.leftChild);
            }
            if (node.rightChild != null) {
                count += countNodes(node.rightChild);
            }
        } 
        return count;
    }

    //This method converts a binary expression tree into infix notation usging brackets
    public static String toInfixString(ExpNode node) {
        String result = "";
        if (node != null) {
            if (isOperator(node.symbol)) {
                result += "(";
            }
            if (node.leftChild != null) {
                result += toInfixString(node.leftChild);
            }
            result += node.symbol;

            if (node.rightChild != null) {
                result += toInfixString(node.rightChild);
            }
            if (isOperator(node.symbol)) {
                result += ")";
            }
        }
        return result;
    }

    public static double calculationResult(ExpNode node) {
        double result = 0;
        
        if (node != null) {
            if (isOperator(node.symbol)) {
                result += node.evaluate();
            }
        }
        
        return result;
    }

    public static void main(String[] args) {

        String postfix = "10 2 ~ - 3 4 * +";
        String[] tokens = postfix.split(" ");

        ExpNode root = ExpressionTreeBuilder.buildExpressionTree(tokens);

        System.out.println(ExpressionTreeBuilder.toInfixString(root));
        System.out.println("Result： " + ExpressionTreeBuilder.calculationResult(root));
        System.out.println("count node1: " + ExpressionTreeBuilder.countNodes(root));

        System.out.println("-----------------------");
        String postfix2 = "3 1 + 3 4 * / 2 2 * ~ 10 10 + - +";
        String[] tokens2 = postfix2.split(" ");

        ExpNode root2 = ExpressionTreeBuilder.buildExpressionTree(tokens2);

        System.out.println(ExpressionTreeBuilder.toInfixString(root2));

        System.out.println("Result： " + ExpressionTreeBuilder.calculationResult(root2));

        System.out.println("count node1: " + ExpressionTreeBuilder.countNodes(root2));
    }
}
