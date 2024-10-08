package com.rayhanm17.HW1;

import java.util.Iterator;
import java.util.LinkedList;

public class SinglyLinkedList {
	public Node head;

	public SinglyLinkedList(Node head) {
		this.head = head;
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
