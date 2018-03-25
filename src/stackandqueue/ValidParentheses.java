package stackandqueue;

import java.util.Stack;

public class ValidParentheses {
    /*
    ID:20
    Given a string containing just the characters
    '(', ')', '{', '}', '[' and ']', determine if
    the input string is valid.

    The brackets must close in the correct order,
    "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     */
    public static boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty())
                return false;
            char top = stack.pop();
            char match;
            if (top == '{')
                match = '}';
            else if (top == '(')
                match = ')';
            else
                match = ']';
            if (c != match)
                return false;
        }
        if (!stack.isEmpty())
            return false;
        return true;
    }

    /*
    ID:150
    Evaluate the value of an arithmetic expression in
    Reverse Polish Notation.

    Valid operators are +, -, *, /. Each operand may
    be an integer or another expression.

    Some examples:
    ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
    ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     */
    public static int evalRPN(String[] tokens) {
        Stack<String> tokenStack = new Stack<>();
        for (String token : tokens) {
            if (isOperator(token)){
                String a = tokenStack.pop();
                String b = tokenStack.pop();
                String sum = "";
                if (token.charAt(0) == '+')
                    sum = add(a, b);
                else if (token.charAt(0) == '-')
                    sum = substract(b, a);
                else if (token.charAt(0) == '*')
                    sum = multiply(a, b);
                else
                    sum = divide(b, a);
                tokenStack.push(sum);
            }else {
                tokenStack.push(token);
            }
        }
        String ans = tokenStack.pop();
        return Integer.parseInt(ans);
    }

    private static boolean isOperator(String a){
        if (a.length() > 1)
            return false;
        if (a.charAt(0) == '+' || a.charAt(0) == '-' ||
                a.charAt(0) == '*' || a.charAt(0) == '/'){
            return true;
        }
        return false;
    }

    private static String add(String a, String b){
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        int sum = x + y;
        return String.valueOf(sum);
    }

    private static String substract(String a, String b){
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        int sum = x - y;
        return String.valueOf(sum);
    }

    private static String multiply(String a, String b){
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        int sum = x * y;
        return String.valueOf(sum);
    }

    private static String divide(String a, String b){
        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        int sum = x / y;
        return String.valueOf(sum);
    }
}
