/*
Given an array nums of n integers, are there elements a, b, c
in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */

// we can sort it at first, nlogn
// we use a base pointer to point a value
// and use another two pointers (atart and end) to find the sum
// note, base pointer and two pointers need to point at different value. then we can have no duplication
// left point at base+1

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        Arrays.sort(nums);
        // base pointer. it still needs to remain 2 index for 2 pointers
        for (int i = 0; i < nums.length - 2; i++) {
            // we sorted this array. we only need the sum equal to 0.
            if (nums[i] > 0) break;
                // skip the same value
            else if (i > 0 && nums[i] == nums[i - 1]) continue;
            // two pointers
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                if (nums[i] + nums[low] + nums[high] < 0) {
                    low++;
                } else if (nums[i] + nums[low] + nums[high] > 0) {
                    high--;
                } else {
                    // we add it to the result
                    result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    // continue. bcz there may be another sum betw them
                    high--;
                    low++;
                    while (low < high && nums[low] == nums[low - 1]) low++;
                    while (low < high && nums[high] == nums[high + 1]) high--;
                }
            }
        }
        return result;
    }
}

// 19 ms, faster than 76.78%


class Solution_2 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int negSize = 0;
        int posSize = 0;
        int zeroSize = 0;
        for (int v : nums) {
            if (v < minValue) minValue = v;
            if (v > maxValue) maxValue = v;
            if (v > 0) posSize++;
            else if (v < 0) negSize++;
            else zeroSize++;
        }
        if (zeroSize >= 3) res.add(Arrays.asList(0, 0, 0));
        if (negSize == 0 || posSize == 0) return res;
        if (minValue * 2 + maxValue > 0) maxValue = -minValue * 2;
        else if (maxValue * 2 + minValue < 0) minValue = -maxValue * 2;

        int[] map = new int[maxValue - minValue + 1];
        int[] negs = new int[negSize];
        int[] poses = new int[posSize];
        negSize = 0;
        posSize = 0;
        for (int v : nums) {
            if (v >= minValue && v <= maxValue) {
                if (map[v - minValue]++ == 0) {
                    if (v > 0)
                        poses[posSize++] = v;
                    else if (v < 0)
                        negs[negSize++] = v;
                }
            }
        }
        Arrays.sort(poses, 0, posSize);
        Arrays.sort(negs, 0, negSize);
        int basej = 0;
        for (int i = negSize - 1; i >= 0; i--) {
            int nv = negs[i];
            int minp = (-nv) >>> 1;
            while (basej < posSize && poses[basej] < minp)
                basej++;
            for (int j = basej; j < posSize; j++) {
                int pv = poses[j];
                int cv = 0 - nv - pv;
                if (cv >= nv && cv <= pv) {
                    if (cv == nv) {
                        if (map[nv - minValue] > 1)
                            res.add(Arrays.asList(nv, nv, pv));
                    } else if (cv == pv) {
                        if (map[pv - minValue] > 1)
                            res.add(Arrays.asList(nv, pv, pv));
                    } else {
                        if (map[cv - minValue] > 0)
                            res.add(Arrays.asList(nv, cv, pv));
                    }
                } else if (cv < nv)
                    break;
            }
        }
        return res;

    }
}

//6ms, 100%