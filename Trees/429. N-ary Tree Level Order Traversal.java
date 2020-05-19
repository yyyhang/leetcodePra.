/*
Given an n-ary tree, return the level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal,
each group of children is separated by the null value (See examples).

https://leetcode.com/problems/n-ary-tree-level-order-traversal/

Example 1:

Input: root = [1,null,3,2,4,null,5,6]
Output: [[1],[3,2,4],[5,6]]

Example 2:

Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

//using queue

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List <List<Integer>> res = new ArrayList <>();
        // it's a 2d list
        if (root == null) return res;
        Queue <Node> queue = new ArrayDeque <> ();
        // array deque: https://www.geeksforgeeks.org/arraydeque-in-java/
        Node curr = root;
        queue.add(curr);

        while (!queue.isEmpty()){
            int size = queue.size();
            // using this one to check if they are in the smae depth
            List <Integer> level = new ArrayList<>();
            while (size>0){
                curr = queue.poll();
                level.add(curr.val);
                // remember, here needs val, not node
                if (!curr.children.isEmpty()) queue.addAll(curr.children);
                // add all chilren into queue
                size--;
            }
            res.add(level);
        }
        return res;
    }
}
// for bfs we need size() for level order

// what the diff betw isEmpty() and empty()?

/*
recursive solution

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        levelOrder(root, 0, res);
        return res;
    }

    private void levelOrder(Node node, int depth, List<List<Integer>> res) {
        if (res.size() <= depth) res.add(new ArrayList<Integer>());
        res.get(depth).add(node.val);
        for (Node c : node.children) {
            if (c != null) levelOrder(c, depth + 1, res);
        }
    }
}

i still need to read this segments later

 */