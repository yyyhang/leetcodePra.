/*
Given a binary tree, return the bottom-up level order traversal of its nodes' values.
(ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
 */

// this question samilar to 102

// we can just solve like 102, and return a reverse list -- Collection.reverse
// or we can add the level at the beginning of the res list -- ArrayList.add(index, object)

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
            res.add(0,level);
        }
        return res;
    }
}
// 1ms, 80.93%

class Solution_2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<TreeNode>();
        List<List<Integer>> al=new ArrayList<List<Integer>>();
        if(root==null) return al;
        else {
            q.add(root);
            q.add(null);
            List<Integer> temp=new ArrayList<Integer>();
            while(q.size()>1) {
                TreeNode n=q.poll();
                if(n==null) {
                    // we add it to the beginning
                    al.add(0, temp);
                    temp=new ArrayList<Integer>();
                    q.add(null);
                }
                else {
                    temp.add(n.val);
                    if(n.left!=null) q.add(n.left);
                    if(n.right!=null) q.add(n.right);
                }
            }
            // for the last level
            al.add(0,temp);
        }
        return al;
    }
}
// 1ms, 80.93%. it seems the limit of BFS solution


// DFS
class Solution_DFS {
    List<List<Integer>> results = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        levelOrderBottom(root, 0);
        return results;
    }

    public void levelOrderBottom(TreeNode root, int level){
        if (root == null) return;
        // we add it to the beginning
        if (level >= results.size()) results.add(0, new ArrayList<>());
        // the index is dynamic, we need size(); bcz it start from 0, we need minus one
        // the order is reverse, we need minus the level
        results.get(results.size() - 1 - level).add(root.val);
        levelOrderBottom(root.left, level+1);
        levelOrderBottom(root.right, level+1);
    }
}

// 100%