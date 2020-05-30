/*
Given two non-empty binary trees s and t, check whether tree t has exactly the same structure
and node values with a subtree of s.
A subtree of s is a tree consists of a node in s and all of this node's descendants.
The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.


Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.
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

// we need to find the node of t in tree s, then compare them just like the same tree
public class Solution {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s,t);
    }

    public boolean equals(TreeNode x,TreeNode y) {
        if(x==null && y==null) return true;
        if(x==null || y==null) return false;
        return x.val==y.val && equals(x.left,y.left) && equals(x.right,y.right);
    }

    public boolean traverse(TreeNode s,TreeNode t) {
        return  s!=null && ( equals(s,t) || traverse(s.left,t) || traverse(s.right,t));
    }
}

// 5ms, 96.73%

// this one is not right

// it cannot pass [4,1,null,1,null,6,7] [4,1,null,6,7]
class Solution_wrong {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return helper(s, t, false);
    }

    boolean helper(TreeNode s, TreeNode t, boolean start) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;

        if (s.val == t.val) {
            // if it not same, then it won't return false. it will keep running to match another one
            // this is bcz it not return to the first node, i.e.4.
            // as they are diffe at 1 and 6, it will return 1 and 1, and use 1 as the first node t ro match another one
            if (helper(s.left, t.left, true) && helper(s.right, t.right, true)) return true;
            // so, if we need to correct this solution , we need add here:
            // else if (start) return false;
            // this can return to the very first node t.
        }
        if (s.val != t.val && start) return false;
        return helper(s.left, t, false) || helper(s.right, t, false);
    }
}

// 5ms, 96.73%