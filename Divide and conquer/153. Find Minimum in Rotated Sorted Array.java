/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2]
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
 */

// Divide and conquer
// https://zxi.mytechroad.com/blog/leetcode/leetcode-153-find-minimum-in-rotated-sorted-array/

class Solution {
    public int findMin(int[] nums) {
        return min(nums, 0, nums.length-1);
    }

    private minFind(int[] nums, int lo, int hi){
        // if we only have 1 or 2 elements
        if (lo+1 >= hi) return min(nums[lo], nums[hi]);

        // sorted. we return the minimum element
        if (nums[lo] < nums[hi]) return nums[lo];

        // unsorted
        int mid = lo + (hi-lo)/2;
        // avoid overflow

        return min(minFind(nums, lo, mid-1), minFind(nums, mid, hi));
    }
}

// brute force
class Solution_1 {
    public int findMin(int[] nums) {
        int min = nums[0];
        for (int num : nums){
            if (min > num) min = num;
        }
        return min;
    }
}

// Binary Search
// you wanna look at the unsorted side and find the minimum element, and leave the sorted side aside
// https://www.youtube.com/watch?v=IzHR_U8Ly6c
// maybe in the future, if search some value in an ordered array, bs is an option, as it can find the max/min value
// similar to 34
class Solution_2 {
    public int findMin(int[] nums) {
        int low = 0, mid = 0, high = nums.length-1;
        while(low<high){
            mid= low+(high-low)/2;
            if(nums[mid]>nums[high])
                low=mid+1;
            else
                high=mid;
        }
        return nums[low];
    }
}
// if hi next to lo, nums[hi]<nums[lo], then the lo will increase 1 to equal hi