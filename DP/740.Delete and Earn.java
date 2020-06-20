/*
Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points.
After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by applying such operations.

Example 1:

Input: nums = [3, 4, 2]
Output: 6
Explanation:
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.


Example 2:

Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation:
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.


Note:

The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].
 */

/*
sort it first
then for each one we just care about its best value
it can only from the previous 2 distinct elements before it but not next to it.
wwhy not take into 3 elements?
the third one have already in the previous 2 distinct number.
i.e. one of the previous 2 distinct numbers come from the third one or at least compared it but take the max
[1,1,2,2,2,3,4,5] 5+2+2+2

for this kind of question
1. for each one need max value
2. consider where is this one can come from
3. where is no need to consider
 */

//similar to rubber house

class Solution {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];    // otherwise it may get -2147483648
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] == nums[i]) dp[i] = dp[i-1] + nums[i];
            // after finish, check the logic. don't forget else here
            else{
                int x = i - 1;
                while (x >= 0) {
                    if (nums[x] == nums[i] - 1) x--;
                    else break;
                }
                if (x < 0) dp[i] = nums[i];
                else if (x == 0) dp[i] = nums[i] + dp[x];
                else {
                    int y = x - 1;
                    while (y >= 0) {
                        // do not wirte down wrong varable
                        if (nums[x] == nums[y]) y--;
                        else break;
                    }
                    if (y < 0) dp[i] = nums[i] + dp[x];
                    else dp[i] = nums[i] + Math.max(dp[x],dp[y]);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

//  3 ms, faster than 54.34%
// O(nlogn)

// https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-790-domino-and-tromino-tiling/


// or we convert it into another array, from number 1 to number max. if this number is not in the nums,
// then we set it as 0. otherwise, it should be the number value * apeear time