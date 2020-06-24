/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST.
Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        // find the node
        if (key == root.val) {
            root = helper(root);
        }
        else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    private TreeNode helper(TreeNode root) {
        if (root.left == null) return root.right;
        else if (root.right == null) return root.left;
        // if it has 2 children, we find the smallest value in right subtree
        TreeNode tmp = root.right;
        while (tmp.left != null) tmp = tmp.left;
        // we put all the left subtree to this value, instead changing the node with the target one
        tmp.left = root.left;
        return root.right;
    }
}

// 0 ms, faster than 100.00%, jump out of the box


// can also see this: https://zxi.mytechroad.com/blog/tree/leetcode-450-delete-node-in-a-bst/