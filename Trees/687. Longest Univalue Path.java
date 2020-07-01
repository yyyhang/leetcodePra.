/*
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

The length of path between two nodes is represented by the number of edges between them.

 

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5
Output: 2

 

Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5
Output: 2

 

Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.
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
class Solution {
    int max = Integer.MIN_VALUE;
    
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        int ans = helper(root);
        return max;
    }
    
    // we record the max as max(left, right, sum)
    // but in recursive, we only return one path the root in
    private int helper(TreeNode root) {
        if (root == null) return -1;
        // we use l and r to record the path the root in
        int l = 0, r = 0;
        int right = helper(root.right);
        int left = helper(root.left);
        // if they are same, we add path by one
        if (left != -1 && root.val == root.left.val) l = left + 1;
        if (right != -1 && root.right.val == root.val) r = right + 1;
        // we compare the path which go through root and previous max
        max = Math.max(l+r, max);
        return Math.max(l, r);
    }
}
