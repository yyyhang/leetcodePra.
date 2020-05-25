/*
There are N students in a class. Some of them are friends, while some are not.
Their friendship is transitive in nature. For example, if A is a direct friend of B,
and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle
is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1,
then the ith and jth students are direct friends with each other, otherwise not. And you have to output
the total number of friend circles among all the students.

Example 1:
Input:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input:
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.
 */

// it's samilar to the question 200: islands

// for this question, the graph is a symmetry graph
// focus more on the people node rather than graph.
class Solution {
    private int n;

    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) return 0;
        n = M.length;
        // this mark all the person
        // if ith person has been visited, the visited[i] = 1
        int[] visited = new int[n];

        int res = 0;
        // bcz the first column represents the persons, we can only traversal this column
        // if the person is visited, we don't need to visit them again
        for (int i = 0; i < n; i++){
            if (visited[i] == 0){
                dfs (M, i, visited);
                res++;
            }
        }
        return res;
    }
    private void dfs(int[][] M, int i, int[] visited){
        if (visited[i] == 1) return;

        visited[i] = 1;
        for (int j = 0; j < n; j++){
            // visit all possible friend. using visited to check
            // if they are firend and this friend is not visited yet, we dfs this firend
            if (M[i][j] == 1 && visited[j] == 0){
                dfs(M, j, visited);
            }
        }
    }
}
//0ms, 100%


// or use this way:
// https://zxi.mytechroad.com/blog/graph/leetcode-547-friend-circles/

class Solution_2 {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        if (n == 0) return 0;

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (M[i][i] == 0) continue;
            ++ans;
            dfs(M, i, n);
        }
        return ans;
    }

    private void dfs(int[][] M, int curr, int n) {
        for (int i = 0; i < n; ++i) {
            // if they are not friend, just continue
            if (M[curr][i] == 0) continue;
            // otherwise, mark them are not friend
            // however, the 2d access will be a little slower
            // 1d array can be loaded to the buff one time
            M[curr][i] = M[i][curr] = 0;
            dfs(M, i, n);
        }
    }
}
// 3ms, 33.71%

// or we can use union find