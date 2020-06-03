/*
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
Follow up:

A solution using O(n) space is pretty straight forward.
Could you devise a constant space solution?
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

// we need inorder traversal to find the mistake elements
// but if we need O(1) space, we cannot use stack or recursive. instead, we need:
// Morris traversal:
// https://www.educative.io/edpresso/what-is-morris-traversal
// https://www.bilibili.com/video/av77724849/
    // this question:
    // https://www.youtube.com/watch?v=QZMropFflv4

class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode first = null, second = null;
        boolean firstTime = true;
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);
        // Morries Traversal
        while (root != null) {
            // if we are not reach the last left node, we link the rightmost node to the root
            if (root.left != null) {
                TreeNode tmp = root.left;
                while (tmp.right != null && tmp.right != root) tmp = tmp.right;

                if (tmp.right == null) {
                    // if we haven't create the link, we link the root to the rightmost node as a clue
                    tmp.right = root;
                    // then we change the root node to the previous root's left child
                    // same idea as recursive
                    root = root.left;
                } else {
                    // else, we've created before, now we need to back to the root
                    tmp.right = null;
                    // we midify here to adapt to this question
                    // we find the first wrong number
                    if (pre.val > root.val && firstTime) {
                        first = pre;
                        firstTime = false;
                    }
                    if (pre.val > root.val && !firstTime) {
                        second = root;
                    }
                    // else we just update the pre value
                    pre = root;

                    root = root.right;
                }
                // no left, we move to the right
            } else {
                if (pre.val > root.val && firstTime) {
                    first = pre;
                    firstTime = false;
                }
                if (pre.val > root.val && !firstTime) {
                    second = root;
                }
                pre = root;
                root = root.right;
            }
        }
        // then we swap them
        if (first != null && second != null) {
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
    }
}
// 2 ms, faster than 77.91%; 39.6 MB, less than 80.77%

//----------------------------------//

// more neat one :
public class Solution {
    public void recoverTree(TreeNode root) {

        TreeNode first = null;     // first node need to be swap
        TreeNode second = null;    // second node need to be swap
        TreeNode pre = new TreeNode(Integer.MIN_VALUE);  //previous node.

        while(root!=null){
            TreeNode node = root.left;

            // If left is not null, we need to find the rightmost node of left subtree,
            // Set its right child to current node
            if(node!=null){

                //find the rightmost
                while(node.right!=null && node.right != root){
                    node = node.right;
                }

                //There are two cases,
                //null: first time we access current, set node.right to current and move to left child of current and continue;
                //current: we accessed current before, thus we've finished traversing left subtree, set node.right back to null;
                if(node.right == null){
                    node.right = root;
                    root = root.left;
                    continue;
                }else{
                    node.right = null;
                }
            }

            // compare current node with previous node
            if(root.val < pre.val ){
                // first time we enconter reversed order, we set previous node to first
                // using this instead of flag
                if( first == null ){
                    first = pre;
                }
                //In case that two nodes are successive, we set second to current every time.
                second = root;
            }
            pre = root;
            root = root.right;
        }

        //swap the value;
        int temp = second.val;
        second.val = first.val;
        first.val = temp;
    }
}

//--------------------------------------------------//

class Solution_recursive {
    public void recoverTree(TreeNode root) {
        inOrder(root);
        if (swaps[0] != null && swaps[1] != null) {
            int tmp = swaps[0].val;
            swaps[0].val = swaps[1].val;
            swaps[1].val = tmp;
        }
    }

    TreeNode cur;
    TreeNode[] swaps = new TreeNode[2];
    private void inOrder(TreeNode node) {
        if (null == node) {
            return;
        }

        inOrder(node.left);
        if (cur != null && cur.val > node.val) {
            /** first met, shoule put both cur & node, because maybe this two nodes will be swaped */
            if (swaps[0] == null) {
                swaps[0] = cur;
            }
            swaps[1] = node;
        }
        cur = node;
        inOrder(node.right);
    }
}

// 2 ms, faster than 77.91%; 39.9 MB, less than 80.77%