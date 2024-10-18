package com.rayhanm17;

public class App {
    public static void main(String[] args) {

        // Example post-order array
        int[] postorder = {7 ,4 ,2 ,8 ,5 ,6 ,3 , 1}; // Post-order of a BST

        // Construct the BST from the post-order array
        BST<Integer> bst = BST.constructFromPostOrder(postorder);
        
        // Print the tree after construction
        System.out.println("BST constructed from post-order:");
        System.out.println(bst);
    }
}