/*
Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        recur(curr,res);
        return res;
    }


    private void recur(TreeNode curr, List <Integer> res){
        //notice: return void
        if (curr == null) return;
        res.add(curr.val);
        recur(curr.left,res);
        recur(curr.right,res);
    }
}

class Solution2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList < > ();
        Stack <TreeNode> stack = new Stack < > ();
        if (root == null) return res;
        TreeNode curr = root;
        stack.push(curr);

        while (!stack.empty()){
            curr = stack.pop();
            res.add(curr.val);
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        return res;
    }
}


/*
public List<Integer> preorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> list = new ArrayList<>();
    if(root == null) return list;
    while(root != null || !stack.isEmpty()){
        while(root != null){
            stack.push(root);
            list.add(root.val);
            root = root.left;
        }
        TreeNode topNode = stack.pop();
        root = topNode.right;
    }
    return list;
}
*/