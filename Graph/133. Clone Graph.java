/*
Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}


Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed).
For example, the first node with val = 1, the second node with val = 2, and so on.
The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph.
Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1.
You must return the copy of the given node as a reference to the cloned graph.

Example 1:


Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
Example 2:


Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.
Example 4:


Input: adjList = [[2],[1]]
Output: [[2],[1]]


Constraints:

1 <= Node.val <= 100
Node.val is unique for each node.
Number of Nodes will not exceed 100.
There is no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
 */



/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

// it is a connected graph, no single node

// for this problem, we have two ways to copy it
// the first one is we copy a node. and at the meantime, we create all the neighbours, then let the node points to them
// and we chage to a new node that we never travasal before

// another way is use DFS to all the sub nodes

// neighbours is a list
// first we copy nodes, then travesal nodes to get connections

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        HashMap <Node, Node> map = new HashMap<>();
        // copy value, and let the neighbour to be empty at first
        Node newNode = new Node(node.val, new ArrayList<>());
        map.put(node,newNode);
        // using BFS with queue
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            // travsal all neighbors and put the nodes haven't travsaled into the map and queue
            for(Node nei: cur.neighbors){
                if(!map.containsKey(nei){
                    map.put(nei, new Node(nei.val, new ArrayList<>()));
                    queue.add(nei);
                }
                // with the hashmap we can easily add the new neighours to the new nodes
                map.get(cur).neigghbors.add(map.get(nei));
            }
        }
        return newNode;
    }
}

class SolutionDFS {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        return dfs(node, map);
    }
    // this function can travsal all the neighbours and add the neighour nodes to the neighbors
    private Node dfs(Node node, HashMap<Node, Node> map) {
        if (map.containsKey(node)) return map.get(node);
        Node clone = new Node(node.val, new ArrayList<Node>());
        map.put(node, clone);
        for (Node nb: node.neighbors) {
            clone.neighbors.add(dfs(nb, map));
            // if the node in the map, just add it to the neighbors
            // otherwise traval all the neighbors of the neighbors until traval all of them
        }
        return clone;
    }
}
