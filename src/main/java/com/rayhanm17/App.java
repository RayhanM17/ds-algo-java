package com.rayhanm17;

import java.util.Random;
import com.rayhanm17.D1.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args){
        int[] a = new int[5000];
        Random random = new Random();
        for(int i = 0; i < 5000;i++)
            a[i] = random.nextInt()%1001;//generates 5k numbers from -1000 to 1000
        long startTime = System.currentTimeMillis();
        System.out.println(Cubic.maxSubSequenceSum(a));
        System.out.println("Cubic took " + (System.currentTimeMillis() - startTime) + " miliseconds!");
        startTime = System.currentTimeMillis();
        System.out.println(Quad.maxSubSequenceSum(a));
        System.out.println("Quadratic took " + (System.currentTimeMillis() - startTime) + " miliseconds!");
        startTime = System.currentTimeMillis();
        System.out.println(Quasi.maxSubSequenceSum(a));
        System.out.println("Quasilinear took " + (System.currentTimeMillis() - startTime) + " miliseconds!");
    }
}