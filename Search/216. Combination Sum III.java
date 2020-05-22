/*
Find all possible combinations of k numbers that add up to a number n,
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combianations = new ArrayList<>();
        dfs(result, combianations, k, n , 1);
        return result;
    }

    public void dfs(List<List<Integer>> result, List<Integer> combianations, int k, int n, int idx){
        if (k == 0 && n == 0){
            // if (n != 0) return;
            result.add(new ArrayList(combianations));
            return;
        }

        for (int i = idx; i <= 9; i++){
            // no need to continue for larger numbers
            if (i > n) break;
            combianations.add(i);
            dfs(result, combianations, k-1, n-i, i+1);
            combianations.remove(combianations.size() - 1);
            // if (i > n) break;
        }
    }
}

// 1ms, 25%


// this one is much faster:
// https://leetcode.com/problems/combination-sum-iii/discuss/645100/Java-simple-solution-beats-100-of-Java-submissions-with-explanation.

class Solution_2 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        makeComb(1, n, new ArrayList<Integer>(), k, results);
        return results;
    }

    private void makeComb(int currNum, int remain, List<Integer> comb, int k, List<List<Integer>> results) {
        if (remain == 0 && comb.size() == k) {
            results.add(comb);
        } else if (remain >= currNum) {
            for (int i = currNum; i <= 9;i++) {
                List<Integer> copy = new ArrayList<>(comb);
                copy.add(i);
                makeComb(i + 1, remain - i, copy, k, results);
            }
        }
    }
}

// 0ms
//but why ?

// Solution 3, binary , brute force:
// https://zxi.mytechroad.com/blog/searching/leetcode-216-combination-sum-iii/
