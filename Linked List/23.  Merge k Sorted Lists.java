/*
Merge k sorted linked lists and return it as one sorted list.
Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
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

// priority queue

class Solution {
    class NodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode a, ListNode b) {
            return a.val - b.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        if (lists == null || lists.length == 0) return dummy.next;
        int size = lists.length;
        ListNode curr = dummy;
        NodeComparator cmp = new NodeComparator();
        Queue<ListNode> pqueue = new PriorityQueue(cmp);
        // put all the head node into the pqueue
        for (int i = 0; i < size; i++){
            if (lists[i] != null) pqueue.add(lists[i]);
        }
        while (pqueue.size() != 0) {
            ListNode node = pqueue.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) pqueue.add(node.next);
        }
        return dummy.next;
    }
}

// 5ms


// or write like this:

class Solution_2 {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> heap = new PriorityQueue<>( (a, b) -> a.val - b.val);
        //2, 3
        for(ListNode head : lists){
            if (head != null)
                heap.add(head);
        }
        if( heap.isEmpty() )    return null;
        ListNode t = heap.poll();
        if( t.next != null )
            heap.add(t.next);

        ListNode head = new ListNode( t.val );
        ListNode temp = head;

        while( ! heap.isEmpty() ){
            ListNode curr = heap.poll();
            if( curr.next != null )
                heap.add(curr.next);

            ListNode node = new ListNode(curr.val);
            temp.next = node;
            temp = temp.next;
        }
        return head;
    }
}

// 7ms

class Solution_3 {
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeRange(lists, 0, lists.length-1);
    }
    ListNode mergeRange(ListNode[] lists, int s, int e) {
        if (s > e) return null;
        if (s == e) return lists[s];
        int mid = (s + e) >> 1;
        return merge(mergeRange(lists, s, mid), mergeRange(lists, mid+1, e));
    }
    ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        ListNode head = new ListNode(-1);
        ListNode first = head;
        for (ListNode n1=node1, n2=node2; n1 != null || n2 != null;) {
            if (n2 == null || (n1 != null && n1.val < n2.val)) {
                head.next = n1;
                head = head.next;
                n1 = n1.next;
            } else {
                head.next = n2;
                head = head.next;
                n2 = n2.next;
            }
        }
        return first.next;
    }
}

// 3ms