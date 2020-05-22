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

// This is a solution using math:
// we need walk m+n-2 steps. and choose m-1 as vertical/ horizontal.
// bcz we can just move to the next grid, it is not permutation
class Solution {
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

