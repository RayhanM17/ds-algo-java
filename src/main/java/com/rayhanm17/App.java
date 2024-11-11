package com.rayhanm17;

public class App {
    public static void main(String[] args) {
        Comparable[] array = new Comparable[]{-1, 5, 6, 2, 3, 1, 8, 4, 0};
        MinBinaryHeap.heapSort(array);
        for(int i = 1; i < array.length;i++)
            System.out.println(array[i]);
    }
}