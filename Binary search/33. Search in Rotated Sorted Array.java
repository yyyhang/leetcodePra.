/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */

class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        // when they are near each other
        while (start + 1 < end) {
            int mid = start + ( end - start) / 2;
            if (nums[mid] == target) return mid;
            // mid and start at the same area
            if (nums[start] < nums[mid]) {
                if (nums[start] <= target && nums[mid] >= target) end = mid;
                else start = mid;
                // mid and end at rthe same area
            } else if (nums[mid] < nums[end]) {
                if (nums[end] >= target && nums[mid] <= target) start = mid;
                else end = mid;
            }
        }

        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
}

// 100%

// https://www.youtube.com/watch?v=lWEIIFFflQY