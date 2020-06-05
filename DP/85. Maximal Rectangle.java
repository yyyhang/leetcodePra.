/*
Given a 2D binary matrix filled with 0's and 1's,
find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
 */

/*
for this question, the first that i think is using dfs. actually, i think this one is simalar to
one of my 9021 quizs
 */

// we use the histogram. we scan the column from the top to the bottom
// we use the curr row as the base
// reference:
// https://www.youtube.com/watch?v=g8bSdXCG-lA

/*
1 0 1 0 0      1 0 1 0 0
1 0 1 1 1  =>  2 0 2 1 1
1 1 1 1 1  =>  3 1 3 2 2
1 0 0 1 0      4 0 0 3 0
 */

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        // we use this to record the histogram
        int[] dp = new int[m];
        int maxArea = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                // for the first row
                if (i == 0) dp[j] = matrix[i][j] == '1' ? 1 : 0;
                // else add it to the previous
                else dp[j] = matrix[i][j] == '1' ? (dp[j]+1) : 0;
                int min = dp[j];
                // from this point to scan to the right to get the area
                for (int k = j; k >= 0; k--) {
                    // if the value of this point is 0, there is no area at all
                    if (min == 0) break;
                    if (dp[k] < min) min = dp[k];
                    // but wil;l we have some rectangles that do not need to go back to the smallest left side?
                    //for example, it can form a bigger area just with the next one
                    // that's why we use this to compare each
                    maxArea = Math.max(maxArea, min * (j - k + 1));
                }
            }
        }
        return maxArea;
    }
}

// 4 ms, faster than 82.62%

// solution2, but still feel confused
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if(matrix==null||matrix.length==0) return 0;
        int n=matrix.length;
        int m=matrix[0].length;
        // why use m+1 here?
        int[] height=new int[m+1];
        int max=0;
        for(int i=0;i<matrix.length;i++){
            updateHeights(matrix, i, height);
            max=Math.max(max,maxArea(height));
        }
        return max;

    }
    private void updateHeights(char[][] matrix, int row, int[] heights) {
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[row][col] == '1') {
                heights[col]++;
            } else {
                heights[col] = 0;
            }
        }
    }

    private int maxArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n + 1];
        // why use l?
        int l = 0;
        stack[l] = -1;
        int res = 0;
        for (int r = 0; r < n; r++) {
            while (l > 0 && heights[r] < heights[stack[l]]) {
                int h = heights[stack[l--]];
                res = Math.max(res, h * (r - stack[l] - 1));
            }
            stack[++l] = r;
        }
        return res;
    }
}
// 1 ms, faster than 100.00%
// why this is faster ?

