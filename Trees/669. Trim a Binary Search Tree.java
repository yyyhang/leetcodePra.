/*
Given a binary search tree and the lowest and highest boundaries as L and R,
trim the tree so that all its elements lies in [L, R] (R >= L).
You might need to change the root of the tree, so the result should return
the new root of the trimmed binary search tree.

Example 1:
Input:
    1
   / \
  0   2

  L = 1
  R = 2

Output:
    1
      \
       2
Example 2:
Input:
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output:
      3
     /
   2
  /
 1
 */

// but what if the 0 node has two children? recall the property of the binary search tree

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

// this is a binary search tree. recalling the property of bst, all the left nodes less than the root
// and all the right nodes bigger than root

class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return null;
        // if (root.val < L) return root.right; this is not right, it will return the whole children whithout checking
        if (root.val < L) return trimBST(root.right, L, R);
        if (root.val > R) return trimBST(root.left, L, R);
        // else L <= root.val <= R, we need to check its children recursively
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        // remember to return itself
        return root;
    }
}

// 0ms, 100%