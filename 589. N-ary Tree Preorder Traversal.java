/*
Given an n-ary tree, return the preorder traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).



Follow up:

Recursive solution is trivial, could you do it iteratively?

Input: root = [1,null,3,2,4,null,5,6]
Output: [1,3,5,6,2,4]

Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
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

class Solution {
    public List<Integer> preorder(Node root) {
        List <Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack <Node> stack = new Stack <> ();
        Node curr = root;
        stack.push(curr);

        while (!stack.isEmpty()){
            curr = stack.pop();
            res.add(curr.val);
            // go through one branch to the end
            for (int i = curr.children.size()-1; i>=0 ; i--){
                stack.push(curr.children.get(i));
                // add the right side children into stack first
            }
        }
        return res;
    }
}

/*
recursive solution:

public List<Integer> preorder(Node root) {
    List<Integer> res = new ArrayList<>();
    helper(root,res);
    return res;
}
public void helper(Node root, List<Integer> list){
    if(root == null) return ;
    list.add(root.val);
    if(root.children == null) return;
    for(Node child : root.children){
        helper(child,list);
    }
}

 */