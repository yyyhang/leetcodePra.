/*
Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // we need a 2d array to store these results
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return results;
        Arrays.sort(candidates);
        // we use the combination to record the possible result
        List<Integer> combination = new ArrayList<>();
        toFindCombination(results, combination, candidates, target, 0);
        return results;
    }

    private void toFindCombination(List<List<Integer>> results, List<Integer> combination, int[] candidates, int target, int startIndex){
        // terminal point
        if (target == 0){
            // we need a deep copy of the combination here
            results.add(new ArrayList<>(combination));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++){
            // no need to combine the rest elements
            if (candidates[i] > target){
                break;
            }

            combination.add(candidates[i]);
            toFindCombination(results, combination, candidates, target - candidates[i], i);
            // next element at this level
            combination.remove(combination.size() - 1 );
        }
    }
}

// https://www.youtube.com/watch?v=irFtGMLbf-s