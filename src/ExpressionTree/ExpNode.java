/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExpressionTree;

/**
 *
 * @author Seth
 */
public abstract class ExpNode {
    public ExpNode leftChild;
    public ExpNode rightChild;
    
    protected String symbol;
    
    public ExpNode(String value)
    {
        this.symbol = value;
    }
    @Override
    public String toString()
    {
        return symbol;
    }
    public abstract double evaluate() throws ArithmeticException;
}
