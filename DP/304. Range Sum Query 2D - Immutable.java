/*
https://leetcode.com/problems/range-sum-query-2d-immutable/

Given a 2D matrix matrix, find the sum of the elements inside the rectangle
defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]

Range Sum Query 2D
The above rectangle (with the red border) is defined by
(row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

// thie first thing that i can come up is that, we can create a new matrix, and for each slot,
// store a tuple, which contains the sum of the row and the sum of the column
// or just store the row, and then add the column

class NumMatrix {
    int[][] dp;
    // use this mathod to initialise the dp matrix
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            // just store 0 in dp
            dp = new int[1][1];
            return;
        }
        int row = matrix.length;
        int column = matrix[0].length;
        dp = new int[row][column+1];
        // we need one more column to store 0, otherwise we cannot calculate the sum from idx 0;
        for (int i = 0; i < row; i++){
            for (int j = 0; j <= column; j++){
                if (j == 0) dp[i][j] = 0;
                else dp[i][j] = dp[i][j-1] + matrix[i][j-1];
            }
        }
    }

    // O(n)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++){
            sum += dp[i][col2+1] - dp[i][col1];
        }
        return sum;
    }
}

// 11 ms, faster than 80.78%, 44.8 MB, less than 96.39%

//-------------------------------//

// but also we can store the sum value of a rectangle from (0,0) to (i,j)
// https://www.youtube.com/watch?v=PwDqpOMwg6U

class NumMatrix {
    private int[][] T;

    public NumMatrix(int[][] matrix) {
        int row = 0;
        int col = 0;
        if (matrix.length != 0) {
            row = matrix.length;
            col = matrix[0].length;
        }
        T = new int[row + 1][col + 1];
        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                T[i][j] = T[i - 1][j] + T[i][j - 1] + matrix[i - 1][j - 1] - T[i - 1][j - 1];
            }
        }
    }

    // O(1)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++;
        col1++;
        row2++;
        col2++;
        return T[row2][col2] - T[row1 - 1][col2] - T[row2][col1 - 1] + T[row1 - 1][col1 - 1];
    }
}

// 10 ms, faster than 100.00%, 45.6 MB, less than 24.34%

// or read the graph instruction here
// https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-304-range-sum-query-2d-immutable/

