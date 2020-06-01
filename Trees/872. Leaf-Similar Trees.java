/*
Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.



For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.



Constraints:

Both of the given trees will have between 1 and 200 nodes.
Both of the given trees will have values between 0 and 200
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

// for this question, i think we can get the leaf nodes of these two trees. then compare them;
// but it seems not effecient, bcz even if the first leaf nodes are different, we still need to traversal all nodes
// how can we compare leaf nodes one by one? maybe we can traversal the whole root1 at first, then compare with root2 one by one


class Solution {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> result1 = new LinkedList<>();
        List<Integer> result2 = new LinkedList<>();
        leafSequence(root1,result1);
        leafSequence(root2,result2);
        return result1.equals(result2);
    }

    private void leafSequence(TreeNode root, List<Integer> result){
        if(root!=null){
            if(root.left==null && root.right ==null)
                result.add(root.val);
            leafSequence(root.left, result);
            leafSequence(root.right, result);
        }
    }
}

// 0ms, 100%; 39.29% space
// O(n1 + n2)



// compare root2 one by one
class Solution_2 {
    public LinkedList<Integer> queue;                //Queue to store leaf nodes from left to right

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        queue = new LinkedList<Integer>();
        fetchLeaves(root1);                                          //fill the queue with leaf nodes
        return verifyLeaves(root2) && queue.size() == 0;             //if all leaves are matched and queue is also empty
    }

    public void fetchLeaves(TreeNode root) {
        if(root != null){
            if(root.left == null && root.right == null) queue.add(root.val); //Leaf node
            else{
                fetchLeaves(root.left);
                fetchLeaves(root.right);
            }
        }
    }

    public boolean verifyLeaves(TreeNode root) {
        if(root != null){
            if(root.left == null && root.right == null){
                // using try in case the queue is empty
                try{
                    return root.val == queue.poll();             //compare with leaf nodes of queue
                }
                catch(Exception e){
                    return false;
                }
            }
            else
                return verifyLeaves(root.left) && verifyLeaves(root.right);
        }
        return true;
    }
}

// 100%, 39.29% space