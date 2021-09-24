/**
 * Student Name: Peifen Lu
 * Student ID: 18008550
 */
package ExpressionTree;

/**
 *
 * @author gkn3798
 */
public class OperandNode extends ExpNode {

    public OperandNode(String value) {
        super(value);
        this.leftChild = null;
        this.rightChild = null;
    }

    @Override
    public double evaluate() throws ArithmeticException {
        double result = 0;

        try {
            result = Double.parseDouble(this.symbol);

        } catch (NumberFormatException e) {
            System.out.println("Number Format invalid");
        }
        return result;
        //     return (Double.parseDouble(this.symbol));
    }
}
