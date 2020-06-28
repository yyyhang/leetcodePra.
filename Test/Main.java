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
        res.findKthNumber(3,3,5);

    }
}



class Solution {
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = n*m;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cnt = helper(m, n, k, mid);
            if (cnt >= k) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private int helper(int m, int n, int k, int mid) {
        int x = 1, y = n;
        int cnt = 0;
        while (x <= m && y >= 0) {
            if (x*y <= mid) {
                cnt += y;
                x++;
            } else {
                y--;
            }
        }
        return cnt;
    }
}