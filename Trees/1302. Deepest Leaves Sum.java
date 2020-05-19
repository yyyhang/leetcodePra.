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
class Solution_BFS {
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // compute sum for every level. if we still have next level, calculate the sum again
        while (!queue.isEmpty()){
            int size = queue.size();
            sum = 0;
            // take all nodes out of this level
            // but how can i travesal the all level without using size() ??
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

// 50% run time, and 100% memory
// is it O(n)? it seems it is n^2. but why ?

// DFS

class Solution_DFS {
    public int deepestLeavesSum(TreeNode root) {
        // array in the heap
        int[] sum = new int[1];
        int[] deepest = new int[1];
        deepest[0] = -1;
        find(root, sum, deepest, 0);
        return sum[0];
    }
    private void find(TreeNode n, int[] sum, int[] deepest, int depth){
        if(n==null){return;}

        //if we find the current node to be deeper than the deepest then reset sum
        if(depth>deepest[0]){
            deepest[0] = depth;
            sum[0] = n.val;
        }
        //if we find the current node to at deepest then add its val to sum
        else if(depth==deepest[0]){
            sum[0]+=n.val;
        }
        else{
            // do nothing for shallower nodes
        }
        find(n.left, sum, deepest, depth+1);
        find(n.right, sum, deepest, depth+1);
    }
}