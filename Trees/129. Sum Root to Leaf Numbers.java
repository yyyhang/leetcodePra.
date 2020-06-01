/*
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.
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

// compare with 113, this one no need to remove the last elemtent.
// bcz list is global, but the int is not. when we return to the callee, it back to its previous value

class Solution {
    int total_sum;
    public int sumNumbers(TreeNode root) {
        helper(root, 0);
        return total_sum;
    }

    public void helper(TreeNode root, int sum) {
        if(root == null) return ;
        sum= (sum*10) + root.val;

        if(root.left == null && root.right == null) {
            total_sum += sum;
            return ;
        }

        helper(root.left, sum);
        helper(root.right, sum);

    }
}
//100%


// or we can use string. but for this one, we need to remove the last element

class Solution_2 {
    int sum=0;
    void solve(TreeNode root, StringBuilder str){
        if(root==null)return;
        str.append(root.val);
        // change the string to the int
        if(root.left==null && root.right == null) sum += Integer.parseInt(str.toString());
        solve(root.left,str);
        solve(root.right,str);
        // string is global, we need to remove the last element manually
        str.setLength(str.length()-1);
    }
    public int sumNumbers(TreeNode root) {
        StringBuilder str = new StringBuilder("");
        solve(root,str);
        return sum;
    }
}

// 100%