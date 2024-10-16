package com.rayhanm17;

public class App {
    public static void main(String[] args) {

        //root stores "Ana"                                            Ana
        //                                                          /       \
        //root's left child stores "Alice"                      Alice       Bob
        //                                                     /    \      /   \
        //root's right child stores "Bob"                    null   Amy   Ben  null
        //Alice's right child is "Amy" (Alice has not left child)
        //Bob's left child is "Ben" (Bob has no right child)

        BinaryNode<String> Level1Right = new BinaryNode<>("Ben");
        BinaryNode<String> Level1Left = new BinaryNode<>("Amy");

        BinaryNode<String> leftSubtree = new BinaryNode<>("Alice", null, Level1Left);

        BinaryNode<String> rightSubTree = new BinaryNode<>("Bob", Level1Right, null);

        BinaryNode<String> root = new BinaryNode<>("Ana", Level1Left, Level1Right);

        System.out.println("Tree:"  + root);

        System.out.println("Preorder traversal");
        root.printPreOrder(); System.out.println();

        System.out.println("Postorder traversal: ");
        root.printPostOrder(); System.out.println();

        // Generate random nodes
        BinaryNode<Double> midRightLeaf1 = generateRandomNode();
        BinaryNode<Double> midLeftLeaf1 = generateRandomNode();

        BinaryNode<Double> leftSubtree1 = new BinaryNode<>(
                generateRandomValue(), null, midLeftLeaf1);
        BinaryNode<Double> rightSubTree1 = new BinaryNode<>(
                generateRandomValue(), midRightLeaf1, null);

        // Ensure the root node is not null
        Double rootValue = generateNonNullRandomValue();
        BinaryNode<Double> root1 = new BinaryNode<>(
                rootValue, leftSubtree1, rightSubTree1
        );

        // Calculate the sum of all nodes
        double totalSum = sum(root1);
        System.out.println("\nDoubles Tree:\n\t" + root1);
        System.out.println("\nSum of nodes: " + totalSum);
    }

    // Method to generate a random double between 1.0 and 10.0 or null
    private static Double generateRandomValue() {
        return Math.random() < 0.5 ? 1.0 + (9.0 * Math.random()) : null;
    }

    // Method to generate a random double between 1.0 and 10.0 (non-null)
    private static Double generateNonNullRandomValue() {
        return 1.0 + (9.0 * Math.random());
    }

    // Method to generate a random BinaryNode with a random value
    private static BinaryNode<Double> generateRandomNode() {
        return new BinaryNode<>(generateRandomValue());
    }

    // Recursive method to find the sum of elements stored in a binary tree
    public static double sum(BinaryNode<Double> root) {
        if (root == null || root.element == null) {
            return 0;
        }
        return root.element + sum(root.left) + sum(root.right);
    }
}