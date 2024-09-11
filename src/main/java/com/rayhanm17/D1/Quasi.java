package com.rayhanm17.D1;

public class Quasi {
    private static int max3(int a, int b, int c){
        return (a > b)?(a > c)? a: c: (b > c)? b: c;
    }
    private static int maxSumRec(int[] a, int left, int right){
        //This private helper method uses recursion to find the max subsequence sum in the subarray a[left .. right]
        if(left == right){//base case
            if(a[left] > 0)
                return a[left];
            else //a[left] is negative or zero
                return 0;
        }
        //let's break a[left .. right] into two subarrays: a[left .. center] and a[center + 1 .. right]
        int center = left + (right - left)/2;//center is the average of left and right
        //NOTE: avoid using (left + right)/2 to find the average as it may cause integer overflow!
        //There are three cases:
        //Case 1: the subsequence with maximum sum lies completely within the left half:
        int maxLeftSum = maxSumRec(a, left, center);//find solution for a[left .. center]
        //Case 2: the subsequence with maximum sum lies completely within the right half:
        int maxRightSum = maxSumRec(a, center + 1, right);//find solution for a[center+1 .. right]
        //Case 3: the subsequence with maximum sum starts in the left half and ends in the right half
        //To find Case 3's answer, let's find the max sum of subarray ending at index center!
        int maxLeftBorderSum = 0, leftBorderSum = 0;
        for(int i = center; i >= left; i--){
            leftBorderSum += a[i];
            if(leftBorderSum > maxLeftBorderSum)
                maxLeftBorderSum = leftBorderSum;
        }
        //To find Case 3's answer, let's find the max sum of subarray starting at index center+1!
        int maxRightBorderSum = 0, rightBorderSum = 0;
        for(int i = center+1; i <= right; i++){
            rightBorderSum += a[i];
            if(rightBorderSum > maxRightBorderSum)
                maxRightBorderSum = rightBorderSum;
        }
        //Case 3's answer is maxLeftBorderSum + maxRightBorderSum
        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);

    }
    public static int maxSubSequenceSum(int[] a){
      return maxSumRec(a, 0, a.length - 1);
    }
}
