package com.rayhanm17.D2;

import java.util.LinkedList;
import java.util.Iterator;

public class SinglyLinkedList {
	public Node head;
	public SinglyLinkedList(Node head) {
		this.head = head;
	}

	public static int length(SinglyLinkedList list) {
		if (list.head == null) return 0;

		Node temp = list.head;
		int length = 1;

		while(temp.next != null) {
			temp = temp.next;
			length++;
		}

		return length;
	}

	public static void removeOddIndices(SinglyLinkedList list){
		Node temp = list.head;
		while(temp != null && temp.next != null){
		  System.out.println("Current temp is equal to "+ temp);
		  System.out.println("Deleting " + temp.next);
		  temp.next = temp.next.next;//removes temp.next
		  temp = temp.next;
		}
	}

	public static void removeEvenIndices(SinglyLinkedList list){
		if (list.head == null) return;

		System.out.println("Head is equal to "+ list.head);
		System.out.println("Deleting " + list.head);
		list.head = list.head.next;

		Node temp = list.head;
		while(temp != null && temp.next != null){
			System.out.println("Current temp is equal to "+ temp);
			System.out.println("Deleting " + temp.next);
			temp.next = temp.next.next;//removes temp.next
			temp = temp.next;
		}
	}

	// HW1 Question 3a
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

	// HW1 Question 3b
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
        int count = 0;

        while (list.head != list.head.next) {
            count++;
            if (count % k == 0) {
                Node toRemove = temp;
                temp = temp.next;
                removeNode(list, toRemove);
            } else {
                temp = temp.next;
            }
        }

        return list.head.data;
    }	

	/*
	 * This method goes from left to right reversing the next item
	 * There is a gap between the curr item that was reversed and the next item
	 * this method set the cur to the next and iterates soothly reversing the list
	 * at the end it sets the head to the tail
	 */
	public static void whatDoesItDo(SinglyLinkedList list){
		Node prev = list.head; // init prev
		if(prev == null) //checks if empty list
		  return;
		Node curr = prev.next //init curr
		Node next; // init next
		prev.next = null; //head becomes new tail
		while(curr != null){
		  next = curr.next; //save curr.next so that you don't lose the rest of nodes!
		  curr.next = prev; //reverse the arrow direction connecting curr and prev nodes!
		  prev = curr; //update prev
		  curr = next; //update curr
		}
		list.head = prev;
	}
    
	@Override
	public String toString() {
		if(head == null)//base case
			return "[]";
		String result = "[" + head;
		if(head.next != null)
			result += ", ";
		SinglyLinkedList rest = new SinglyLinkedList(head.next);
		
		return result + rest.toString().substring(1);
	}
}