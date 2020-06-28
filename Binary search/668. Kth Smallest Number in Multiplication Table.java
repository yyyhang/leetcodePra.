/*
Nearly every one have used the Multiplication Table.
But could you find out the k-th smallest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k,
you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output: 3
Explanation:
The Multiplication Table:
1	2	3
2	4	6
3	6	9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).
Example 2:
Input: m = 2, n = 3, k = 6
Output: 6
Explanation:
The Multiplication Table:
1	2	3
2	4	6

The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
Note:
The m and n will be in the range [1, 30000].
The k will be in the range [1, m * n]
 */

// similar to 378

class Solution {
    public int findKthNumber(int m, int n, int k) {
        int left = 1, right = n*m;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cnt = helper(m, n, k, mid);
            if (cnt >= k) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private int helper(int m, int n, int k, int mid) {
        int x = 1, y = n;
        int cnt = 0;
        while (x <= m && y >= 0) {
            if (x*y <= mid) {
                cnt += y;
                x++;
            } else {
                y--;
            }
        }
        return cnt;
    }
}

// 9 ms, faster than 97.70%
