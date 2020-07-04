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
        char[][] nums = {{'5','3','.','.','7','.','.','.','.'},};
        // TreeNode right = new TreeNode(20,new TreeNode(15),new TreeNode(7));
        // TreeNode root = new TreeNode(-10, new TreeNode(9), right);
        res.isValidSudoku(nums);

    }
}



class Solution {
    int[][] rows = new int[9][10];
    int[][] cols = new int[9][10];
    int[][] box = new int[9][10];

    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                char d = board[i][j];
                if (d != '.') {
                    int num = d - '0';
                    if (!place(num, i, j)) return false;
                }
            }
        }
        return true;
    }

    private boolean place(int num, int row, int col) {
        int idx = (row / 3) * 3 + (col / 3);
        if (rows[row][num] + cols[col][num] + box[idx][num] == 0)
            return false;
        else{
            rows[row][num]++;
            cols[col][num]++;
            box[idx][num]++;
            return true;
        }
    }
}