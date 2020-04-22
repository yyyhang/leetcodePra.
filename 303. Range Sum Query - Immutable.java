/*
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
 */

class NumArray {

    int[] dp;
    // put here then another method can use it
    public NumArray(int[] nums) {
        int n = nums.length;
        dp = new int[n+1];
        // we need an extra index to record the 0 in the prefix sum array
        dp[0] = 0;
        for (int i=1; i<=n;i++){
            dp[i] = dp[i-1]+nums[i-1];
            // add all nums togather, an then record it
        }
    }

    public int sumRange(int i, int j) {
        return dp[j+1]-dp[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */

//https://www.youtube.com/watch?v=CjPMfq3ULZg