/*
Given a linked list, swap every two adjacent nodes and return its head.

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy  = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null){
            swap(cur);
            cur = cur.next.next;
        }
        return dummy.next;
    }

    public void swap(ListNode pre){
        ListNode dummy = pre.next;
        pre.next = dummy.next;
        dummy.next = dummy.next.next;
        pre.next.next = dummy;
    }
}

/*
class Solution2 {
    public ListNode swapPairs(ListNode head) {
        int cnt = 1;
        //int K = 2;
        ListNode dummy  = new ListNode(0);
        ListNode tmp;
        dummy.next = head;
        ListNode pre = dummy.next;
        ListNode cur = pre.next;
        while (cnt < 2){
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
            cnt++;
        }
        dummy.next.next = cur;
        
    }
}
change it later
*/
