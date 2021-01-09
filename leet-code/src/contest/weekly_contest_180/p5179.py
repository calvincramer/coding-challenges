# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    def print(self):
        print(str(self.val), end="  ")
        if self.left:
            print("left: ", str(self.left.val), end=" ")
        if self.right:
            print("right: ", str(self.right.val), end="")
        print()
        if self.left:
            self.left.print()
        if self.right:
            self.right.print()


def tree_to_list(root: TreeNode, l):
    if not root:
        return
    tree_to_list(root.left, l)
    l.append(root.val)
    tree_to_list(root.right, l)

class Solution:
    def balanceBST(self, root: TreeNode) -> TreeNode:
        if not root:
            return None
        lst = []
        tree_to_list(root, lst)
        print(lst)
        return self.trav(lst)



    def trav(self, lst) -> TreeNode:
        if not lst:
            return None
        mid = len(lst) // 2
        root = TreeNode(lst[mid])
        root.left = self.trav(lst[:mid])
        root.right = self.trav(lst[mid+1:])
        return root


t = TreeNode(1)
t.left = TreeNode(1.5)
t.right = TreeNode(2)
t.right.right = TreeNode(3)
t.right.right.right = TreeNode(4)

t.print()
sol = Solution()
t = sol.balanceBST(t)
print("\n")
t.print()