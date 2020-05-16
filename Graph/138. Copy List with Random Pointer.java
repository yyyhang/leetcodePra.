/*
A linked list is given such that each node contains an additional random pointer which
could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.


Example 1:


Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:


Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
Example 3:



Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
Example 4:

Input: head = []
Output: []
Explanation: Given linked list is empty (null pointer), so return null.


Constraints:

-10000 <= Node.val <= 10000
Node.random is null or pointing to a node in the linked list.
Number of Nodes will not exceed 1000
 */

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

// we need to deep copy the nodes at first, then copy the pointers
// otherwise, the nodes are still not exist and you can't assign pointers to point to them
// also, we need hashmap to put old and new nodes. the new nodes haven't linked yet, we need something to get the node

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Map<Node,Node> map = new HashMap<>();
        // we still need to use head later. if we change it now, we can't back to the original value later
        Node curr = head;
        // loop one, we copy all the nodes
        while (curr != null){
            map.put(curr, new Node(curr.val));
            curr = curr.next;
        }
        // loop two, we copy all the pointers
        curr = head;
        // the important part is here:
        while(curr!=null){
            // we assign the value of new_node.next refer to the new_node.next
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }
}

// https://www.youtube.com/watch?v=oXABtaRa37U


// question: csn i fina a way to deal with this problem without hashtable?