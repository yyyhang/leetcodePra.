/*
https://leetcode.com/problems/unique-paths/

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 7 x 3 grid. How many possible unique paths are there?



Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28


Constraints:

1 <= m, n <= 100
It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
 */

// DP
// for each grid, it can be only reached by its top grid or left grid
// so the possible paths to this grid is sum(top, left)

class Solution {
    public int uniquePaths(int m, int n) {
        // check the boundray
        if (m == 0 || n == 0) return 0;
        // initialise
        int[][] paths = new int[m][n];
        // for these two lines, we only have one path to reach
        // initialise them here, then we do not need to consider the boundary later
        for (int i = 0; i < m; i++) paths[i][0] = 1;
        for (int j = 0; j < n; j++) paths[0][j] = 1;
        // note the right side need to be stored first
        // that's why we scan from left to right and then top to bottom
        for (int x = 1; x < m; x++){
            for (int y = 1; y < n; y++){
                paths[x][y] = paths[x-1][y] + paths[x][y-1];
            }
        }
        return paths[m-1][n-1];
    }
}

// refer: http://www.goodtecher.com/leetcode-62-unique-paths/


// This is a solution using math:
// we need walk m+n-2 steps. and choose m-1 as vertical/ horizontal.
// bcz we can just move to the next grid, it is not permutation
class Solution_1 {
    public int uniquePaths(int m, int n) {
        return nCr (m+n-2, n-1);
    }

    public int nCr(int n, int r) {
        // we need to use double(64 bytes) to store the value, otherwise the result will be too huge
        // as sometimes the result is not an integer, we need to use round to get the integer
        return (int) Math.round((fact(n) / (fact(r) * fact(n - r)))) ;
    }

    // n!
    public double fact(int n) {
        double res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }
}
// 0ms, 100%

// This can be even faster:
class Solution_2 {
    public int uniquePaths(int m, int n) {
        if (n > m) return nCr(m+n-2, n-1);
        return nCr(m+n-2, m-1);
    }

    public int nCr(int n, int r) {
        if (n == r) return 1;
        return (int) Math.round((reverseFact(n, r) / fact(n - r))) ;
    }

    public double reverseFact(int n, int r){
        // using double in case overflow
        double res = n;
        for (int i = n-1; i>r; i--)
            res = res * i;
        return res;
    }

    public double fact(int n) {
        // in case we meet m!/0!
        double res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }
}


// revursive way
class Solution_dfs {
    private int[][] dp;
    private int m;
    private int n;

    public int uniquePaths(int m, int n) {
        this.dp = new int[m][n];
        this.m = m;
        this.n = n;
        return dfs(0, 0);
    }

    private int dfs(int x, int y) {
        // boundary
        if (x > m - 1 || y > n - 1)
            return 0;
        // terminal point: reach destination
        if (x == m - 1 && y == n - 1)
            return 1;
        if (dp[x][y] == 0)
            dp[x][y] = dfs(x + 1, y) + dfs(x , y + 1);
        return dp[x][y];
    }
}

// refer: https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-62-unique-paths/
