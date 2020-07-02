/*
Given a binary search tree (BST) with duplicates, find all the mode(s)
(the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.


For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2


return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space?
(Assume that the implicit stack space incurred due to recursion does not count).
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
// the easiest way, we can use hashmap
// but we need extra space
class Solution {
    Map<Integer,Integer> map = new HashMap<>();

    void solve(TreeNode root){
        if(root == null) return;
        int c = map.getOrDefault(root.val,0)+1;
        map.put(root.val,c);
        solve(root.left);
        solve(root.right);
    }

    public int[] findMode(TreeNode root) {
        List<Integer> lis = new ArrayList<>();
        solve(root);
        int max=0;
        for(int i : map.values()) max=Math.max(max,i);
        for(Map.Entry<Integer,Integer> m : map.entrySet()){
            if(m.getValue()==max) lis.add(m.getKey());
        }
        int ans[] = new int[lis.size()];
        for(int i=0;i<lis.size();i++)
            ans[i] = lis.get(i);
        return ans;
    }
}
// 4 ms, faster than 41.62%;  41.6 MB, less than 7.14%


class Solution_mine {

    Integer pre;
    int max = Integer.MIN_VALUE;
    int cnt = 0;

    public int[] findMode(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        inorder(root, ans);

        // convert it to int[]
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        inorder(root.left, ans);

        if (pre == null || root.val != pre) cnt = 1;
        else if (root.val == pre) cnt++;
        pre = root.val;
        if (cnt > max) {
            ans.clear();
            ans.add(pre);
            max = cnt;
        }
        else if (cnt == max){
            ans.add(pre);
        }
        inorder(root.right, ans);
    }
}

// 0 ms, faster than 100.00%, 39.9 MB, less than 28.57%