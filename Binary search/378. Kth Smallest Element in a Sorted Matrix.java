/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order,
find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 111, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
 */

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        // use length or size()
        int n = matrix.length - 1;
        // find the smallest one
        int left = matrix[0][0], right = matrix[n][n];
        while (left < right) {
            int mid = left + (right - left)/2;
            int cnt = helper(mid, n, matrix);
            // must use >= here, otherwise even the left == k, we still need to add one
            if (cnt >= k) right = mid;
            else left = mid + 1;
        }
        // return mid; // cannot find this symbol
        return left;
    }

    private int helper(int mid, int n, int[][] matrix) {
        int x = 0, y = n;
        int cnt = 0;
        while (x <= n && y >= 0) {
            if (matrix[y][x] <= mid) {
                // bcz y count from 0, so we need y + 1
                cnt += y + 1;
                x++;
            } else {
                y--;
            }
        }
        return cnt;
    }
}

// 0 ms, faster than 100.00%

// for questions like this: find a number, try to think if we can use binary search

// it will not return any number not in the matrix
// since the binary searh will locate the number in the critical point, witch will definately in the matrix
//you can proof by contradiction