/*
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.

https://leetcode.com/problems/trapping-rain-water/

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case,
6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */

/*
one way is to record the amount of water each index can hold.
this can be done by min (the heightest right one, heightest left one) - its value
but this way needs two arrays to record these two.
therefore, space: O(n)
 */

/*
another way is to use two pointers. one pints to the most left, another one points to the most right.
 */

class Solution {
    public int trap(int[] height) {
        int max = 0;
        int leftmax = 0;
        int rightmax = 0;
        int i = 0;
        int j = height.length - 1;
        while (i<j){
            leftmax = Math.max(leftmax,height[i]);
            rightmax = Math.max(rightmax,height[j]);
            if (leftmax < rightmax){
                max += leftmax - height[i];
                //after update the highest left and right one. in this case, the index one just depend on this side
                //even between the index and right tallest one, there are some taller or shorter one, they cannot
                // affect the units this one can hold
                i++;
            } else {
                max += rightmax - height[j];
                // same. just right side decides the water it can hold
                j--;
            }
        }
        return max;
    }
}

// how to use dp?
