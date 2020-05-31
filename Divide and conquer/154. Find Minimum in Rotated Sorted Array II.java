/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?
Accepted
 */

// it looks samilar to tree: 81
// we can draw a pic to help us to think about

// but for this one, we cannot determine some situation when start = end:
// e.g. 2,2,2,2,2,2 vs. 2,2,2,3,1,2.
// we have no idea which one is sorted if we just compare start and end


class Solution {
    public int findMin(int[] nums) {
        return minFind(nums, 0, nums.length - 1);
    }

    private int minFind(int[] nums, int lo, int hi){
        // if we only have 1 or 2 elements
        if (lo+1 >= hi) return Math.min(nums[lo], nums[hi]);
        // sorted. we return the minimum element.
        // to tell it is sorted or not, we need nums[lo] less than nums[hi], they cannot be equal.
        if (nums[lo] < nums[hi]) return nums[lo];
        // unsorted
        int mid = lo + (hi-lo)/2;
        // avoid overflow

        return Math.min(minFind(nums, lo, mid-1), minFind(nums, mid, hi));
    }
}

// for this question, the important thing is we need to analyse the big O.
// the code same with the 153, but there big O is different

//0ms. 100%

//Average: O(logn)
//Worst: O(n)