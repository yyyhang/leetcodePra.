/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?



An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
 */

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // check the inupt array in case we exceed the bound
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        if (m == 0 || n == 0) return 0;

        // will it initialize all the elements to zero? yes
        int[][] paths = new int[n][m];

        for (int i = 0; i < m; i++){
            if (obstacleGrid[0][i] == 0) paths[0][i] = 1;
            // we can also comment this line
            else if (n == 1) return 0;
            else break;
        }

        for (int i = 0; i < n; i++){
            if (obstacleGrid[i][0] == 0) paths[i][0] = 1;
            else if (m == 1) return 0;
            else break;
        }
        // then we start to scan from (1,1)
        for (int x = 1; x < n; x++){
            for (int y = 1; y < m; y++){
                if (obstacleGrid[x][y] == 0) {
                    paths[x][y] = paths[x - 1][y] + paths[x][y - 1];
                }
            }
        }

        return paths[n-1][m-1];
    }
}
// my solution, faster than 100%, less than 100%

/*
Note:
    1. for 2D array arr[m][n], the m is the vertical, the n is the horizontal. do not put in wrong order
    2. check out what if we have only one line or column
 */


// another solution:
class Solution_2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int R = obstacleGrid.length;
        int C = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Filling the values for the first column
        for (int i = 1; i < R; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < C; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < R; i++) {
            for (int j = 1; j < C; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[R - 1][C - 1];
    }
}

// we ca nalso use recursive way
// https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-63-unique-paths-ii/
