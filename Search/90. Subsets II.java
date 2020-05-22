/*
Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */

// note you might have dupicate [1,2] and [1,2]

// we can sort the array at first. and for the same depth, only the first same element will be used.
// samilar to question 40

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> subset = new ArrayList<>();
        result.add(new ArrayList<>());
        dfs(nums, 0, result, subset);
        return result;
    }

    public void dfs(int[] nums, int idx, List<List<Integer>> result, List<Integer> subset){
        for (int i = idx; i < nums.length; i++){
            // duplicated will not be consider at the same depth
            // but it can be same at deffrent depth
            if (i != idx && nums[i] == nums[i-1]){
                continue;
            }

            subset.add(nums[i]);
            result.add(new ArrayList<>(subset));
            dfs(nums, i+1, result, subset);
            subset.remove(subset.size()-1);
        }
    }
}

//2ms


class Solution_2 {

    public void backtrack(List<List<Integer>> result,List<Integer> tempList,int[] nums,int start){
        result.add(new ArrayList<>(tempList));
        for(int i=start;i< nums.length;i++){
            if(i != start && nums[i] == nums[i-1]){
                continue;
            }
            tempList.add(nums[i]);
            backtrack(result,tempList,nums,i+1);
            tempList.remove(tempList.size()-1);
        }

    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0){
            return result;
        }
        Arrays.sort(nums);
        List<Integer> tempList = new ArrayList<>();
        backtrack(result,tempList,nums,0);
        return result;

    }
}

/1ms