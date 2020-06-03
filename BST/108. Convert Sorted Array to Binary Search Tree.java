/*
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree
in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5
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

// bcz it doesn't require the shape of tree, we can set the middle element as the array recursively
// then the tree will be balanced

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length - 1);
    }

    public TreeNode buildBST(int[] nums, int l, int r){
        // when l = r, we still need to add this node to the tree
        if (l>r) return null;
        int m = l + (r - l) / 2;
        // create a new root
        TreeNode root = new TreeNode(nums[m]);
        // we use the left side and right side of the curr node as subtree, but not include the curr node
        root.left = buildBST(nums, l, m - 1);
        root.right = buildBST(nums, m + 1, r);
        return root;
    }
}

// 0 ms, faster than 100.00%