/*
Given an array of size n, find the majority element. The majority element is the
element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
 */

// Boyer-moore Majority vote algrithm
// the majority is bigger than the rest of other numbers togather
// https://www.youtube.com/watch?v=2s7b0zs4Vf4

class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int res = 0;

        for (int num:nums){
            // check if we need to change to another number
            if (count == 0){
                res = num;
            }
            // check if the next number is same or not
            if (num != res){
                count--;
            } else {
                count++;
            }
        }

        return res;

    }
}

class Solution_HashTable {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums){
            map.put(num, map.getOrDefault(num, 0)+1);
            // If no value is mapped with the provided key then the default value is returned.
            if (map.get(num) > nums.length / 2){
                return num;
            }
        }
        return -1;
        // otherwise java will complain-- error: missing return statement
    }
}

// Divide and Conquer
// If we know the majority element in the left and right halves of an array,
// we can determine which is the global majority element in linear time.

class Solution {
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    private int majorityElementRec(int[] nums, int lo, int hi) {
        // base case; the only element in an array of size 1 is the majority element. lo and hi are index
        if (lo == hi) {
            return nums[lo];
        }

        // recurse on left and right halves of this slice.
        int mid = (hi-lo)/2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid+1, hi);

        // if the two halves agree on the majority element, return it.
        if (left == right) {
            return left;
        }

        // otherwise, count each element and return the "winner".
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);

        return leftCount > rightCount ? left : right;
    }

    public int majorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length-1);
    }
}


// Sorting:
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }