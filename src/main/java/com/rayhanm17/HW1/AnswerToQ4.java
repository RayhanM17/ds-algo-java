package com.rayhanm17.HW1;

public class AnswerToQ3B {
    public static void main(String[] args) {
        
    }

    // Question 4
	public static void removeValuesLargerThanMax(LinkedList<Integer> list, int max) {
        // Use an iterator to remove elements
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() > max) {
                iterator.remove();
            }
        }
    }
}