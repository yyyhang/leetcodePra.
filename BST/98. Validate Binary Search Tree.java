/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
*/

// comment: but is it valid if the child's right subtree greater than the node's key?

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Note: in order to cover the range of -2^31 ~ 2^31-1, we need to use double integer. but why we need to cover this range
// According to the define of BST, no, every nodes in the left subtree should less than the node

// then this function just like inorder travasral, and compare that if they are from smallest to biggest
class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        int max = - Integer.MAX_VALUE;
        // there should be Double, not double; Double is an object and double is a primitive data type
        // we need to put all the left child into the stack
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            // we not only neet to compare its left child, but also all of the child
            if (root.val <= max) return False;
            max = root.val;
            // update the min and then get into the right branch
            root = root.right
        }
    }
}