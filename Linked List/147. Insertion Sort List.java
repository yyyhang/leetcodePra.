/*
Sort a linked list using insertion sort.


A graphical example of insertion sort. The partial sorted list (black) initially contains
only the first element in the list.

With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list


Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data,
finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

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

// Note this problem is for linked list not array;

// for the list questions, we first check if the node is null or not
// then we use a dummy node to point to the head
// we use prev here to traversal the elements sorted

class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = head;
        ListNode prev = null;
        ListNode tmp = null;
        while(curr != null && curr.next != null){
            if(curr.val <= curr.next.val){
                curr = curr.next;
            } else {
                // change their position
                // at first, take out the element needed to change
                tmp = curr.next;
                // it is just like delete this node (tmp)
                curr.next = curr.next.next;
                prev = dummy;
                while(prev.next.val <= tmp.val){
                    prev = prev.next;
                }
                // insert btew pre and pre.next
                tmp.next = prev.next;
                prev.next = tmp;
                // Since we actually use the curr.next and we have already changed the next already
                // we do not need to change the : curr = curr.next;
            }
        }
        return dummy.next;
    }
}

// every time we take out one node, and put the node between prev and prev.next if this node < prev.next
// otherwise, we change the prev to prev.next, and do step 1 again until we insert it into correct position
class Solution_2 {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode curr = head;
        ListNode prev = dummy;
        while (curr != null) {

            if(prev.val > curr.val)
                prev = dummy;

            while (prev.next != null && prev.next.val<curr.val) {
                prev = prev.next;
            }
            //insert current node between prev and prev.next
            ListNode nextNode=curr.next;
            curr.next=(prev.next);
            prev.next=(curr);
            curr=nextNode;
        }
        return dummy.next;
    }
}

// but why this one is mush faster than the solution one?
