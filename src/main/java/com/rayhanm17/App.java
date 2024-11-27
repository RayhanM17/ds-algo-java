package com.rayhanm17;

import java.util.*;

public class App {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        DisjointSet<String> disjointSet = new DisjointSet<>(); // Create a new DisjointSet to manage friendships
        
        while (true) { // Phase I: Input Friendship Information
            System.out.println("Phase I: Enter friendship info like \"Alice and Bob are now friends.\" Enter \"DONE\" to end Phase I.");
            String line = keyboard.nextLine();
            if (line.equalsIgnoreCase("DONE")) {
                break;
            }
            String[] words = line.split(" ");
            String userA = words[0], userB = words[2];
            // Use DisjointSet.union method to store the friendship between userA and userB
            disjointSet.union(userA, userB); // Union A and B as friends
        }

        while (true) { // Phase II: Respond to Friendship Queries
            System.out.println("Phase II: Ask a question like \"Are Alice and Bob friends?\". Enter \"QUIT\" to end Phase II.");
            String line = keyboard.nextLine();
            if (line.equalsIgnoreCase("QUIT")) {
                break;
            }
            String[] words = line.split(" ");
            String userA = words[1], userB = words[3];
            // Use DisjointSet.find method to check the friendship between userA and userB
            // Print "Yes" or "No" to respond
            if (disjointSet.find(userA).equals(disjointSet.find(userB))) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}