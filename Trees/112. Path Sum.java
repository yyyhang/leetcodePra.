/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path
such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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

// we use recursive to treat this question as sub question.
// every node is a new root

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        // first, we need to find the terminal point
        if (root == null) return false;
        // IMPORTANT: sum can be negative
        // if (root.val > sum) return false;
        if (root.val == sum && root.left == null && root.right == null) return true;
        // if (hasPathSum(root.left, sum - root.val)) return true;
        // if (hasPathSum(root.right, sum - root.val)) return true;
        // return false;
        return (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val)) ? true : false;
    }
}

// 100%

// [-2,null,-3]
// -5

// so, for this kind of question, i should ask if the sum can be negative or not