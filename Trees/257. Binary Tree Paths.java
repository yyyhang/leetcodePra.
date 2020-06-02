/*
Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
class Solution {
    public List binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        if(root==null) return ans;
        String tmp="";

        helperPaths(root,ans,tmp);
        return ans;
    }

    public void helperPaths(TreeNode node , List<String> ans, String tmp) {
        if(node==null) return;
        tmp += node.val;
        // we meet the leaf node
        if(node.left==null && node.right==null) ans.add(tmp);
        helperPaths(node.left,ans,tmp+"->");
        helperPaths(node.right,ans,tmp+"->");
        // is String a global or local? why we don't need to remove the last here, but 129 needs?
        return;
    }
}

// 8ms. 60.70%

class Solution_2 {

    List<String> list = new ArrayList<String>();
    static final String ARROW = "->";

    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null) return list;
        getTreePath(root, new StringBuilder());
        return list;
    }

    private void getTreePath(TreeNode tn, StringBuilder sb) {
        // Add the node value
        sb.append(tn.val);
        // If it is leaf node, add the value to the list
        if(tn.left == null && tn.right == null) list.add(sb.toString());
        // If not a leaf node
        else {
            // Find the length of StringBuilder at this point (this will come in handy later)
            int len = sb.length();
            //If there is left node, call the function again
            if(tn.left != null) getTreePath(tn.left, sb.append(ARROW));

            // If there is a right node, call the function again.
            // Here we have used the same StringBuilder object, but trimmed down to the length
            // just before calling the getTreePath() function on the left node above
            // Since StringBuilder is mutable, the call to getTreePath() for left node above
            // changes the object and appends the left node values. Hence we need to remove
            // the left node values, before we call the getTreePath() function on right node
            // You can initialize a new StringBuilder object just after else statement
            // StringBuilder sb2 = new StringBuilder(sb); and use that sb2 below.
            // but we are reusing same StringBuilder object to save on memory
            if(tn.right != null)
                getTreePath(tn.right, sb.delete(len, sb.length()).append(ARROW));
        }
    }
}

// 1ms, 100%
// https://leetcode.com/problems/binary-tree-paths/discuss/631434/Java-1ms-beats-100-code-with-comments

class Solution_3 {
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        path(root, list, new StringBuilder());
        return list;
    }

    private static void path(TreeNode root, List<String> list, StringBuilder sb) {
        if (root != null) {
            sb.append(root.val);
            int len = sb.length();
            if (root.left == null && root.right == null) {
                list.add(sb.toString());
            } else {
                path(root.left, list, sb.append("->"));
                // remove, and then it can use for right subtree
                sb.delete(len,sb.length());
                path(root.right, list, sb.append("->"));
                // we return to callee, there is no need to return to remove
            }
        }
    }
}

// 1 ms, faster than 100.00%