/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */

// what if n < k ? what if n = 0 ?
// return []. Because it cannot reach to the terminal point
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> combi = new ArrayList<>();
        helper(n, k, res, combi, 1);
        return res;
    }

    private void helper(int n, int k, List<List<Integer>> res, List<Integer> combi, int idx){
        // terminal
        if (k == 0){
            // we need a deep copy here. otherwise the reference might change
            res.add(new ArrayList<>(combi));
            return;
        }
        //
        for (int i = idx; i <= n; i++){
            combi.add(i);
            // we need to use the rest elements after current index
            helper(n, k - 1, res, combi, i + 1);
            combi.remove(combi.size() - 1);
        }
    }
}

// 31ms, 22.99%