/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1

Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        // Nothing at all: this is the null array.
        // An empty cup: this is the empty list case.
        int num = 0;
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                if (grid[i][j] == '1'){
                    num += dfs(grid,i,j);
                    // num += dfs(grid,i,j);
                }
            }
        }
        return num;
    }

    private int dfs(char[][] grid, int i, int j){
        if(i<0 || i >= grid.length || j<0 || j>= grid[i].length || grid[i][j] == '0'){
            return 0;
        }
        // dfs, sink this node at first
        grid[i][j] = '0';
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
        return 1;
        // I only care about the return value of the first one, other node I just need them to sink.
        // or i don't even need any return value, meeting one i just need to add one. check line 35
    }
}

// O(mn)