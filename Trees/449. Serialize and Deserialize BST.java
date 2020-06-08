/*
Serialization is the process of converting a data structure or object into a sequence of bits
so that it can be stored in a file or memory buffer, or transmitted across a network connection
link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction
on how your serialization/deserialization algorithm should work. You just need to ensure that
a binary search tree can be serialized to a string and this string can be deserialized to the
original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize
algorithms should be stateless.
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


//                   5
//                 /  \
//                2    6
//              /  \    \
//             1    4    8
//

// where string stores
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return makeString(root, new StringBuffer()).toString();
    }

    // StringBuilder objects are like String objects, except that they can be modified.
    public StringBuffer makeString(TreeNode root, StringBuffer sb) {
        if(root == null) return new StringBuffer(" ");
        if(root != null) {
            // pre-order
            sb.append(root.val);
            sb.append(" ");
            makeString(root.left, sb);
            makeString(root.right, sb);
        }
        return sb;
    }

    // pre-order: 5,| 2, 1, 4,| 6, 8
    // Decodes your encoded data to tree.

    // we push parent node to stack and form its left children
    // if the next is bigger than the curr node, we need to check if it is still biger than the parent
    // if the next is bigger than the curr and less than curr's paret, it is the right child
    public TreeNode deserialize(String data) {
        // if (data == null || data == " ") return null;
        if(data.equals(" ")) return null;
        String[] ds = data.split(" ");

        Stack<TreeNode> stack = new Stack<>();
        // we add the first element as string
        TreeNode root = new TreeNode(Integer.parseInt(ds[0]));
        stack.push(root);
        // we need visit all nodes to form the tree
        for (int i = 1; i < ds.length; i++) {
            // get curr node
            TreeNode node = stack.peek();
            int temp = Integer.parseInt(ds[i]);
            // add left child and push it to the stack
            if (temp < node.val) {
                node.left = new TreeNode(temp);
                stack.push(node.left);
            }
            else {
                // when curr > node, we know it might be right child, we need to pop until to the parent node/ root node
                while (!stack.isEmpty() && temp > stack.peek().val) node = stack.pop();
                node.right = new TreeNode(temp);
                stack.push(node.right);
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

// using pre-order?

// 13 ms, faster than 29.88%



public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val).append(",");
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    //  the idea is hold this value to try all posiible position to insert it

    // pre-order: 5,| 2, 1, 4,| 6, 8
    public TreeNode deserialize(Queue<String> q, int lower, int upper) {
        if (q.isEmpty()) return null;
        // peek() method retrieves but does not remove the head of the Queue.
        // If queue is empty then peek() method also returns null.
        // we use peek() here just for test if it is in the subtree
        String s = q.peek();
        int val = Integer.parseInt(s);
        if (val < lower || val > upper) return null;
        // Both poll() and remove() method is used to remove head object of the Queue.
        //The main difference lies when the Queue is empty(). If Queue is empty then poll() method will return null.
        // While in similar case , remove() method will throw NoSuchElementException .
        q.poll();
        TreeNode root = new TreeNode(val);
        // using upper and lower to check the left and right
        root.left = deserialize(q, lower, val);
        root.right = deserialize(q, val, upper);
        return root;
    }
}

// 4 ms, faster than 93.94%
