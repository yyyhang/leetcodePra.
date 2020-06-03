/*
Given an array nums of n integers and an integer target,
find three integers in nums such that the sum is closest to target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.



Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).


Constraints:

3 <= nums.length <= 10^3
-10^3 <= nums[i] <= 10^3
-10^4 <= target <= 10^4
 */

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        int d = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; ++i) {
            int s = i + 1;
            int t = n - 1;
            while (s < t) {
                int sum = nums[i] + nums[s] + nums[t];
                if (sum == target) return target;
                int diff = Math.abs(sum - target);
                if (diff < d) {
                    d = diff;
                    ans = sum;
                }
                if (sum > target) --t;
                else ++s;
            }
        }
        return ans;
    }
}

// 3ms, 98.09%