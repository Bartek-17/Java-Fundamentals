import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParenthesesMatcher {
    
    // Method to check if parentheses in the expression are matched
    public static boolean areParenthesesMatched(String expression) {
        Stack<Character> stack = new Stack<>();
        
        // Regular expression pattern to match only parentheses
        Pattern pattern = Pattern.compile("[(){}\\[\\]]");
        Matcher matcher = pattern.matcher(expression);
        
        // Traverse the expression and process each parenthesis
        while (matcher.find()) {
            char current = matcher.group().charAt(0);
            
            // If it's an opening parenthesis, push it onto the stack
            if (current == '(' || current == '{' || current == '[') {
                stack.push(current);
            } 
            // If it's a closing parenthesis, check if it matches the last opened one
            else {
                if (stack.isEmpty()) {
                    return false;  // Unmatched closing parenthesis
                }
                char lastOpened = stack.pop();
                if (!isMatchingPair(lastOpened, current)) {
                    return false;  // Mismatched pair
                }
            }
        }
        
        // If stack is empty, all parentheses are properly matched
        return stack.isEmpty();
    }
    
    // Method to check if the opening and closing parentheses are a matching pair
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') || 
               (open == '{' && close == '}') || 
               (open == '[' && close == ']');
    }
    
    public static void main(String[] args) {
        // Sample expressions to test
        String expression1 = "3 + (2 - (1 * 4))"; // Correctly matched
        String expression2 = "(2 + 3 * (4 - 5]";   // Incorrectly matched
        String expression3 = "{2 + [3 * (4 + 5)]}"; // Correctly matched
        
        // Check parentheses matching for each expression
        System.out.println("Expression 1: " + (areParenthesesMatched(expression1) ? "Matched" : "Not Matched"));
        System.out.println("Expression 2: " + (areParenthesesMatched(expression2) ? "Matched" : "Not Matched"));
        System.out.println("Expression 3: " + (areParenthesesMatched(expression3) ? "Matched" : "Not Matched"));
    }
}
