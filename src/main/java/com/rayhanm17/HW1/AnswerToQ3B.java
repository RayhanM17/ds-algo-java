package com.rayhanm17.HW1;

public class AnswerToQ3B {
    public static void main(String[] args) {
        
    }

    // Question 3B
	public static int findLastPen(int n, int k) {
        // Create the circular linked list
        Node head = new Node(1);
        Node current = head;
        for (int i = 2; i <= n; i++) {
            current.next = new Node(i);
            current = current.next;
        }
        current.next = head; // Make it circular

        SinglyLinkedList list = new SinglyLinkedList(head);

        Node temp = list.head;
        Node prev = null;
        int count = 0;

        // loop through circular linked list
        while (list.head != list.head.next) {
            count++;
            if (count % k == 0) {
                if (prev == null) {
                    list.head = list.head.next;
                } else {
                    prev.next = 
                }
            } else {
                temp = temp.next;
            }
        }

        return list.head.data;
    }
}
