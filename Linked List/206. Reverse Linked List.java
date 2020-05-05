/*
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively.
Could you implement both?
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
we need three pointers, one point to current node. we need to change its point point to its prenode later
one pointer point to curr.next_node, to make sure the rest nodes won't be lost
one pointer point to  curr.pre_node, we need to change curr pointer poin to it
(the first pre_pointer is dummy)
then move these pointers to next position until reach the boundry
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        // as pre just point to an exsit node, we don't need to allcate a new memory for it
        ListNode cur = head;
        ListNode next;
        while (cur != null){
            next = cur.next;
            // let pointer next record the position of curr.next
            cur.next = pre;
            // then change curr's pointter point to pre
            pre = cur;
            cur = next;
            // moving posdition of poiners
        }
        return pre;
        // finally, return the last node (pre move to cur and ex-cur is null now)
    }
}