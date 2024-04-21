#[COMPLETED]

# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next


class Solution:
    def connect(self, root: 'Node') -> 'Node':
        if not root:
            return root
        self.trav(root.left, root.right)
        return root

    def trav(self, root_left, root_right):
        if root_left and root_right:
            root_left.next = root_right
        else:
            return  # Perfect tree, return if found None
        # Recurse
        self.trav(root_left.left, root_left.right)
        self.trav(root_right.left, root_right.right)
        self.trav(root_left.right, root_right.left)     # Connect distant relatives



t = Node(1)
t.left = Node(2)
t.right = Node(3)
t.left.left = Node(4)
t.left.right = Node(5)
t.right.left = Node(6)
t.right.right = Node(7)

sol = Solution()
ret = sol.connect(t)
trav = ret
while trav:
    lin_trav = trav
    # Print level list
    while lin_trav:
        print(lin_trav.val, " -> ", end="", sep="")
        lin_trav = lin_trav.next
    print("end")
    # Go down left side of tree
    trav = trav.left
