/*
Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
addAtHead(val) : Add a node of value val before the first element of the linked list.
After the insertion, the new node will be the first node of the linked list.
addAtTail(val) : Append a node of value val to the last element of the linked list.
addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list.
If index equals to the length of linked list, the node will be appended to the end of linked list.
If index is greater than the length, the node will not be inserted.
deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.


Example:

Input:
["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
[[],[1],[3],[1,2],[1],[1],[1]]
Output:
[null,null,null,null,2,null,3]

Explanation:
MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3


Constraints:

0 <= index,val <= 1000
Please do not use the built-in LinkedList library.
At most 2000 calls will be made to get, addAtHead, addAtTail,  addAtIndex and deleteAtIndex.
 */

class MyLinkedList {
    // class Node() {} is not right to define class

    // you have to use key word this here
    class Node {
        int val;
        Node next;
        Node(int x) {
            this.val = x;
            this.next = null;
        }
        Node(int x, Node next) {
            this.val = x;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // just like 9024, we need to set the head and other things for the linked list
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = tail = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        Node curr = new Node(0, head);
        for (int i = 0; i <= index; i++) {
            curr = curr.next;
        }
        return curr.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        head = new Node(val,head);
        if (size == 0) tail = head;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        // this would be wrong since we create a node just point the end node, but no one points at it.
//        tail = new Node(val, tail);
//        if (size == 0) head = tail;
//        size++;
        // in case null pointer
        Node n = new Node(val, tail);
        if (size == 0) head = tail = n;
        else tail = tail.next = n;
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;
        // Node curr = new Node(0, head);
        // for (int i = 0; i < index-1; i++) curr = curr.next;
        if (index == 0)  { addAtHead(val); return; }
        if (index == size) { addAtTail(val); return; }
        Node prev = new Node(0, head);
        for (int i = 0; i < index; i++) prev = prev.next;
        prev.next = new Node(val, prev.next);
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        /*
        if (index == 0 && size == 1) {
            head = tail = null;
            return;
        }
        else if (index == 0) {
            head = head.next;
            return;
        }
        */
        Node prev = new Node(0, head);
        // give a real number, and check what you need
        for (int i = 0; i < index; i++) prev = prev.next;
        // delete
        prev.next = prev.next.next;
        // change head
        if (index == 0) head = prev.next;
        // then change tail
        if (index == size - 1) tail = prev;
        size--;
    }
}

// 8 ms, faster than 82.03%

// the thing is that how can I move my tail to point at the last node?
// set a condition

// the index-th start from 0;

// the most hard part is the index

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */