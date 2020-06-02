/*
Given an array of integers that is already sorted in ascending order,
find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target,
where index1 must be less than index2.

Note:

Your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element twice.
Example:

Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 */

// the idea is that we use two pointers, one at the start and anothers at the end
// we add the sum of these two pointers. if the sum bigger than target, we move the start
// else, move the end


class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // int[] res = new int[2];
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            // we need to return the index starting from 1
            if (sum == target) return new int[]{left+1,right+1};
            if (sum > target) right--;
            else left++;
        }
        return null;
    }
}

// 100%