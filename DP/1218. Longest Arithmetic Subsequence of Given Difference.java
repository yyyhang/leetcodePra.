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

// DP
// assume we have [... , x-2d, x-d, x, ...]
// for x, if the x-d exists, the max++. otherwise, the x is the first element of a new arithmetic sequence
// if a = x-d, x = a + d. we can use x-d to check if x-d exists

class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer,Integer> map = new HashMap<>();
        int ans = 0;
        for (int x : arr){
            // if the previous one exsits, we can just add one to the longest
            map.put(x, map.getOrDefault(x-difference, 0)+1);
            ans = Math.max(ans, map.get(x));
        }
        return ans;
    }
}
//  https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-1218-longest-arithmetic-subsequence-of-given-difference/

// this one is much faster, but i don;t understand

class Solution_2 {
    public int longestSubsequence(int[] arr, int difference) {
        int[] dp = new int[20_001];
        int res = 0;
        for (int n : arr) {
            //current value plus offset
            int i = n + 10_000;
            //value - diff - possible previous value in sequence
            int idx = i - difference;
            //length of sequence ended with prev value
            int p = (idx >= 0 && idx <= 20_000) ? dp[idx] : 0;
            //check if newly calculated value is greater than we have met before for this number
            dp[i] = Math.max(dp[i], p + 1);
            //save global longest sequence length
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}

// https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/discuss/399618/Java-dp-O(1)-space-explained-short-and-clean-7ms