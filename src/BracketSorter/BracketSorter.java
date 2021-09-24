/**
 * This class implements an algorithm to evaluate whether the opening and closing
 * bracket and brace pairs match up within a string
 * Student ID: 18008500
 * Student Name: Peifen Lu
 */
package BracketSorter;

import java.util.Stack;

/**
 *
 * @author gkn3798
 */
public class BracketSorter {

    public static boolean bracketSorted(String input) {
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);

            if (character == '{' || character == '['
                    || character == '(' || character == '<') {
                stack.push(character);
            }

            if (character == '}' || character == ']'
                    || character == ')' || character == '>') {
                if (stack.isEmpty()) {
                    return false;
                }
            }

            char leftBracket;

            switch (character) {
                case '}':
                    leftBracket = stack.pop();
                    if (leftBracket == '(' || leftBracket == '<'
                            || leftBracket == '[') {
                        return false;
                    }
                    break;

                case ']':
                    leftBracket = stack.pop();
                    if (leftBracket == '(' || leftBracket == '<'
                            || leftBracket == '{') {
                        return false;
                    }
                    break;
                case ')':
                    leftBracket = stack.pop();
                    if (leftBracket == '[' || leftBracket == '<'
                            || leftBracket == '{') {
                        return false;
                    }
                    break;
                case '>':
                    leftBracket = stack.pop();
                    if (leftBracket == '[' || leftBracket == '('
                            || leftBracket == '{') {
                        return false;
                    }
                    break;
            }
        }
        
        if (!stack.isEmpty()){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] examples = new String[6];

        examples[0] = "{((2*5)+(3*-2+5))}";
        examples[1] = "{(2*5)+(3*-2+5))}";
        examples[2] = "List<List>";
        examples[3] = "List<List";
        examples[4] = "{(<>){}{...}(e(e)e){hello}}";
        examples[5] = "{(<eeeek>>){}{...}e(e)e){hello}";

        for (int i = 0; i < 6; i++) {
            System.out.println(examples[i]);
            if (bracketSorted(examples[i])) {
                System.out.println("match up all bracket and brace pairs and evaluate successfully!\n");
            } else {
                System.out.println("fail to match up all the bracket and brace pairs!\n");
            }
        }

    }
}
