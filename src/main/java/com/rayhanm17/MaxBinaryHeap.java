package com.rayhanm17;


// Max Heap Implemintation

public class MaxBinaryHeap<T extends Comparable<? super T>>{
    private Comparable[] data;
    private int size = 0;
    public MaxBinaryHeap(int capacity){
        data = new Comparable[capacity];
    }
    public MaxBinaryHeap(Comparable[] data){
        this.data = data;
        size = data.length - 1;
        for(int i = size/2; i >= 1;i--)
            percolateDown(i);
    }
    public int size(){
        return size;
    }
    private void percolateDown(int root){//heapify
        int leftChild = root * 2;
        int rightChild = leftChild + 1;
        int max; //max of left,, right, and parent nodes.
        //comparing parent with left child
        if(leftChild <= size && data[leftChild].compareTo(data[root]) > 0)
            max = leftChild;
        else
            max = root;
        //comparing the max with the right child
        if (rightChild <= size && data[rightChild].compareTo(data[max]) > 0)
            max = rightChild;
        if(max != root){ //if not the base case!
            Comparable temp = data[root];//swapping max w/ root
            data[root] = data[max];
            data[max] = temp;
            percolateDown(max);
        }
    }
    public Comparable extractMax(){
        if(size == 0)
            throw new IllegalStateException();
        Comparable rv = data[1];
        data[1] = data[size];//swap root w/ last leaf
        data[size] = rv;
        size--;
        percolateDown(1);//heapify the root
        return rv;
    }

    public void percolateUp(int current) {
        if(current == 1)
            return;
        int parent = current / 2;
        if(data[parent].compareTo(data[current]) < 0) {
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

    public static MaxBinaryHeap buildMaxHeap(Comparable[] values){
        return new MaxBinaryHeap(values);
    }

    public static void heapSort(Comparable[] values){
        MaxBinaryHeap heap = buildMaxHeap(values);
        for(int i = 0; i < values.length - 1;i++)
            heap.extractMax();
    }
}