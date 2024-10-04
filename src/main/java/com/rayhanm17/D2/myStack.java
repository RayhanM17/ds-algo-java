package com.rayhanm17.D2;

public class myStack {
    private SinglyLinkedList stack = null;

    public myStack() {
        stack = new SinglyLinkedList(null);
    }

    public void push(Object data) {
        if(stack.head == null) {
            stack.head = new Node(data, null);
            return;
        }

        Node item = new Node(data, stack.head);
        stack.head = item;
    }

    public Object pop() {
        if (stack.head == null) {
            System.out.println("Stack is empty");
            return null;
        }
    
        Object data = stack.head.data;
        stack.head = stack.head.next;
        return data;
    }

    public Object peek() {
        if (stack.head == null) {
            System.out.println("Stack is empty");
            return null;
        }
    
        return stack.head.data;
    }

    public boolean isEmpty() {
        return stack.head == null;
    }
}
