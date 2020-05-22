/*
Given an integer array nums, find the contiguous subarray (containing at least one number)
which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
which is more subtle.
 */

/*
for each element, if the previous sum is a positive number, it needs to add it to become a greater one.
But if the previous one is a negative number, the thing we need to do is set it as the first element of a new subset
for example:
[-2,1,-3,4,-1,2,1,-5,4]
[-2,1,-2,4, 3,5,6, 1,5]
 */

class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int x : nums){
            sum = (sum>=0)? sum+x : x;
            max = Math.max(max, sum);
        }
        return max;
    }
}