package com.rayhanm17;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {

        System.out.println(findDistinguishingChar("apple", "pale"));
        System.out.println(findDistinguishingChar("banana", "ban"));
        System.out.println(findDistinguishingChar("ban", "banner"));
        System.out.println(isAPotentialPalindrome("racar"));

        int[] numbers = {10,20,30,10};
        System.out.println(sumOfUniqueElements(numbers));

        // Creating a sample binary tree:
        //         5
        //       /   \
        //      3     8
        //     / \   / \
        //    1   4 7   10
        
        BinaryNode<Integer> root = new BinaryNode<>(5);
        root.left = new BinaryNode<>(3);
        root.right = new BinaryNode<>(8);
        root.left.left = new BinaryNode<>(1);
        root.left.right = new BinaryNode<>(4);
        root.right.left = new BinaryNode<>(7);
        root.right.right = new BinaryNode<>(10);

        System.out.println(countGreaterThanValue(root, 4));

        // Creating a sample binary tree:
        //         1
        //       /   \
        //      2     2
        //     / \   / \
        //    3   4 4   3

        BinaryNode<Integer> symmetricRoot = new BinaryNode<>(1);
        symmetricRoot.left = new BinaryNode<>(2);
        symmetricRoot.right = new BinaryNode<>(2);
        symmetricRoot.left.left = new BinaryNode<>(3);
        symmetricRoot.left.right = new BinaryNode<>(4);
        symmetricRoot.right.left = new BinaryNode<>(4);
        symmetricRoot.right.right = new BinaryNode<>(3);

        System.out.println(isSymmetric(symmetricRoot)); // Expected: true
        System.out.println(isSymmetric(root)); // Expected: false

        System.out.println(removeLeaves(symmetricRoot));
    }

    public static char findDistinguishingChar(String word1, String word2) {
        char dist = ' ';
        int appear = 0;


        HashMap<Character, Integer> charFrequency = new HashMap<>();

        // create frequency table
        for(char c : word1.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        //loop through largest  and look for matches
        for (char c : word2.toCharArray()) {
            if (charFrequency.containsKey(c) && charFrequency.get(c) > appear) {
                dist = c;
                appear = charFrequency.get(c);
            }
        }


        return dist;
    }

    public static boolean isAPotentialPalindrome(String word) {
        HashMap<Character, Integer> charFrequency = new HashMap<>();

        // Count the frequency of each character
        for (char c : word.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        // Count characters with an odd frequency
        int oddCount = 0;
        for (int count : charFrequency.values()) {
            if (count % 2 != 0) {
                oddCount++;
            }
            
            if (oddCount > 1) {
                return false;
            }
        }

        return true;
    }

    public static int sumOfUniqueElements(int[] numbers) {
        HashMap<Integer, Integer> numFrequency = new HashMap<>();
        int sum = 0;

        // Count frequency of each number
        for (int num : numbers) {
            numFrequency.put(num, numFrequency.getOrDefault(num, 0) + 1);
        }

        // Sum unique elements (frequency of 1)
        for (int num : numFrequency.keySet()) {
            if (numFrequency.get(num) == 1) {
                sum += num;
            }
        }

        return sum;
    }

    public static int countGreaterThanValue(BinaryNode<Integer> root, int value) {
        // Base case: If the node is null, return 0
        if (root == null) {
            return 0;
        }

        // Count this node if its value is greater than the specified value
        int count = (root.element > value) ? 1 : 0;

        // Recursively count in the left and right subtrees
        count += countGreaterThanValue(root.left, value);
        count += countGreaterThanValue(root.right, value);

        return count;
    }

    public static boolean isSymmetric(BinaryNode<Integer> root) {
        if (root == null) {
            return true; // An empty tree is symmetric
        }
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(BinaryNode<Integer> left, BinaryNode<Integer> right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        // Check if the values are equal and the left subtree of left node is a mirror of the right subtree of the right node, and vice versa
        return (left.element == right.element) && isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public static BinaryNode removeLeaves(BinaryNode root) {
        if (root == null) {
            return null;
        }
        
        // Check if the current node is a leaf
        if (root.left == null && root.right == null) {
            return null; // Remove the leaf node by returning null
        }
        
        // Recursively remove leaves from left and right subtrees
        root.left = removeLeaves(root.left);
        root.right = removeLeaves(root.right);
        
        return root; // Return the updated root node
    }
}