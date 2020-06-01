/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
 */

// it is samilar to the 112, but we need to find all the paths

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

// we can use a queue/linkedlist to add the possible result. if it is the sum, then we add it to the res
// otherwise, remove the last element and try another child

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        // don't forget call the function....
        helper(root, sum, res, tmp);
        return res;
    }

    public void helper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> tmp) {
        if (root == null) return;
        tmp.add(root.val);
        // note, we need to add a new array rather than add a reference
        if (root.val == sum && root.left == null && root.right == null) res.add(new ArrayList(tmp));
        helper(root.left, sum - root.val, res, tmp);
        helper(root.right, sum - root.val, res, tmp);
        // remove the last element
        tmp.remove(tmp.size() - 1);
    }
}

// 1ms, 99.97%

// for linkedlist, we can use the list.removeLast()