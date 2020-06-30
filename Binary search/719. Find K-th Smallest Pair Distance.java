/*
Given an integer array, return the k-th smallest distance among all the pairs.
The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
Input:
nums = [1,3,1]
k = 1
Output: 0
Explanation:
Here are all the pairs:
(1,3) -> 2
(1,1) -> 0
(3,1) -> 2
Then the 1st smallest distance pair is (1,1), and its distance is 0.
Note:
2 <= len(nums) <= 10000.
0 <= nums[i] < 1000000.
1 <= k <= len(nums) * (len(nums) - 1) / 2.
 */

/*
what i think is we can sort this array at first -> nlogn
then we caculate a new array with each adjoint element absolute difference -> n
then we sort this array again -> nlogn
and then we cnt the kth -> n
 */

class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int[] diff = new int[nums.length - 1];
        int j = 0;
        for (int i = 1; i <= nums.length-1; i++) {
            diff[j++] = nums[i]  - nums[i-1];
        }
        Arrays.sort(diff);
        return diff[k-1];
    }
}

// but this is not right, because we also need to calculate the differences of values witch are not near each other
// how to get all pairs??
// we can use O(n^2) -> 2 loops to compute all pairs
// then can use bucket sort to get the answer

//++++++++++++++++++++//

//we can use binary search to find the kth number (in terms of kth number, we can use the binary search to find it)

class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int size = nums.length;
        int left = 0;
        // the largest difference
        int right = nums[size-1] - nums[0];
        // binary search
        while ( left < right) {
            int mid = left + (right - left) / 2;
            if (findPairs(nums, mid) < k) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    // why this can compute the number of pairs??
    private int findPairs(int[] nums, int target) {
        int size = nums.length;
        int count = 0;
        int end = 0;
        for (int i = 0; i < size; i++) {
            while (end + 1 < size && nums[end + 1] - nums[i] <= target) end++;
            count += end - i;
        }
        return count;
    }
}

// 4 ms, faster than 79.73%

