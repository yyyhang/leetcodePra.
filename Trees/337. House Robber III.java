/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
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

// this first thing I came up is using DP, but how to implement DP for tree?

class Solution {
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int sum = root.val;
        // they are the optimal val the root can add of that path, which not only contains the vlaue of its grandchildren, but all the val it can add 
        if (root.right != null) sum += rob(root.right.right) + rob(root.right.left);
        if (root.left != null) sum += rob(root.left.right) + rob(root.left.left);
        return Math.max(sum, rob(root.left) + rob(root.right));
    }
}

// 655 ms, faster than 27.22% of Java online submissions for House Robber III.

/*
why we do not consider the child of the grandson, is bcz the value the grandchild returns contains the value of it's children

e.g.                              9
                                 / \
                                2   5
                               / \   \
                              2   3   1
                                       \
                                       13
                                       
When 9 recursively need results from grandchild 1, the node of 1 will return the value of the max(itself, child), which is the biggest value the root 9 
can add (will return 13). 
So, what we need to do, is just compare the reults of the root and its children, as they cannot be robbed at same time 
*/

// to implement DP for tree, we cannnot just use aray to store the dp value, instead we can use hashtable to store the dp value. in that case, we can
// set the node as the key, and the sum asthe value

class Solution {
    
    HashMap<TreeNode, Integer> map = new HashMap<>();
    
    public int rob(TreeNode root) {
        if (root == null) return 0;
        // this can reduce the redudence of computing same subquestions
        if (map.containsKey(root)) return map.get(root);
        int sum = root.val;
        if (root.right != null) sum += rob(root.right.right) + rob(root.right.left);
        if (root.left != null) sum += rob(root.left.right) + rob(root.left.left);
        // do not forget to store the value into the hash map;
        // we need to store the value it needs to return rather than the value of sum
        int res = Math.max(sum, rob(root.left) + rob(root.right));
        map.put(root,res);
        return res;
    }
}

// 2 ms, faster than 55.99% 

// Solution 3

class Solution {
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    
    // we use an array to store 2 results of this node: rob(1) or not rob(0)
    private int[] helper(TreeNode root) {
        if (root == null) return new int[2];
        int[] res = new int[2];
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        // when the root choose not to steal, then the vlaue should be the max value of its children
        // no matter they steal or not
        res[0] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        res[1] = left[0] + right[0] + root.val;
        return res;  
    }
}

// 2ms ???

class Solution {
    public int rob(TreeNode root) {
        int[] ans = robProperly(root);
        return Math.max(ans[0], ans[1]);
    }
    // include, exclude
    private int[] robProperly(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = robProperly(root.left);
        int[] right = robProperly(root.right);
        // include root
        // why here can cause 2ms difference ?!
        int include = root.val + left[1] + right[1];
        int exclude = Math.max(left[1], left[0]) + Math.max(right[1], right[0]);
        return new int[]{include, exclude};
    }
}

// 0ms????
