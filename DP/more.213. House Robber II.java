/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
That means the first house is the neighbor of the last one. Meanwhile,
adjacent houses have security system connected and it will automatically contact the police
if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
 */

// 5,1,2,3,4

/*
   //index   0   1   2   3   4
int[] arr = {10, 20, 30, 40, 50};
Arrays.copyOfRange(arr, 0, 2);          // returns {10, 20}
Arrays.copyOfRange(arr, 1, 4);          // returns {20, 30, 40}
Arrays.copyOfRange(arr, 2, arr.length); // returns {30, 40, 50} (length = 5)
 */

// the idea come into my mind was like:  calculate one sum with no i==0; another for no last idx

class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int sum1 = ro(Arrays.copyOfRange(nums, 1, nums.length));
        int sum2 = ro(Arrays.copyOfRange(nums, 0, nums.length-1));
        return Math.max(sum1,sum2);
    }

    public int ro(int[] nums) {
        int[] dp = new int[nums.length+3];
        for (int i = 0; i < nums.length; i++) {
            dp[i+3] = Math.max(dp[i],dp[i+1]) + nums[i];
        }
        return Math.max(dp[dp.length-1], dp [dp.length-2]);
    }
}

// 0 ms, faster than 100.00%