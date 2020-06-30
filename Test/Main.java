import java.util.Arrays;
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
        // int[] nums = {1,3,1,5,2,3,5,7,9,8};
        int[] nums = {1,6,1};
        // TreeNode right = new TreeNode(20,new TreeNode(15),new TreeNode(7));
        // TreeNode root = new TreeNode(-10, new TreeNode(9), right);
        res.smallestDistancePair(nums, 3);

    }
}



class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int[] diff = new int[nums.length - 1];
        int j = 0;
        for (int i = 1; i <= nums.length-1; i++) {
            diff[j++] = nums[i]  - nums[i-1];
        }
        Arrays.sort(diff);
        return diff[k-1];
    }
}