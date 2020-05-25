/*
Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 */


// solution: search for twice, one for start range, for for end range
// in this case, these 2 pointers will stop at (they will end up with adjoining each other):
// start pointer at the start of target number, or
// start pointer will at the index of the target - 1

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        if (nums == null || nums.length == 0) return res;
        int start = 0, end = nums.length - 1;
        int st_range = -1, nd_range = -1;
        // one line just one int

        while (start +1 < end){
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) end = mid;
            // this is to make sure the end will move to the start position of the target, while
            // start will only stay at the index(target) or index(target)-1; if still feel confused, try to draw pic
            else start = mid;
        }
        if (nums[start] == target) st_range = start;
        else if (nums[end] == target) st_range = end;
        // Nb: you need to always make sure just give the value to one pointer
        // if not in the array
        if (st_range == -1) return res;

        start = 0; end = nums.length-1;
        // if it not init sentence, you need ; rather than ,
        // do not forget to init them
        while (start +1 < end) {
            int mid = start + (end - start) / 2;
            // the varable will only live in the loop, hence you need to declare again
            if (nums[mid] <= target) start = mid;
            else end = mid;
            // same as :
            // if (nums[mid] > target) end = mid;
            // else start = mid;
        }

        if (nums[end] == target) nd_range = end;
        // NB: you need to put the end at the first, in case some input do not neet to go to the loop, e.g. [2,2]
        // otherwise, the start range will equal to end range
        else if (nums[start] == target) nd_range = start;
        //For "illegal start of expression" errors, try looking at the lines
        // preceding the error for a missing ')' or '}' or missing semicolon.

        // add the result to the list
        res[0] = st_range;
        res[1] = nd_range;
        return res;
    }

}


class Solution_from_lc {
    // returns leftmost (or rightmost) index at which `target` should be
    // inserted in sorted array `nums` via binary search.
    private int extremeInsertionIndex(int[] nums, int target, boolean left) {
        int lo = 0;
        int hi = nums.length;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > target || (left && target == nums[mid])) {
                hi = mid;
            }
            else {
                lo = mid+1;
            }
        }

        return lo;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIdx = extremeInsertionIndex(nums, target, true);

        // assert that `leftIdx` is within the array bounds and that `target`
        // is actually in `nums`.
        if (leftIdx == nums.length || nums[leftIdx] != target) {
            return targetRange;
        }

        targetRange[0] = leftIdx;
        targetRange[1] = extremeInsertionIndex(nums, target, false)-1;

        return targetRange;
    }

}

// but this one is funny, if you input [2,1] 1, the output will be [-1,-1]


