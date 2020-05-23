/*
Given a triangle, find the minimum path sum from top to bottom.
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space,
where n is the total number of rows in the triangle.
 */

// convert it to this:
// [[2]                 [[2]
//  [3, 4]]              [5, 6]
//  [6, 5, 7]]           [11, 10, 11]
//  [4, 1, 8, 3]]        [15, 11, 18, 14]]

class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // opt[i][j] is the path sum start from one node of bottom layer to node (i,j)
        int[][] opt = new int[n+1][n+1];
        for(int i = n-1;i>=0;i--){
            for(int j=0;j<=i;j++){
                opt[i][j] = Math.min(opt[i+1][j],opt[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return opt[0][0];
    }
}