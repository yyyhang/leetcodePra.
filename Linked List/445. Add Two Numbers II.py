"""
You are given two non-empty linked lists representing two non-negative integers.
The most significant digit comes first and each of their nodes contain a single digit.
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
"""

# NB: this one is not same as No.2 as this one not stores integers reversely.

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

#withour reverse
class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:        # like a def of what return
        n1 = n2 = 0
        #store the value in linked list
        while l1:
            n1 = n1*10 + l1.val
            l1 = l1.next
        while l2:
            n2 = n2*10 + l2.val
            l2 = l2.next

        dummy = tail = ListNode(0)      #The value is doesn't matter
        sum = str(n1+n2)                # read the digits in sum
        for e in sum:
            tail.next = ListNode(e)
            tail = tail.next
        return dummy.next

#l1 = l1.next if l1 else None
#l2 = l2.next if l2 else None #NB: can't without else ...

#Runtime: 68 ms, faster than 90.91% of Python3 online submissions for Add Two Numbers II.
#Memory Usage: 13.6 MB, less than 6.25% of Python3 online submissions for Add Two Numbers II.
#Maybe I can try to improve it later