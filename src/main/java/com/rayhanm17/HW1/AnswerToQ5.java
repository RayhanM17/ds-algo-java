package com.rayhanm17.HW1;

public class AnswerToQ3B {
    public static void main(String[] args) {
        
    }

    // Question 5
	public static boolean isBalanced(String input, char[] openingSymbols, char[] closingSymbols) {
        Map<Character, Character> symbolMap = new HashMap<>();
        for (int i = 0; i < openingSymbols.length; i++) {
            symbolMap.put(closingSymbols[i], openingSymbols[i]);
        }

        Stack<Character> stack = new Stack<>();

        for (char ch : input.toCharArray()) {
            if (symbolMap.containsValue(ch)) {
                stack.push(ch);
            } else if (symbolMap.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != symbolMap.get(ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}