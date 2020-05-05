/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position
(0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:

Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:

Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.




Follow-up:
Can you solve it without using extra space?
 */

// comparing with 141, this one needs us tell the start of the cycle.
// so, we need to put a point at the start of the linked list, and another at the meet point

// https://www.youtube.com/watch?v=UkKBPGt5Nok

// if the fast just left behind the slow one step, the next step they will meet each other

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
    public ListNode detectCycle(ListNode head) {
        ListNode = fast;
        ListNode = slow;
        // at first, we check if there is a cycle
        while (fast != slow){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == null || fast.next == null) return null;
        }
        // then we check where is the meet point
        slow = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
            // why it is .next rather than .next.next ?? bcz we neet let the path length they have is same
        }
    }
}