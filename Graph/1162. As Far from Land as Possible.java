/*
Given an N x N grid containing only values 0 and 1,
where 0 represents water and 1 represents land,
find a water cell such that its distance to the nearest land cell is maximized and return the distance.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0)
and (x1, y1) is |x0 - x1| + |y0 - y1|.

If no land or water exists in the grid, return -1.



Example 1:

1 0 1
0 0 0
1 0 1

Input: [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation:
The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:

1 0 0
0 0 0
0 0 0

Input: [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation:
The cell (2, 2) is as far as possible from all the land with distance 4.


Note:

1 <= grid.length == grid[0].length <= 100
grid[i][j] is 0 or 1
 */


// we use bfs.
// Manhattan Distance
class Solution {
    public int maxDistance(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        // find all the lands
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                if(grid[i][j] == 1) queue.offer(new int[]{j,i});
                // why here is (j,i). In array, first one is height, and the second one is length
                // but in queue, we push the coordinates. we need to use this to add direction
                // but still feel confused why it matters. afterall, it needs to go all dirctions
                // if we use wrong order of x,y, it will change anther point
                // e.g. we might change [0,2] instead [2,0]
                // so, if we want to change order here , we need to change all order of x and y later
            }
        }
        // if they are all 0s or all 1s
        if(queue.size() == 0 || queue.size() == grid.length * grid[0].length) return -1;

        int cnt = 0;
        // we define the direction
        int[][] positions = {{1,0},{-1,0},{0,1},{0,-1}};
        // bfs
        while(queue.size()>0){
            int size = queue.size();
            // for this level bfs
            while(size>0){
                size--;
                int[] land = queue.poll();
                // we visit this point all neighbours
                for(int[] position: positions){
                    int x = land[0] + position[0], y = land[1] + position[1];
                    // not exceed tje bound and not visited
                    if (x>=0 && x<grid[0].length && y>=0 && y<grid.length && grid[y][x] != 1){
                        // they will visit all direction. but if we not change here, it will turn 0 to 1 of a wrong point
                        grid[y][x] = 1;
                        queue.offer(new int[]{x,y});
                    }
                }
            }
            cnt++;
        }
        // this is bcz for the last node, we still will try to bfs it.
        return cnt-1;
    }
}
// 12 ms 86%

/*
1. the way of how to move to other directions is great
 */

// https://leetcode.jp/leetcode-1162-as-far-from-land-as-possible-%E8%A7%A3%E9%A2%98%E6%80%9D%E8%B7%AF%E5%88%86%E6%9E%90/

class Solution {

    int[][] moves = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public int maxDistance(int[][] grid) {
        int cells = grid.length * grid[0].length;
        for(int[] gr : grid)
            for(int g : gr)if(g==1)cells--;
        if(cells==grid.length * grid[0].length || cells==0)return -1;
        int it = 1;
        while(cells>0){
            for(int r = 0;r<grid.length;r++){
                for(int c=0;c<grid[r].length;c++){
                    if(grid[r][c] == it){
                        for(int[] move : moves){
                            int rT = r+move[0];
                            int cT = c+move[1];
                            if(rT<0 || cT<0 ||rT==grid.length||cT==grid[rT].length || grid[rT][cT]!=0)continue;
                            grid[rT][cT]=it+1;
                            cells--;
                        }
                    }
                }
            }
            it++;
        }
        return it-1;
    }
}
// 9ms, 95%