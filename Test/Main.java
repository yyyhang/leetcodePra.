import java.util.HashMap;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Main{
    public static void main(String[] args) {
        Solution res = new Solution();
        TreeNode right = new TreeNode(20,new TreeNode(15),new TreeNode(7));
        TreeNode root = new TreeNode(-10, new TreeNode(9), right);
        res.maxPathSum(root);

    }
}



class Solution {

    int result = Integer.MIN_VALUE;

    private int helper(TreeNode root) {
        if (root == null) return 0;
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);
        int ms = Math.max(Math.max(left,right)+root.val, root.val);
        int m2l = Math.max(ms, left+right+root.val);
        result = Math.max(m2l, result);
        return ms;
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        helper(root);
        return result;
    }
}