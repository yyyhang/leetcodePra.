/*
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node
to any node in the tree along the parent-child connections. The path must contain at
least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 */

// https://www.youtube.com/watch?v=TO5zsKtc1Ic

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

/*
we have 3 cases, one is the node is the root of that path, one is the node is on one side of that path,
or the last is not in the path
 */

    // https://www.youtube.com/watch?v=TO5zsKtc1Ic

class Solution {
    int result = Integer.MIN_VALUE;

    private int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        int ms = Math.max(Math.max(left,right)+root.val, root.val);
        int m2l = Math.max(ms, left+right+root.val);
        result = Math.max(m2l, result);
        return ms;
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        helper(root);
        return result;
    }
}

//  0 ms, faster than 100.00%