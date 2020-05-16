/*
Given a binary tree, you need to compute the length of the diameter of the tree.
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
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

// note in the length link, only one node can have both child nodes.

// this one is faster. but why?
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] res = new int[1];
        height(root,res);
        return res[0];
    }

    private int height(TreeNode root, int[] res){
        if(root==null) {
            return 0;
        }
        int l = height(root.left, res);
        int r = height(root.right, res);
        res[0] = Math.max(l+r,res[0]);
        return Math.max(l,r) + 1;
    }
}




// this question just like the deepest subtree. all you need to do is, compare both subtree's length,
// and choose the max one to add one as curr node's length, and return to the father node.
// but we also need a max value to store the max(curr.left+curr.right+1, max) as the longest length
// this is what i think


class Solution {
    class Pair{
        int len, max;
        Pair(int len, int max){
            this.len = len;
            this.max = max;
        }
    }

    public int diameterOfBinaryTree(TreeNode root) {
        TreeNode curr = root;
        return recur(curr).max;
    }

    private Pair recur(TreeNode curr){
        if (curr == null) return new Pair(0,0);
        Pair left = recur(curr.left);
        Pair right = recur(curr.right);
        int len = Math.max(left.len,right.len)+1;
        int max = Math.max(left.len+right.len,Math.max(left.max,right.max));
        // we can't use left.len+right.len+1 here, as we need to return the numbers of edges
        // edges = nodes - 1
        // or you can also add one here, and at the last minus as result
        return new Pair(len, max);
    }
}

/*
This is my solution. the problem is, it just like return the number of nodes. I don't know how to
return two values in Java without creating a new struct
*/