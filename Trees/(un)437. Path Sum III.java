/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards
(traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
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

// we travesal all the nodes as root to get the sum
// so, we need two recursive

class Solution {
    int res = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return res;
        helper(root, sum);
        if(root.left != null) pathSum(root.left, sum);
        if(root.right != null) pathSum(root.right, sum);
        return res;
    }
    // find the number of solutions that starting at the root, it doesn't matter where it ends.
    private void helper(TreeNode root, int sum) {
        // search until empty tree
        if(root == null) return;
        if(root.val == sum) res++;
        helper(root.left, sum-root.val);
        helper(root.right, sum-root.val);
    }
}
// 18ms, 60.60%
// O(n^2)


public class Solution_2 {
    int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        int depth = findDepth(root);
        int[] pathArray = new int[depth];
        helper(root, sum, pathArray, 0, depth);
        return count;
    }

    public void helper(TreeNode root, int total, int[] nums, int level, int depth) {
        if(root == null) return;
        if(level >= depth) return;
        nums[level] = root.val;
        int sum = 0;
        for(int i = level; i>=0; i--) {
            sum += nums[i];
            if(sum == total)
                count++;
        }
        helper(root.left, total, nums, level + 1, depth);
        helper(root.right, total, nums, level + 1, depth);
    }

    public int findDepth(TreeNode root) {
        if(root == null) return 0;
        int left = findDepth(root.left);
        int right = findDepth(root.right);
        return Math.max(left, right) + 1;
    }
}

// 3 ms, faster than 75.13%
// O(n) ?

// using hashmap, prefix sum
class Solution_3 {
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        Map<Integer,Integer> s = new HashMap<>();
        s.put(0,1);
        return fun(root, s, 0, sum);
    }
    public int fun(TreeNode root, Map<Integer, Integer> s , int currSum, int sum)
    {
        if(root == null) return 0;

        currSum+= root.val;

        int totalPath = 0;
        if(s.containsKey(currSum-sum) && s.get(currSum-sum)>0 ) totalPath += s.get(currSum-sum);

        s.put(currSum, s.getOrDefault(currSum, 0)+1);

        totalPath += fun(root.left, s, currSum, sum);
        totalPath += fun(root.right, s, currSum, sum);

        s.put(currSum, s.get(currSum)-1);

        return totalPath;

    }
}

// 2 ms, faster than 100.00% of Java online submissions