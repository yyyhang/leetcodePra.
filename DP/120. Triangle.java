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

// from bottom to the top
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // arr2d.size() to get the height of this array.
        int n = triangle.size();
        // opt[i][j] is the path sum start from one node of bottom layer to node (i,j)
        // we use one more row and one more column to avoid checking the bound
        int[][] opt = new int[n+1][n+1];
        // we start from the last row, its result come from the [0,0,0...]
        for(int i = n-1;i>=0;i--){
            for(int j=0;j<=i;j++){
                opt[i][j] = Math.min(opt[i+1][j],opt[i+1][j+1]) + triangle.get(i).get(j);
            }
        }
        return opt[0][0];
    }
}

// 2 ms, 74%; 39MB, 10.20%


// in this solution we update the array in the original array
class Solution_2 {
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            for (int j =0; j <= i; j++){
                // for the start point [0,0]
                if (i == 0 && j == 0) {
                    // but how can i change it to reach the line 68 without this condition?
                    if (n == 1) min = triangle.get(i).get(j);
                    continue;
                }
                // update the first column. it can only have one to sum
                if (j == 0) triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j));
                    // for the last column
                else if (j == i) triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j-1));
                else triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i-1).get(j), triangle.get(i-1).get(j-1)));

                if (i == triangle.size() - 1) min = Math.min(triangle.get(i).get(j), min);
            }
        }
        return min;
    }
}
// 5ms 21.29%, 39.6MB 8.16%

/*
1. for Integer, you cannot just use Integer[i][j]. instead, you need to use Integer.get(i).get(j)
2. check how these solutions dealing with bound

3. triangle.get(i).get(j) += triangle.get(i-1).get(j) is wrong, bcz will return a value.
    we cannot assign a value to another value. we can only assign a value to a variable
4. triangle.add(Arrays.asList(4,1,8,3));
 */