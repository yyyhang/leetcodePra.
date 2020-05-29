/*
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.



Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1


Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?



Constraints:

The number of elements of the BST is between 1 to 10^4.
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
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

// we use inorder here. instead add these nodes to an array, we just cnt++ until the cnt == k

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        // we use this to record the nth smallest and the value
        int [] record = new int[2];
        inorder(root, k, record);
        return  record[1];
    }
    private void inorder(TreeNode root, int k, int[] record) {
        // terminal point
        if (root == null) return;
        // we traversal all the nodes at first
        inorder(root.left, k, record);
        // what's the difference btwe z++ and ++z. why it changed the result?
        // since we start from the 0, but the k starts from the 1, we need to use ++z
        if (record[0]++ == k) {
            record[1] = root.val;
            return;
        }
        // then we traversal the right side
        inorder(root.right, k, record);
    }
}

/*
int[] M = {1,2}
int a = M[0]++;     // a = 1, it assigns value first, and then add one
int b = ++M[0];     // b = 3, it adds one first, then assign value
// M = {3,2}
 */