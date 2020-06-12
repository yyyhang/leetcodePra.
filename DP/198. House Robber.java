/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed, the only constraint
stopping you from robbing each of them is that adjacent houses have security
system connected and it will automatically contact the police if two adjacent
houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.



Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

0 <= nums.length <= 100
0 <= nums[i] <= 400
 */

// brute force
/*
class Solution {
    public int rob(int[] nums) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum1 += nums[i];
        }
        for (int j = 1; j < nums.length; j += 2) {
            sum2 += nums[j];
        }
        return sum1 > sum2 ? sum1 : sum2;
    }
}

// but the thing is that you can rob more next next, for instance,  [2,1,1,2], the answer is 4
 */

// DP
// we need to add it to the i-2 or i-3, bcz they are the houses we can grab before i
class Solution {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length+3];
        for (int i = 0; i < nums.length; i++) {
            dp[i+3] = Math.max(dp[i],dp[i+1]) + nums[i];
        }
        return Math.max(dp[dp.length-1], dp [dp.length-2]);
    }
}

//  0 ms, faster than 100.00%