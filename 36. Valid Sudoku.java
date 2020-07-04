/*
vDetermine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

A partially filled sudoku which is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
Example 2:

Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.
 */

class Solution {
    // the number from 0 - 9, so we need 10 place for it
    int[][] rows = new int[9][10];
    int[][] cols = new int[9][10];
    int[][] box = new int[9][10];

    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++){
                char d = board[i][j];
                if (d != '.') {
                    int num = d - '0';
                    if (!place(num, i, j)) return false;
                }
            }
        }
        return true;
    }

    private boolean place(int num, int row, int col) {
        int idx = (row / 3) * 3 + (col / 3);
        if (rows[row][num] + cols[col][num] + box[idx][num] != 0)
            return false;
        else{
            rows[row][num]++;
            cols[col][num]++;
            box[idx][num]++;
            return true;
        }
    }
}

// 2 ms, faster than 84.30%

class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] grid = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    // that's why we only need 9 place to store number
                    int val = (int)(board[i][j] - '1');
                    int idx = 3 * (i / 3) + j/3;
                    if (rows[i][val] || cols[j][val] || grid[idx][val]) return false;
                    rows[i][val] = true;
                    cols[j][val] = true;
                    grid[idx][val] = true;
                }
            }
        }
        return true;
    }
}