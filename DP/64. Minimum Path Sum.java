/*
Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */

// we can check the top and the left, to see which path is cheaper

class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int length = grid[0].length;
        int height = grid.length;
        int[][] paths = new int[height][length];
        paths[0][0] = grid[0][0];
        // Note, do not put height and length in wrong order
        for (int i = 1; i < height; i++){
            paths[i][0] = grid[i][0] + paths[i-1][0];
        }
        for (int j = 1; j < length; j++){
            paths[0][j] = grid[0][j] + paths[0][j-1];
        }
        for (int y = 1; y < height; y++){
            for (int x = 1; x< length; x++){
                paths[y][x] = Math.min(paths[y-1][x], paths[y][x-1]) + grid[y][x];
            }
        }
        return paths[height-1][length-1];
    }
}

// 2ms, 87.91%


class Solution_2 {
    public int minPathSum(int[][] grid) {
        int rows = grid.length;

        if (rows==0) return 0;

        int cols = grid[0].length;

        for(int r =0; r<rows;r++){
            for(int c=0;c<cols;c++){
                if(r==0 && c!=0) grid[r][c] += grid[r][c-1]; // for First Row
                else if (r!=0 && c==0) grid[r][c] += grid[r-1][c]; // for First Column
                else if(r!=0 && c!=0) grid[r][c] += Math.min(grid[r][c-1], grid[r-1][c]); //for Rest of the elements, adds the minimum from the element at top and element at left
            }
        }
        return grid[rows-1][cols-1];
    }
}

// 2ms

// recursive way
class Solution_2 {
    public int traverse(int[][] grid, int x, int y, int[][] dp) {
        // bound
        if (x == grid.length || y == grid[0].length) {
            return Integer.MAX_VALUE;
        }
        // terminal point
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            return grid[x][y];
        }
        // traversalled before
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        return dp[x][y] = Math.min(traverse(grid, x + 1, y, dp), traverse(grid, x, y + 1, dp)) + grid[x][y];
    }

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        return traverse(grid, 0, 0, dp);
    }
}

// 0ms , 100%