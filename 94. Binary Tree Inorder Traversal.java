/*
Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?
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
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList < > ();
        // ArrayList class is a dynamic array, ArrayList<Type> arrayList= new ArrayList<>();
        // We can also create array lists using the List interface.
        // It's because the ArrayList class implements the List interface.
        // Integer is the corresponding wrapper class of the int type.
        Stack <TreeNode> stack = new Stack < > ();
        if (root == null) return result;    //but idk why i cant return null here? is [] and null different?
        TreeNode curr = root;
        // why need curr != null here?
        // in what case the stack is empty but the curr is not null? ans: the first time
        while (curr != null || !stack.empty()){
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
                // push all the left trees into stack in this branch
                // when curr != null means this node is a leaf
            }
            curr =stack.pop();
            // after travel all left trees, then we can return the node
            result.add(curr.val);
            curr = curr.right;
            // then right branch
        }
        return res;
    }
}
//O(n)

//another solution is recursive

//and another is Morris Traversal?? idk it before. i'll try it next time.