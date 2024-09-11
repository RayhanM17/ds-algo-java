package com.rayhanm17.D1;

public class Quad {
    public static int maxSubSequenceSum(int[] a){
        int maxSum = 0;
        int solutionStartIndex = -1, solutionEndIndex = -1;
        //Find all sub-arrays starting at index start and ending at index end
        for(int start = 0; start < a.length;start++){
            int thisSum = 0;
            //the end index cannot be smaller than start index
            for(int end = start; end < a.length;end++){
                //find the sum of elements in subarray a[start .. end]
                thisSum += a[end];
                if(thisSum > maxSum) {
                    maxSum = thisSum;
                    //storing the solution
                    solutionStartIndex = start;
                    solutionEndIndex = end;
                }
            }
        }
        System.out.printf("Subsequence with max sum is from index %d to %d.\n", solutionStartIndex, solutionEndIndex);
        return maxSum;
    }
}
