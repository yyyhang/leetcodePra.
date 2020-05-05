/*
Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed)
in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.


Example 3:

Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.


Follow up:

Can you solve it using O(1) (i.e. constant) memory?
 */


// we can use two pointers, one is fast and another one is slow. then let the fast to meet the slow in the 2 or 3 or so on round if there is a cycle
// but the point is, how we prove this algorithm is true ?

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        // first, check the value
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;
        // why it is head.next rather than head??

        while (slow != fast){
            if (fast == null || fast.next == null) return false;

            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}