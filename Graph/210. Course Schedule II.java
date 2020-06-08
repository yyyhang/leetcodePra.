/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering
of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is
impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */

// it just samilar to 207, need to add the order though.

// first we need to build a graph
// then we start from i = 0 to traversal all nodes.
// when a nodes has no node to visit, or all next nodes are visited, we push this node to the begin of the answer
// as there is no node to visit next, it can be the last to visit
// if the next node marked as visiting, that means we visit a node in status of searching, which form a cycle.

class Solution {
    List<Integer> ans = new ArrayList<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        // build nodes
        for (int i = 0; i < numCourses; ++i)
            graph.add(new ArrayList<Integer>());
        // build edges
        for (int i = 0; i < prerequisites.length; ++i) {
            int course = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];
            graph.get(prerequisite).add(course);
        }
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; ++i)
            if (dfs(i, graph, visited)) return new int[0]; //return null;
            // else ans.add(0, i);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = ans.get(i);
        }
        return res;
    }


    private boolean dfs(int curr, ArrayList<ArrayList<Integer>> graph, int[] visited) {
        if (visited[curr] == 1) return true;
        if (visited[curr] == 2) {
            //ans.add(0, curr);     // we cannot add it here since we had add it to ans
            return false;
        }

        visited[curr] = 1;

        for (int next : graph.get(curr))
            if (dfs(next, graph, visited)) return true;
        visited[curr] = 2;
        // all nodes will be add exactly once here
        ans.add(0, curr);
        return false;
    }
}

//  2 ms, faster than 99.73%