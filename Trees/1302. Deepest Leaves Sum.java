/*
Given a binary tree, return the sum of values of its deepest leaves.


Example 1:



Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15


Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
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

// BFS
class Solution {
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            sum = 0;
            for (int i=0; i<size; i++){
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return sum;
    }
}

//3 ms