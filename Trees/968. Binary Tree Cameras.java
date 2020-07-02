/*
Given a binary tree, we install cameras on the nodes of the tree.

Each camera at a node can monitor its parent, itself, and its immediate children.

Calculate the minimum number of cameras needed to monitor all nodes of the tree.



Example 1:


Input: [0,0,null,0,0]
Output: 1
Explanation: One camera is enough to monitor all nodes if placed as shown.
Example 2:


Input: [0,0,null,0,null,0,null,null,0]
Output: 2
Explanation: At least two cameras are needed to monitor all nodes of the tree. The above image shows one of the valid configurations of camera placement.

Note:

The number of nodes in the given tree will be in the range [1, 1000].
Every node has value 0.

https://leetcode.com/problems/binary-tree-cameras/
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

/*
similar to 337
we can use dp to store values in the children and current node
actually, we can use the same way to store two status: has camera or not

 */

/*
first i want to do this question by reverse the ans of 337, the min = nodes# - max

class Solution {
    int cnt;
    public int minCameraCover(TreeNode root) {
        cnt = 0;
        int[] res = new int[2];
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = left[0] + right[0] + 1;
        if (res[0] > res[1] && (!((left[0] < left[1]) && (right[0] < right[1])))) return cnt - res[0];
        cnt++;
        if (cnt == 1) return 1;
        return cnt - Math.max(res[0], res[1]);
}

    private int[] helper(TreeNode root) {
        if (root == null) return new int[2];
        int[] res = new int[2];
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = left[0] + right[0] + 1;
        cnt++;
        return res;
    }
}

but it is not right, e.g.

        0
       /
      1
       \
       0
      /
     0
      \
       1
      /
     0

 */

/*
dp:
if the curr node in respond status,  what is the optimal result
we need to find how to meet the following status for curr node
[State 0] Strict subtree: All the nodes below this node are covered, but not this node.
[State 1] Normal subtree: All the nodes below and including this node are covered, but there is no camera here.
[State 2] Placed camera: All the nodes below and including this node are covered, and there is a camera here (which may cover nodes above this node).

Once we frame the problem in this way, the answer falls out:

To cover a strict subtree, the children of this node must be in state 1.
To cover a normal subtree without placing a camera here, the children of this node must be in states 1 or 2, and at least one of those children must be in state 2.
To cover the subtree when placing a camera here, the children can be in any state.
*/

class Solution {
    final int nul =  9999;
    public int minCameraCover(TreeNode root) {
        int[] ans = helper(root);
        return Math.min(ans[1],ans[2]);
    }

    private int[] helper(TreeNode root) {
        if (root == null) return new int[]{0,0,nul};
        int[] res = new int[3];
        int[] left = helper(root.left);
        int[] right = helper(root.right);
        int minLeft = Math.min(left[1], left[2]);
        int minRight = Math.min(right[1], right[2]);
        res[0] = left[1] + right[1];
        res[1] = Math.min(left[2] + minRight, right[2] + minLeft);
        res[2] = Math.min(left[0], minLeft) + Math.min(right[0], minRight) + 1;
        return res;
    }
}
// 0 ms, faster than 100.00%

/*
Greedy:
If a node has children that are not covered by a camera, then we must place a camera here.
Additionally, if a node has no parent and it is not covered, we must place a camera here.
 */

class Solution {
    int ans;
    Set<TreeNode> covered;

    public int minCameraCover(TreeNode root) {
        ans = 0;
        covered = new HashSet();
        covered.add(null);

        dfs(root, null);
        return ans;
    }

    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            dfs(node.left, node);
            dfs(node.right, node);
            // cover here
            if (par == null && !covered.contains(node) ||
                    !covered.contains(node.left) ||
                    !covered.contains(node.right)) {
                ans++;
                covered.add(node);
                covered.add(par);
                covered.add(node.left);
                covered.add(node.right);
            }
        }
    }
}

//  2 ms, faster than 24.78%

// I guess this is bcz we need to push all things into stack when we are using recusion