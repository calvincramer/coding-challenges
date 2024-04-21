#[COMPLETED]

# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

    def print_LN(self):
        print(self.val, end="")
        if self.next is not None:
            print(" -> ", end="")
            self.next.print_LN()
        else:
            print()

    def __str__(self):
        return str(self.val)

    def __repr__(self):
        return str(self.val)


class Solution(object):
    """
    :type lists: List[ListNode]
    :rtype: ListNode
    """
    def mergeKLists(self, lists):
        if lists is None or len(lists) == 0:
            return []
        sorted_heads = []
        for node in lists:
            if type(node) is ListNode:
                self.insert_inorder(sorted_heads, node, 0, len(sorted_heads) - 1)

        if len(sorted_heads) == 0:
            return []

        merged_head = sorted_heads.pop(0)
        if merged_head is not None and merged_head.next is not None:
            self.insert_inorder(sorted_heads, merged_head.next, 0, len(sorted_heads) - 1)
        merged_cur = merged_head

        while len(sorted_heads) > 0:
            node = sorted_heads.pop(0)
            merged_cur.next = node
            merged_cur = merged_cur.next
            if node.next is not None:
                self.insert_inorder(sorted_heads, node.next, 0, len(sorted_heads) - 1)

        return merged_head

    # Left, right are inclusive
    def insert_inorder(self, lst, node, left, right):
        if len(lst) == 0:
            lst.insert(0, node)
            return
        elif right <= left:
            lst.insert(left if node.val <= lst[left].val else left + 1, node)
            return
        mid = (left + right) // 2
        if node.val < lst[mid].val:
            self.insert_inorder(lst, node, left, mid - 1)
        elif node.val > lst[mid].val:
            self.insert_inorder(lst, node, mid + 1, right)
        else:
            lst.insert(mid, node)


sol = Solution()
h1 = ListNode(1)
h1.next = ListNode(4)
h1.next.next = ListNode(5)
h2 = ListNode(1)
h2.next = ListNode(3)
h2.next.next = ListNode(4)
h3 = ListNode(2)
h3.next = ListNode(6)
ret = sol.mergeKLists([h1, h2, h3])
ret.print_LN()
"""
lst = [ListNode(1), ListNode(3), ListNode(5), ListNode(6), ListNode(7), ListNode(8)]
print("orig: ", lst)
sol.insert_inorder(lst, ListNode(0), 0, len(lst) - 1)
print("add 0:", lst)
sol.insert_inorder(lst, ListNode(2), 0, len(lst) - 1)
print("add 2:", lst)
sol.insert_inorder(lst, ListNode(4), 0, len(lst) - 1)
print("add 4:", lst)
sol.insert_inorder(lst, ListNode(5), 0, len(lst) - 1)
print("add 5:", lst)
sol.insert_inorder(lst, ListNode(6), 0, len(lst) - 1)
print("add 6:", lst)
sol.insert_inorder(lst, ListNode(7), 0, len(lst) - 1)
print("add 7:", lst)
sol.insert_inorder(lst, ListNode(8), 0, len(lst) - 1)
print("add 8:", lst)
sol.insert_inorder(lst, ListNode(9), 0, len(lst) - 1)
print("add 9:", lst)
"""

sol.mergeKLists([])
sol.mergeKLists([[]])
sol.mergeKLists([[],[]])
