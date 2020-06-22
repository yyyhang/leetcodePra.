/*
Given the root of a tree, you are asked to find the most frequent subtree sum.
The subtree sum of a node is defined as the sum of all the node values formed by the
subtree rooted at that node (including the node itself). So what is the most frequent
subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

   5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

   5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// they need to add all subtree and the value of itself

// the thing that i can come up with is that, each time i caculate the sum, i stor it into a hashmap
// for this one, the tricky thing is the convertion of Object and int;
class Solution {
    int max = Integer.MIN_VALUE;
    List<Integer> ans = new ArrayList<>();
    HashMap<Integer, Integer> map = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[0];
        helper(root);
        int[] res = new int[ans.size()];
        int i = 0;
        // convert to int[] from ArrayList
        for (Integer each : ans) {
            res[i] = each;  // unboxing
            i++;
        }
        return res;
    }

    public int helper(TreeNode root) {
        if (root == null) return 0;
        int sum = helper(root.left) + helper(root.right) + root.val;
        // memorize this one
        map.put(sum, map.getOrDefault(sum,0) + 1);  //autoboxing
        // unboxing
        if (map.get(sum) > max) {
            max = map.get(sum);
            ans.clear();
            ans.add(sum);
        }
        else if(map.get(sum) == max) {
            ans.add(sum);
        }
        return sum;
    }
}

// 2 ms, faster than 100.00%

/*

    // why if I pass map as parameter, then the map.get() becomes object???

    public int helper(TreeNode root, HashMap map, List<Integer> ans) {
        if (root == null) return 0;
        int sum = helper(root.left, map) + helper(root.right, map) + root.val;
        // memorize this one
        map.put(sum, map.getOrDefault(sum,0)+1);
        if (map.get(sum) > max) {
            ans.clear();
            ans.add(sum);
        }
        else if (map.get(sum) == max) {
            ans.add(sum);
        }
        return sum;
    }

Line 37: error: bad operand types for binary operator '+'
        map.put(sum, map.getOrDefault(sum,0)+1);
                                            ^
  first type:  Object
  second type: int
Line 39: error: bad operand types for binary operator '>'
        if (map.get(sum) > max) {
                         ^
  first type:  Object
  second type: int
Line 43: error: bad operand types for binary operator '=='
        else if (map.get(sum) == max) {
                              ^
  first type:  Object
  second type: int
3 errors
 */



/*
    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }
        return list.stream().mapToInt(i->i).toArray();
    }
 */