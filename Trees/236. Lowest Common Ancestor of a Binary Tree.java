/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia:
“The lowest common ancestor is defined between two nodes p and q as the lowest node in
T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]




Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.
Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.


Note:

All of the nodes' values will be unique.
p and q are different and both values will exist in the binary tree.
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// is there any dupicate value in this tree?

/*
what i wanna is dfs. if there is a node.val == target, set it as true.
while visting or visited, if we need to set it true, and find it has already set as true, we back this node
 */

class Solution {
    TreeNode ans;
    //TreeNode[] ans = new TreeNode[1];

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return ans[0];
    }
    // 0 == unknow/false, 1 == true, 2 == found
    private int dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return 0;
        // we search if there are nodes in the subtrees
        int flag1 = dfs(root.left, p, q);
        int flag2 = dfs(root.right, p, q);

        if (flag1 == 2 || flag2 == 2) return 2;
        // if we have nodes in both children, curr node is ans, and we no need to traversal others
//        if (flag1 == 1 && flag2 == 1) {
//            ans[0] = root;
//            return 2;
//        }
        // if this node == target
        int f3 = (root == p || root == q) ? 1 : 0;
        // if one child contains the target and the curr node is another target
        // a better way:
        if (flag1 + flag2 + f3 >= 2) {
        // if ((flag1 == 1 || flag2 == 1) && f3 == 1){
            ans = root;
            // ans[0] = root;
            return 2;
        } else if (flag1 == 1 || flag2 == 1 || f3 == 1) return 1;

        return 0;
    }
}

// O(Nodes)
// 4 ms, faster than 100.00%
// speed various over the internet speed

// if you wanna record the answer when dfs, you can use an array

//+++++++++++++++++++++++++++//

/*
For a given root, recursively call LCA(root.left, p, q) and LCA(root.right, p, q)

if both returns a valid node which means p, q are in different subtrees, then root will be their LCA.

if only one valid node returns, which means p, q are in the same subtree, return that valid node as their LCA.
 */
// Author: Huahua
class Solution_2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // we don't even need to traversal the subtree. bcz if root == p, even one of the subtree contains q
        // root is still the LCA
        if (root == null || root == p || root == q) return root;
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l == null || r == null) return l == null ? r : l;
        // if both child contains the target
        return root;
    }
}