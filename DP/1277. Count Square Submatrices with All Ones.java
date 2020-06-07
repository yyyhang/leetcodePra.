/*
Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.



Example 1:

Input: matrix =
[
  [0,1,1,1],
  [1,1,1,1],
  [0,1,1,1]
]
Output: 15
Explanation:
There are 10+ squares of side 1.
There are 4 squares of side 2.
There is  1 square of side 3.
Total number of squares = 10 + 4 + 1 = 15.
Example 2:

Input: matrix =
[
  [1,0,1],
  [1,1,0],
  [1,1,0]
]
Output: 7
Explanation:
There are 6 squares of side 1.
There is 1 square of side 2.
Total number of squares = 6 + 1 = 7.


Constraints:

1 <= arr.length <= 300
1 <= arr[0].length <= 300
0 <= arr[i][j] <= 1
 */

/*
it is samilar to question 221, for each element, we store its maxLenth in dp[]
and for each element, the maxNumber can add the length times (i.e. we have such numbers of squares for this element)
 */

class Solution {
    public int countSquares(int[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxLen = 0, prev = 0;
        int sum = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int tmp = dp[j];
                // if curr node == '1'
                if(matrix[i-1][j-1] == 1) {
                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), prev) + 1;
                } else
                    dp[j] = 0;
                sum += dp[j]
                // for (int k = 1; k <= dp[j]; k++) sum++;
                prev = tmp;
            }
        }
        return sum;
    }
}

// 5 ms, faster than 96.97%
// O(n*m)