package com.rayhanm17;

public class One {
    
    public static double findMedian(int[] list1, int[] list2) {
        if (list1.length != list2.length || list1.length == 0) {
            throw new IllegalArgumentException("Arrays must be non-empty and of the same length.");
        }
    
        int n = list1.length;
    
        int left = 0, right = n;
        while (left <= right) {
            int partition1 = (left + right) / 2;
            int partition2 = n - partition1;
    
            //handle edge cases
            int maxLeft1 = (partition1 == 0) ? Integer.MIN_VALUE : list1[partition1 - 1];
            int minRight1 = (partition1 == n) ? Integer.MAX_VALUE : list1[partition1];
    
            int maxLeft2 = (partition2 == 0) ? Integer.MIN_VALUE : list2[partition2 - 1];
            int minRight2 = (partition2 == n) ? Integer.MAX_VALUE : list2[partition2];
    
            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // Found the correct partition.
                return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
            } else if (maxLeft1 > minRight2) {
                // Move partition1 to the left.
                right = partition1 - 1;
            } else {
                // Move partition1 to the right.
                left = partition1 + 1;
            }
        }
    
        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }
}
