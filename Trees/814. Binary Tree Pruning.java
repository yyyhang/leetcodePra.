/*
We are given the head node root of a binary tree, where additionally every node's value is either a 0 or a 1.

Return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

(Recall that the subtree of a node X is X, plus every node that is a descendant of X.)

Example 1:
Input: [1,null,0,0,1]
Output: [1,null,0,null,1]

Explanation:
Only the red nodes satisfy the property "every subtree not containing a 1".
The diagram on the right represents the answer.


Example 2:
Input: [1,0,1,0,0,0,1]
Output: [1,null,1,null,1]



Example 3:
Input: [1,1,0,1,1,0,1,0]
Output: [1,1,0,1,1,null,1]



Note:

The binary tree will have at most 100 nodes.
The value of each node will only be 0 or 1.
 */

// we need to delete all subtrees not containing 1

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
    public TreeNode pruneTree(TreeNode root) {
        // incase [0,0,0,0]
        if (!helper(root)) return null;
        return root;
        // return (helper(root)) ? root : null;
    }

    private boolean helper(TreeNode root) {
        // terminal point
        if (root == null) return false;
        if (!helper(root.left)) root.left = null;
        if (!helper(root.right)) root.right = null;
        if (root.left == null && root.right == null && root.val != 1) return false;
        return true;
    }
}

// 100%
//O(n)



class Solution_2 {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return root;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        return (root.val == 1 || root.left != null || root.right != null) ? root : null;
    }
}