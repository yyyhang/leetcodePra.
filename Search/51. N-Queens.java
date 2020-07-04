/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */

// the trick point is how to represent hypotenuse

/*
we can get the idx of hypotenuse by x+y and x-y+(n-1)

https://zxi.mytechroad.com/blog/searching/leetcode-51-n-queens/
 */

/*
tips:
1. how to fill an array
2. how to convert btwn char and string
 */

class Solution {
    int[] rows;
    int[] cols;
    int[] left;
    int[] right;
    List<List<String>> ans;
    char[][] puzzle;

    public List<List<String>> solveNQueens(int n) {
        rows = new int[n];
        cols = new int[n];
        left = new int[n+n];
        right = new int[n+n];

        ans = new ArrayList<>();
        puzzle = new char[n][n];
        // we fill the array,
        // https://stackoverflow.com/questions/12573938/the-arrays-fill-method-causes-an-exception
        // Arrays.fill expects a single-dimensional array, we cannot pass in a jagged array.
        for (int x = 0; x < n; x++)
            Arrays.fill(puzzle[x],'.');
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
                puzzle[i][j] = 'Q';
                if (i + 1 < n) next(i + 1, n);
                // else we find the solution
                else {
                    // convert char to array
                    List<String> s = new ArrayList<>();
                    for(char[] ch : puzzle)
                        s.add(String.valueOf(ch));
                    ans.add(s);
                }
                // backtracking
                rows[i]--;
                cols[j]--;
                left[i+j]--;
                right[(n-1)+(j-i)]--;
                puzzle[i][j] = '.';
            }
        }
    }
}

// 3 ms, faster than 77.14%