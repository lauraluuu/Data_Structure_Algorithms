/**
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package ExpressionTree;

/**
 *
 * @author gkn3798
 */
public class OperatorNode extends ExpNode {

    public OperatorNode(String symbol) {
        super(symbol);
    }

    @Override
    public double evaluate() throws ArithmeticException {
        double value = 0;

        switch (this.symbol) {
            case "+":
                value = this.leftChild.evaluate() + this.rightChild.evaluate();
                break;
            case "-":
                value = this.leftChild.evaluate() - this.rightChild.evaluate();
                break;
            case "*":
                value = this.leftChild.evaluate() * this.rightChild.evaluate();
                break;
            case "/":
                value = this.leftChild.evaluate() / this.rightChild.evaluate();
                break;
            case "~":
                value = 1 / this.rightChild.evaluate();
                break;
            default:
                throw new IllegalArgumentException("Illegal symbol");
        }

        return value;
    }

}
