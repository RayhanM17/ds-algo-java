package com.rayhanm17.D2;

public class myQueue {
    private SinglyLinkedList queue = null;

    public myQueue() {
        queue = new SinglyLinkedList(null);
    }

    public void enqueue(Object data) {
        if (queue.head == null) {
            queue.head = new Node(data, null);
            return;
        }

        Node current = queue.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(data, null);
    }

    public Object dequeue() {
        if (queue.head == null) {
            System.out.println("Queue is empty");
            return null;
        }

        Object data = queue.head.data;
        queue.head = queue.head.next;
        return data;
    }

    public Object peek() {
        if (queue.head == null) {
            System.out.println("Queue is empty");
            return null;
        }

        return queue.head.data;
    }

    public boolean isEmpty() {
        return queue.head == null;
    }
}