/*
Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence such that the difference between adjacent elements in the subsequence equals difference.



Example 1:

Input: arr = [1,2,3,4], difference = 1
Output: 4
Explanation: The longest arithmetic subsequence is [1,2,3,4].
Example 2:

Input: arr = [1,3,5,7], difference = 1
Output: 1
Explanation: The longest arithmetic subsequence is any single element.
Example 3:

Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
Output: 4
Explanation: The longest arithmetic subsequence is [7,5,3,1].


Constraints:

1 <= arr.length <= 10^5
-10^4 <= arr[i], difference <= 10^4
 */

class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        int[] dp = new int[20_001];
        int res = 0;
        for (int n : arr) {
            int i = n + 10_000;
            int idx = i - difference;
            int p = (idx >= 0 && idx <= 20_000) ? dp[idx] : 0;
            dp[i] = Math.max(dp[i], p + 1);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}