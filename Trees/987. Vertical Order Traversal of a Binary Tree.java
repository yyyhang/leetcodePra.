/*
Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y),
its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity,
whenever the vertical line touches some nodes, we report the values of the nodes in order
from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of
the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.
Every report will have a list of values of nodes.

Example 1:

Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
Example 2:



Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.


Note:

The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// but maybe i can use recursive way to travel it


// traversal all nodes via BFS, and add their positon to a hash table. then output

class Solution {
    // create a class to store the position and node
    class Node{
        TreeNode root;
        int x;
        int y;
        Node(TreeNode root, int x, int y){
            this.root = root;
            this.x = x;
            this.y = y;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();  // can i use another way to crate a 2d array?
        if (root == null) return res;
        Map<Integer, List<Node>> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(root, 0 , 0));     // diff betw add and offer
        // when element can not be added to collection the add method throws an exception and offer doesn't.
        // since we need to output form the min to max of the x position
        int minx = 0;
        int maxx = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            minx = Math.min(minx, cur.x);
            maxx = Math.max(maxx, cur.x);
            map.putIfAbsent(cur.x, new ArrayList<>());      // what can replace this one??
            map.get(cur.x).add(cur);
            // BFS: we push all the children to the queue
            if (cur.root.left != null){
                queue.offer(new Node(cur.root.left, cur.x-1, cur.y-1));
            }
            if (cur.root.right != null){
                queue.offer(new Node(cur.root.right, cur.x+1, cur.y-1));
            }
        }
        int index = 0;
        for(int i = minx; i<=maxx; i++){
            // what is the Collection ??
            // It works similar to java.util.Arrays.sort() method but it is better then as it can
            // sort the elements of Array as well as linked list, queue and many more present in it.

            // check if they are same position
            Collections.sort(map.get(i), (a,b) ->{
                if (a.y == b.y){
                    return a.root.val - b.root.val;
                }
                return b.y - a.y;
            });
            res.add (new ArrayList<>());
            for (Node node : map.get(i)){
                res.get(index).add(node.root.val);
            }
            index++;
        }
        return res;
    }
}

// https://www.youtube.com/watch?v=AUQL0R-ibEw

/*
If you need add/remove of the both ends, ArrayDeque is significantly better than a linked list. Random access each element is also O(1) for a cyclic queue.

The only better operation of a linked list is removing the current element during iteration.



 */