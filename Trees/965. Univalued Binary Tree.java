/*
A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.



Example 1:

                1
               / \
              1   1
             / \   \
            1  1    1

Input: [1,1,1,1,1,null,1]
Output: true


Example 2:

                2
               / \
              2   2
             / \
            5   2

Input: [2,2,2,5,2]
Output: false


Note:

The number of nodes in the given tree will be in the range [1, 100].
Each node's value will be an integer in the range [0, 99].
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
    public boolean isUnivalTree(TreeNode root) {
        return helper(root, root.val);
    }

    public boolean helper(TreeNode root, int value) {
        if (root == null) return true;
        if (root.val != value) return false;

        return helper(root.left, value) && helper(root.right, value);
    }
}

// 0ms, 100%