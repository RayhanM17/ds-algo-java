package com.rayhanm17.HW1;

public class AnswerToQ3A {
    
    public static void main(String[] args) {
        
    }

    // Question 3A
	public static void removeValuesLargerThanMax(SinglyLinkedList list, int max) {
		if (list.head == null) {
			return;
		}
	
		while (list.head != null && list.head.data > max) {
			list.head = list.head.next;
		}
	
		if (list.head == null) {
			return;
		}
	
		Node current = list.head;
		while (current != null && current.next != null) {
			if (current.next.data > max) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
	}
    
}
