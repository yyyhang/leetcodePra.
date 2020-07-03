/*
Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


A sudoku puzzle...


...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9

https://leetcode.com/problems/sudoku-solver/

 */

class Solution {
    // we define the box size here
    final int n = 3;
    // then we define the number of size
    final int N = n * n;
    // we defien the array to record all the elements we have already used
    int[][] rows = new int[N][N+1];
    int[][] cols = new int[N][N+1];
    // we have nine boxes
    int[][] box = new int[N][N+1];
    // set the board as gloable
    char[][] board;

    boolean solved = false;

    public void solveSudoku(char[][] board) {
        // we set the class tribute
        this.board = board;
        // we go though the whole cells, and record all given number
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                char num = board[i][j];
                if (num != '.') {
                    // if this one has already been given
                    int d = Character.getNumericValue(num);
                    placeNumber(d, i, j);
                }
            }
        }
        // then we start to try to solve this Sudoku from (0, 0)
        backtrack(0, 0);
    }

    private void placeNumber(int d, int row, int col) {
        // find the index of the box
        int idx = (row / n) * n + col / n;
        // we find the row/col/box, and set the corresponding element as 1 (full that bucket)
        // we use this way to find if we used this element or not. we can use other way though
        rows[row][d]++;
        cols[col][d]++;
        box[idx][d]++;
        // change the place as correspond number
        board[row][col] = (char)(d + '0');
    }

    private void removeNumber(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        rows[row][d]--;
        cols[col][d]--;
        box[idx][d]--;
        // change the place as correspond number
        board[row][col] = '.';
    }

    private void backtrack(int row, int col) {
        if (board[row][col] == '.') {
            // iterate over all numbers from 1 to 9
            for (int d = 1; d < 10; d++) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumbers(row, col);
                    if (!solved) removeNumber(d, row, col);
                }
            }
        }
        // if this place has already given, we try the next one
        else placeNextNumbers(row, col);
    }

    public void placeNextNumbers(int row, int col) {
        // if we reach to the last slot, it means we find the solution
        if ((col == N-1) && (row == N-1)) solved = true;
        else {
            // we reach to the last col, we need to change to another row
            if (col == N-1) backtrack(row+1, 0);
                // else ,we try the next col
            else backtrack(row, col + 1);
        }
    }

    private boolean couldPlace(int d, int row, int col) {
        int idx = (row / n) * n + col / n;
        // if not use yet, all numbers should be 0
        return rows[row][d] + cols[col][d] + box[idx][d] == 0;
    }
}

//  10 ms, faster than 63.55%

// https://leetcode-cn.com/problems/sudoku-solver/solution/jie-shu-du-by-leetcode/