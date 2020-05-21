/*
Given a collection of candidate numbers (candidates) and a target number (target),
find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 */

// it is samilar to the previous one, but the dofference is that it can only use one elements once

// so the point of this quesion is how to get rid of the dupulicates

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return results;
        // doing sorting at first to reduce the complexity
        Arrays.sort(candidates);
        List<Integer> combination = new ArrayList<>();
        toFindCombination(results, combination, candidates, target, 0);
        return results;
    }

    private void toFindCombination(List<List<Integer>> results, List<Integer> combination, int[] candidates, int target, int startIndex){
        if (target == 0){
            results.add(new ArrayList<>(combination));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++){
            // check if the current element is a dulicated one or not. otherwise, we may have [1,2,5] [1,2,5] twice
            // using i != startIndex to make sure it won't exceed the bondary
            // Since candidates[i] here is the 1st elemt in combination, this can avoid have same combination
            if (i != startIndex && candidates[i] == candidates[i-1]) continue;
            if (candidates[i] > target) break;

            combination.add(candidates[i]);
            // to find next one. we just traversal the rest elements. otherwise we will meet this element again
            toFindCombination(results, combination, candidates, target - candidates[i], i+1);
            combination.remove(combination.size() - 1 );
        }
    }
}