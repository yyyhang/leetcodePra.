/*
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.
 */

// for this quesrion, the first solution we can use the binary search
// if there are two middle numbers, we need the less one
class Solution {
    public int mySqrt(int x) {
        int i = 1;
        int j = x;
        while (i <= j) {
            // bcz multiplying faster than divide, we use times here (maybe).
            // but it may overflow, we need to use the long type for the middle
            long m = i + (j - i) / 2;
            // it seems wahtever i add one/ minus one or not, it dosen't matter
            // so just consider when they near each other or they are same to dicide the bound
            if (m*m > x) j = (int) m - 1;
            else i = (int) m + 1;
        }
        // consider if i == j == ans, we still need i + 1, so we return j.
        return j;
    }
}
// 100%

// or we use newton's method

class Solution_Newton {
    public int mySqrt(int a) {
        long x = a;
        while (x * x > a)
            x = (x + a / x) / 2;
        return (int)x;
    }
}

// 100%

// https://zxi.mytechroad.com/blog/math/leetcode-69-sqrtx/