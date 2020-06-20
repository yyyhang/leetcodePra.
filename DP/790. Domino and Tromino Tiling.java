/*
We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.

XX  <- domino

XX  <- "L" tromino
X
Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7. // this means this question is dp , and it will be vary large


(In a tiling, every square must be covered by a tile. Two tilings are different if and only
if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings
has both squares occupied by a tile.)

Example:
Input: 3
Output: 5
Explanation:
The five different ways are listed below, different letters indicates different tiles:
XYZ XXZ XYY XXY XYY
XYZ YYZ XZZ XYY XXY
Note:

N  will be in range [1, 1000].
 */

// https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-790-domino-and-tromino-tiling/

class Solution {
    public int numTilings(int N) {
        int kMod = 1000000007;
        // set up a 2d array
        long[][] dp = new long[N+1][3];
        dp[0][0] = dp[1][0] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 2][0] + dp[i - 1][1] + dp[i - 1][2]) % kMod;
            dp[i][1] = (dp[i - 2][0] + dp[i - 1][2]) % kMod;
            dp[i][2] = (dp[i - 2][0] + dp[i - 1][1]) % kMod;
        }
        return (int)dp[N][0];
    }
}

// 2 ms, faster than 35.26%


/*
define: dp[i] ways to completely covert the i*2 board.

dp[0] = 1 # {}
dp[1] = 1 # {|}
dp[2] = 2 # {||, =}
dp[3] = 5 # {|||, |=, =|, ⌊⌉, ⌈⌋} = dp[2] ⊗ {|} + dp[1] ⊗ {=} + dp[0] ⊗ {⌊⌉, ⌈⌋}
dp[4] = 11 # dp[3] ⊗ {|} + dp[2] ⊗ {=} + dp[1] ⊗ {⌊⌉, ⌈⌋} + dp[0] ⊗ {⌊¯⌋,⌈_⌉}
dp[5] = 24 # dp[4] ⊗ {|} + dp[3] ⊗ {=} + 2*(dp[2] + dp[1] + dp[0])
...
dp[n] = dp[n-1] + dp[n-2] + 2*(dp[n-3] + ... + dp[0])
      = dp[n-1] + dp[n-3] + [dp[n-2] + dp[n-3] + 2*(dp[n-4] + ... + dp[0])]
      = dp[n-1] + dp[n-3] + dp[n-1]
      = 2*dp[n-1] + dp[n-3]
 */

class Solution {
    public int numTilings(int N) {
        if (N == 0) return 1;
        int kMod = 1000000007;
        long[] dp = new long[N+1];
        dp[0] = dp[1] = 1;
        if (N > 1) dp[2] = 2;
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i-3] + dp[i-1] * 2) % kMod;
        }
        return (int)dp[N];
    }
}

// 0 ms, faster than 100.00%