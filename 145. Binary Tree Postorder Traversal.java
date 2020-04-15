/*
Given a binary tree, return the postorder traversal of its nodes' values.

Recursive solution is trivial, could you do it iteratively?
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



