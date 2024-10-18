package com.rayhanm17;
import java.util.*;

public class BST<E extends Comparable<? super E>> {
    private BinaryNode<E> root;
    private int size;

    public boolean contains(E value) {
        return contains(root, value);
    }
    //Insert opertion of Set ADT
    public void insert(E value) {
        root = insert(root, value);
        size++;
    }
    //Remove operation of Set ADT
    public void remove(E value){
        root = remove(root, value);
        size--;
    }
    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return root == null;
    }
    public void clear(){
        root = null;
        size = 0;
    }
    public E findMin(){
        return findMin(root);
    }
    public E findMax(){
        return findMax(root);
    }
    public int findHeight(){
        return root.height();
    }
    public String toString(){
        if(root == null)
            return "set {} stored in an empty tree.\n";
        String elements = root.inOrder().toString();
        return "set {" + elements.substring(1,elements.length()-1) + "} stored in tree \n" + root;
    }
    private E findMin(BinaryNode<E> node){
        if(node == null)
            return null;
        if(node.left == null)
            return node.element;
        return findMin(node.left);
    }
    private E findMax(BinaryNode<E> node){
        if(node == null)
            return null;
        if(node.right == null)
            return node.element;
        return findMax(node.right);
    }
    private boolean contains(BinaryNode<E> node, E value) {
        if (node == null)
            return false;
        int comparisonResult = value.compareTo(node.element);
        if(comparisonResult < 0)
            return contains(node.left, value);
        if(comparisonResult > 0)
            return contains(node.right, value);
        return true;
    }

    private BinaryNode<E> insert(BinaryNode<E> node, E value) {
        if (node == null)//base case: empty tree
            return new BinaryNode<>(value);
        if (value.compareTo(node.element) < 0)
            node.left = insert(node.left, value);//insert recursively to left-subtree
        else if (value.compareTo(node.element) > 0)
            node.right = insert(node.right, value);//insert recursively to right-subtree
        else {//duplicate value cannot be inserted in a BST implementing the Set ADT
            size--;//insertion failed!
            //System.out.print("BST.insert: Warning: " + value + " is already stored in the tree \n" + node);
        }
        return node;
    }
    private BinaryNode<E> remove(BinaryNode<E> node, E value) {
        if (node == null) {//base case: empty tree
            System.out.println("BST.remove: Warning: " + value + " doesn't exist in the tree.");
            size++;//removal failed!
            return node;
        }
        if (value.compareTo(node.element) < 0)
            node.left = remove(node.left, value);
        else if (value.compareTo(node.element) > 0)
            node.right = remove(node.right, value);
        else {//we have found the node that needs to be removed!
            if (node.left != null && node.right != null) {//remove a node with two children
                node.element = findMin(node.right);//replace it by the leftmost node at right subtree
                node.right = remove(node.right, node.element);//then, remove the leftmost node in the right subtree
            /*alternative:
            node.element = findMax(node.left);//replace it by the rightmost node at left subtree
            node.left = remove(node.left, node.element);//then, remove the rightmost node in the left subtree
             */
            } else if (node.left != null)//remove a node with a left child only
                return node.left;
            else if (node.right != null)//remove a node with a right child only
                return node.right;
            else//remove a node with no child!
                return null;
        }
        return node;
    }

    public static BST<Integer> constructFromPostOrder(int[] postorder) {
        BST<Integer> bst = new BST<>();
        if (postorder.length == 0) return bst; // Check for empty array
        constructFromPostOrderHelper(bst, postorder, 0, postorder.length - 1);
        return bst;
    }
    
    private static void constructFromPostOrderHelper(BST<Integer> bst, int[] postorder, int start, int end) {
        if (start > end) {
            return; // Base case: no elements to process
        }
    
        // The last element in the current range is the root
        int rootValue = postorder[end];
        bst.insert(rootValue); // Insert the root value into the BST
    
        // Find the index where the left subtree ends
        int leftEnd = start;
        while (leftEnd < end && postorder[leftEnd] < rootValue) {
            leftEnd++;
        }
    
        // Now, leftEnd is the index of the first element greater than rootValue
        // Elements from start to leftEnd - 1 are part of the left subtree
        // Elements from leftEnd to end - 1 are part of the right subtree
        // Recursively construct the left and right subtrees
        constructFromPostOrderHelper(bst, postorder, start, leftEnd - 1); // Left subtree
        constructFromPostOrderHelper(bst, postorder, leftEnd, end - 1); // Right subtree
    }    

    private class BinaryNode<E> {
        public E element;//data
        public BinaryNode<E> left;//left child
        public BinaryNode<E> right;//right child

        //constructor for leaves
        public BinaryNode(E element) {
            this(element, null, null);
        }

        //constructor for internal nodes
        public BinaryNode(E element, BinaryNode<E> left, BinaryNode<E> right) {
            this.left = left;
            this.right = right;
            this.element = element;
        }

        public int height() {
            if (left == null && right == null)
                return 0;
            if (left == null)
                return 1 + right.height();
            if (right == null)
                return 1 + left.height();
            return 1 + Math.max(left.height(), right.height());
        }

        public int size() {
            int size = 1;//counting root
            if (left != null)//counting left subtree nodes
                size += left.size();
            if (right != null)//counting right subtree nodes
                size += right.size();
            return size;
        }

        public void printPreOrder() {
            System.out.print(element + " ");
            if (left != null)
                left.printPreOrder();
            if (right != null)
                right.printPreOrder();
        }

        public void printPostOrder() {
            if (left != null)
                left.printPostOrder();
            if (right != null)
                right.printPostOrder();
            System.out.print(element + " ");
        }

        public void printInOrder() {
            if (left != null)
                left.printInOrder();
            System.out.print(element + " ");
            if (right != null)
                right.printInOrder();
        }
        public ArrayList<E> inOrder(){
            ArrayList<E> list = new ArrayList<>();
            Stack<Object> stack = new Stack<>();
            stack.push(this);
            while(!stack.empty()){
                Object cur = stack.pop();
                if(cur instanceof BinaryNode) {
                    BinaryNode<E> node = (BinaryNode) cur;
                    if (node.right != null)
                        stack.push(node.right);
                    stack.push(node.element);
                    if (node.left != null)
                        stack.push(node.left);
                }else
                    list.add((E)cur);
            }
            return list;
        }

        public void printBFS() {
            Queue<BinaryNode> q = new LinkedList<>();
            q.add(this);
            while (!q.isEmpty()) {
                BinaryNode<E> cur = q.remove();
                System.out.print(cur.element + " ");
                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
            }
        }

        public void printDFS() {
            Stack<BinaryNode> stack = new Stack<>();
            stack.add(this);
            while (!stack.empty()) {
                BinaryNode<E> cur = stack.pop();
                System.out.print(cur.element + " ");
                if (cur.right != null)
                    stack.push(cur.right);
                if (cur.left != null)
                    stack.push(cur.left);
            }
        }

        @Override
        public String toString() {
            if (left == null && right == null && element == null)
                return "";
            Queue<BinaryNode> list = new LinkedList<>();
            String result = "";
            list.add(this);
            list.add(null);
            int level = (int) Math.pow(2, height());
            BinaryNode dummy = new BinaryNode(null);
            while (!list.isEmpty()) {
                boolean allDummies = true;
                for (BinaryNode<E> b : list)
                    if (b != dummy && b != null) {
                        allDummies = false;
                        break;
                    }
                BinaryNode<E> cur = list.remove();
                if (cur == null || allDummies)
                    break;
                for (int i = 0; i < level - 1; i++)
                    result += '\t';
                if (cur != dummy)
                    result += cur.element;
                for (int i = 0; i < level + 1; i++)
                    result += '\t';
                if (cur.left != null)
                    list.add(cur.left);
                else
                    list.add(dummy);
                if (cur.right != null)
                    list.add(cur.right);
                else
                    list.add(dummy);
                if (list.peek() == null) {
                    for (int i = 0; i < height(); i++)
                        result += '\n';
                    list.remove();
                    list.add(null);
                    level /= 2;
                }

            }
            return result + "\n";
        }
    }
}