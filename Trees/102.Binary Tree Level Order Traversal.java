/*
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
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

// this question samilar to 429
// for this question, we can use bfs or dfs; for dfs, we need depth

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        // memorize this. this is for the bfs level order
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size > 0) {
                TreeNode curr = queue.poll();
                level.add(curr.val);
                // push the children to the queue
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
                size--;
            }
            res.add(level);
        }
        return res;
    }
}

// 1ms, 58.57%

// this is a good solution to memorize
class Solution_sentinel {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        List<List<Integer>> al=new ArrayList<List<Integer>>();
        if(root==null) return al;
        else {
            q.add(root);
            // we use a sentinel here.
            q.add(null);
            List<Integer> temp=new ArrayList<Integer>();
            // every time when it polls a sentnel, we know this level is ended;
            while(q.size()>1) {
                TreeNode n=q.poll();
                if(n==null) {
                    al.add(temp);
                    temp=new ArrayList<Integer>();
                    q.add(null);
                }
                else {
                    temp.add(n.val);
                    if(n.left!=null) q.add(n.left);
                    if(n.right!=null) q.add(n.right);
                }
            }
            al.add(temp);
        }
        return al;
    }
}
// 0ms, 100%


//DFS
class Solution_DFS {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();
        levelOrderDfsRec(root, 0, levels);
        return levels;
    }

    public void levelOrderDfsRec(TreeNode root, int i, List<List<Integer>> levels){
        if(root == null) return;
        // we add from 0, 1, 2 ... so the size means the level in the list
        if(levels.size() <= i) levels.add(new LinkedList<Integer>());
        List<Integer> myLevel = levels.get(i);
        myLevel.add(root.val);
        levelOrderDfsRec(root.left, i+1, levels);
        levelOrderDfsRec(root.right, i+1, levels);
    }
}
// 100%
