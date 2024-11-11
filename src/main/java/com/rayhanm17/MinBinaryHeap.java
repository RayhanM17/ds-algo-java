package com.rayhanm17;

// Min Heap Implementation

public class MinBinaryHeap<T extends Comparable<? super T>>{
    private Comparable[] data;
    private int size = 0;

    public MinBinaryHeap(int capacity){
        data = new Comparable[capacity];
    }

    public MinBinaryHeap(Comparable[] data){
        this.data = data;
        size = data.length - 1;
        for(int i = size/2; i >= 1; i--)
            percolateDown(i);
    }

    public int size(){
        return size;
    }

    private void percolateDown(int root) { // Heapify for Min-Heap
        int leftChild = root * 2;
        int rightChild = leftChild + 1;
        int min; // min of left, right, and parent nodes

        // Comparing parent with left child
        if(leftChild <= size && data[leftChild].compareTo(data[root]) < 0)
            min = leftChild;
        else
            min = root;

        // Comparing the min with the right child
        if (rightChild <= size && data[rightChild].compareTo(data[min]) < 0)
            min = rightChild;

        if(min != root) { // if not the base case
            Comparable temp = data[root]; // swapping min with root
            data[root] = data[min];
            data[min] = temp;
            percolateDown(min);
        }
    }

    public Comparable extractMin() {
        if(size == 0)
            throw new IllegalStateException();
        
        Comparable rv = data[1];
        data[1] = data[size]; // swap root with last leaf
        data[size] = rv;
        size--;
        percolateDown(1); // heapify the root
        return rv;
    }

    public void percolateUp(int current) {
        if(current == 1)
            return;
        
        int parent = current / 2;
        if(data[parent].compareTo(data[current]) > 0) {
            Comparable temp = data[current];
            data[current] = data[parent];
            data[parent] = temp;
            percolateUp(parent);
        }
    }

    public void insert(Comparable value) {
        if(size == data.length - 1)
            throw new IllegalStateException();
        
        size++;
        data[size] = value;
        percolateUp(size);
    }

    public static MinBinaryHeap buildMinHeap(Comparable[] values){
        return new MinBinaryHeap(values);
    }

    public static void heapSort(Comparable[] values) {
        MinBinaryHeap heap = buildMinHeap(values);
        for(int i = 0; i < values.length - 1; i++)
            heap.extractMin();
    }
}