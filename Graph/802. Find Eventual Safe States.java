/*
In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.
If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.
More specifically, there exists a natural number K so that for any choice of where to walk,
we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.
The graph is given in the following form: graph[i] is a list of labels j such that (i, j)
is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.

Illustration of graph

Note:

graph will have length at most 10000.
The number of edges in the graph will not exceed 32000.
Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
 */

// the input is put the nodes to the corespond idx node

/*
1. we need to build the graph and connect the edge
2. using topology sorting to check if there is a cycle
 */

class Solution {
    List<Integer> ans;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<List<Integer>> g = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            g.add(new ArrayList<Integer>());
            for (int node : graph[i]) {
                g.get(i).add(node);
            }
        }
        ans = new ArrayList<>();
        int[] visited = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (topo(g, i, visited)) continue;
            ans.add(i);
        }
//        int[] res = new int[ans.size()];
//        for (int i = 0; i < ans.size(); i++){
//            res[i] = ans.get(i);
//        }
        return ans;
    }

    public boolean topo(List<List<Integer>> g, int i, int[] visited) {
        if (visited[i] == 1) return true;
        if (visited[i] == 2) return false;

        visited[i] = 1;
        for (int next : g.get(i)) {
            if(topo(g, next, visited)) return true;
        }
        visited[i] = 2;
        return false;
    }
}

// 13 ms, faster than 40.84%

// =================================== //

class Solution {
    List<Integer> ans;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        // actually, there is no need to transfer the graph to ArrayList
        ans = new ArrayList<>();
        int[] visited = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (topo(graph, i, visited)) continue;
            ans.add(i);
        }
        return ans;
    }

    public boolean topo(int[][] g, int i, int[] visited) {
        if (visited[i] == 1) return true;
        if (visited[i] == 2) return false;

        visited[i] = 1;
        for (int next : g[i]) {
            if(topo(g, next, visited)) return true;
        }
        visited[i] = 2;
        return false;
    }
}
// 4 ms, faster than 96.63%