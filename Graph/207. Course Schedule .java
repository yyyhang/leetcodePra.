/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?



Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.


Constraints:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
1 <= numCourses <= 10^5
 */

/*
This problem is equivalent to finding if a cycle exists in a directed graph.
If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
(similar to detect the deadlock)

 */

// https://zxi.mytechroad.com/blog/graph/leetcode-207-course-schedule/
// the point is we visit a node are in a state of searching

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // we create the graph
        // ================== //
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; ++i)
            graph.add(new ArrayList<Integer>());

        for (int i = 0; i < prerequisites.length; ++i) {
            int course = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];
            graph.get(course).add(prerequisite);
        }
        // =================== //

        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; ++i)
            if (dfs(i, graph, visited)) return false;

        return true;
    }
    // o  unknown, 1 visiting, 2 visited
    private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited) {
        // == 1, we visit some node we are searching, that's a cycle
        if (visited[curr] == 1) return true;
        if (visited[curr] == 2) return false;

        visited[curr] = 1;

        for (int next : graph.get(curr))
            if (dfs(next, graph, visited)) return true;
        // after traversal all nodes, if when we back to the stack and found the crr is visited
        // we need to return ture. bcz there is a cycle
        // otherwise we change it to cisited and return false
        // the first element is the last marked as visited
        visited[curr] = 2;
        return false;
    }

}
//  2 ms, faster than 99.24%