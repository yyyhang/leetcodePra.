/*
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
// REFERENCE:
// https://zxi.mytechroad.com/blog/divide-and-conquer/leetcode-148-sort-list/

// O(nlogn) time and constant space, we need merge sort (bottom up)

// for linked list, we cannot use size() to get the length

// we cannot use recursive way, bcz we can use only constant space

class Solution {
    public ListNode sortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int count  = 0;
        // count nodes
        while (head != null) {
            count++;
            head = head.next;
        }

        // from 1 to count, each time merge two list with step size.
        for (int step = 1; step < count; step += step) {
            // reset the p to first node for a new step size round
            ListNode p = dummy;
            // we merge two lists with step size, then we merge next two, so on.
            // it will return the last sorted node
            for (int i = 0; i < count - step; i += step + step) p = merge(p, step);
        }
        return dummy.next;
    }

    private ListNode merge(ListNode p, int step) {
        ListNode head = p, q = p.next;
        // set q as the head of the second list
        for (int i = 0; i < step; i++) q = q.next;
        // p is dummy or the pre list, we need use .next to set it as head of this head
        p = p.next;
        int count1, count2;
        // p and q will be the start node of two list, head will be pre sorted node.
        // we sort these two lists
        for (count1 = 0, count2 = 0; count1 < step && count2 < step && q != null;) {
            if (p.val <= q.val) {
                head.next = p;
                p = p.next;
                head = head.next;
                count1++;
            }
            else {
                head.next = q;
                q = q.next;
                head = head.next;
                count2++;
            }
        }
        // append the remaining node, return the last sorted node as the start of next merge.
        // if we used all of the list one, we need to append list2
        if (count1 == step) {
            // append list2 q at the end
            head.next = q;
            while (count2 != step - 1 && q.next != null) {
                q = q.next;
                count2++;
            }
            return q;
        }
        else {
            ListNode tmp = q;
            head.next = p;
            while (count1 != step - 1) {
                p = p.next;
                count1++;
            }
            p.next = tmp;
            return p;
        }
    }
}

// 3 ms (97.17%), 42.3MB
// O(nlogn) time, O(1) space

// common merge way
class Solution_topToBottom {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head, pre = head;
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return merge(sortList(head), sortList(slow));
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val <= l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}

// 6 ms (37.32%), 44MB
// O(nlogn) time, O(logn) space
