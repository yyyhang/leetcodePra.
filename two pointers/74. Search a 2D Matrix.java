/*
Write an efficient algorithm that searches for a value in an m x n matrix.
This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true

Example 2:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
 */

// we search the 1d first time, then search the 2d
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        // remember, if start from 0, we need length - 1
        int lo = 0, hi = matrix.length-1;
        int i = 0, j = matrix[0].length-1;
        while (lo <= hi) {
            int mi = lo + (hi - lo)/2;
            if (matrix[mi][0] > target) hi = mi - 1;
            else lo = mi + 1;
        }
        // in case the target less than all numbers
        if (hi < 0) return false;
        while(i <= j) {
            int m = i + (j - i)/2;
            if (matrix[hi][m] > target) j = m - 1;
            else i = m + 1;
        }
        return matrix[hi][j] == target;
    }
}
// 0ms, 100%

/*
for binary search, try to consider the flowing bounds:

bigger than all elements, less than all elements, not in the array.

and think about how to change lo and hi, and will it skip the answer if using add one to pointer
or will it cannot be changed if the pointer become the middle.
 */


// treat the 2D as a 1D array
// for instance, for the previous eaxmple, we have index from 0 to 11, and the middle is 5
// so, if the number of the columns is n, for the middle, its position will be row: 5/4 = index 1 row
// and its column will be 5%4 = index 1 column.

class Solution_2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int start = 0;
            int end = matrix[0].length * matrix.length - 1;
            while (start <= end) {
                int midIndex = start + (end - start) / 2;
                int mid = matrix[midIndex / matrix[0].length][midIndex % matrix[0].length];
                if (mid == target) {
                    return true;
                } else if (mid < target) {
                    start = midIndex + 1;
                } else {
                    end = midIndex - 1;
                }
            }
        }
        return false;
}