/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 */

// same as 51

class Solution {
    int[] rows;
    int[] cols;
    int[] left;
    int[] right;
    int ans;
    int[][] puzzle;

    public int totalNQueens(int n) {
        rows = new int[n];
        cols = new int[n];
        left = new int[n+n];
        right = new int[n+n];

        next(0, n);
        return ans;
    }

    private void next(int i, int n) {
        for (int j = 0; j < n; j++) {
            if (rows[i] + cols[j] + left[i+j] + right[(n-1)+(j-i)] == 0) {
                rows[i]++;
                cols[j]++;
                left[i+j]++;
                right[(n-1)+(j-i)]++;
                if (i + 1 < n) next(i + 1, n);
                    // else we find the solution
                else {
                    ans++;
                }
                // backtracking
                rows[i]--;
                cols[j]--;
                left[i+j]--;
                right[(n-1)+(j-i)]--;
            }
        }
    }
}

// 1 ms, faster than 83.78%