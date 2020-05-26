/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example:

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */

// for this question, we use 2 pointers, oen fpr the start index, another for sum
// if sum bigger than the target, we move the idx
// if the sum less than the target, move the sum pointer


class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        int left = 0;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                res = Math.min(res, i - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}

// 1ms, 99.89%

// compare my solution (not work):

/*
public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 0;
        int fast = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        boolean flag = false;
        while (fast < nums.length && slow <= fast){
            if (sum < s) {
                cnt++;
                sum += nums[fast];
                fast++;
            }
            else {
                min = Math.min(cnt, min);
                cnt--;
                sum -= nums[slow];
                slow++;
                flag = true;
            }
        }
        return flag? min : 0;
    }
 */