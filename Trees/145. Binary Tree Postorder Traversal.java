/*
Given a binary tree, return the postorder traversal of its nodes' values.

Recursive solution is trivial, could you do it iteratively?
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

/*
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        recur(curr,res);
        return res;
    }

    private void recur(TreeNode curr, List <Integer> res){
        if (curr == null) return;
        recur(curr.left,res);
        recur(curr.right,res);
        res.add(curr.val);
    }
}
 */

// go to the right child at first as pre order, then reverse it

class Solution {
    public List<Integer> postorderTraversal(TreeNode root){
        List <Integer> res = new ArrayList <>();
        if (root == null) return res;
        Stack <TreeNode> stack = new Stack <>();
        stack.push(root);
        while (!stack.empty()){
            TreeNode curr = stack.pop();
            res.add(0, curr.val);
            // every time put the value at the first index
            if (curr.left != null) stack.push(curr.left);
            // first push, last pop. we need to go to the right tree at first
            if (curr.right != null) stack.push(curr.right);
        }
        return res;
    }
}


// all of the traversal ways can be shift to this method, pre an inorder can
// easily code directly tho.

