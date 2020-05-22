/*
Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */

// DFS
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> subset = new ArrayList<>();
        result.add(new ArrayList<>(subset));
        dfs(nums, result, subset, 0);
        return result;
    }

    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> subset, int index){
        if(index == nums.length){
            // result.add(new ArrayList<>(subset));
            return;
        }
        for(int i = index; i < nums.length; i++){
            subset.add(nums[i]);
            // we add all subsets to the results
            result.add(new ArrayList<>(subset));
            dfs(nums, result, subset, i+1);
            subset.remove(subset.size()-1);
        }
    }
}

// 1ms 57.14%

// we can change it to this:

class Solution_dfs {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        // result.add(new ArrayList<>(subset));
        dfs(nums, result, subset, 0);
        return result;
    }
    private void dfs(int[] nums, List<List<Integer>> result, List<Integer> subset, int index){
        result.add(new ArrayList<>(subset));
        for(int i = index; i < nums.length; i++){
            subset.add(nums[i]);
            dfs(nums, result, subset, i+1);
            subset.remove(subset.size()-1);
        }
    }
}

// binary
// give all possible mode from [00..00] to [11..11]. and then use 1 to check each bit is 1 or not
class Solution_bin {
    public List<List<Integer>> subsets(int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < 1<<length; i++){
            List<Integer> subset = new ArrayList<>();
            // take out the mode, 101, for example. then check each bit is 1 or not.
            // if it is 1, add it to the subset
            for(int j = 0; j < length; j++){
                // 2 & (1<<1) = 2
                // b(101) & (1<<0) = 1; b(101) & (1<<2) = 4; b(101) & (1<<1) = 0;
                if((i & (1<<j)) != 0){
                    subset.add(nums[j]);
                }
                // or use (i>>j & 1) == 1;
            }
            result.add(new ArrayList<>(subset));
        }
        return result;
    }
}
//2ms
// why it is slower than the later one? bcz the bits shift (line 104 vs line 76)?

class Solution_bin2 {
    public List<List<Integer>> subsets(int[] nums) {
        int allones = 1, l = nums.length;
        while(--l > 0) {
            allones <<= 1; // to get 100...000 (n zeros)
        }
        allones = (allones<<1) - 1; // to get 11...111 (n ones)
        List<List<Integer>> list = new ArrayList<>();
        for(int i=1;i<=allones;i++){
            List<Integer> sub = new ArrayList<>();
            for(int j=0;j<nums.length;j++){
                if((i>>j & 1) == 1) sub.add(nums[j]);
            }
            list.add(sub);
        }
        // add empty
        list.add(new ArrayList<>());
        return list;
    }
}
// 0ms, O(n*2^n)

