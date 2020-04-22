/*
Given a binary search tree with non-negative values,
find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).


Note:

There are at least two nodes in this BST.
This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        Integer tmp = null;
        int min = Integer.MAX_VALUE;
        // for max, set the max as the miniman, while set the min as miniman

        while(root!=null || !stack.isEmpty()){
            while (root!= null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (tmp != null) min = Math.min(root.val-tmp, min);
            // tips: using null and if condition to deal with the forst element
            // you can't set the int varable as null, but Integer can

            tmp = root.val;
            root = root.right;
            // remember, especially in the loop, check if you updates all condition. otherwise, it will endless
        }
        return min;
        // remember where is the out of this function and you then need to return right answer.

    }
}

/*
 Tips:   min/ max can be the name of the varable. but if you wan to compare two number, you need to use Math.min
         you can use int - Integer
         a;ways check the if you update the condition in a loop
 */