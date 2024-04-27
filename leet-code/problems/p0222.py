from typing import Optional
import main


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def countNodes(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        if root.left is None and root.right is None:
            return 1
        if root.right is None:
            return 1 + self.countNodes(root=root.left)

        # Figure out depth of left
        d = 0
        trav = root
        while trav is not None:
            d += 1
            trav = trav.left

        # Check depth of right subtree
        trav = root.right
        rd = 1
        while trav is not None:
            rd += 1
            trav = trav.left

        if d == rd:
            # Know that left subtree is filled out on last row
            return 1 + self.countNodes(root=root.right) + (2 ** (d - 1) - 1)
        else:
            # Know that left subtree is not filled out on last row, know right subtree is filled out up to rd
            return 1 + self.countNodes(root=root.left) + (2 ** (d - 2) - 1)


test = main.UTest()
sol = Solution()
T = TreeNode
tree = TreeNode(1, left=T(2, left=T(4), right=T(5)), right=T(3, left=T(6)))
test.test_eq(sol.countNodes(root=tree), 6)

tree = TreeNode(1, left=T(2, left=T(4)), right=T(3))
test.test_eq(sol.countNodes(root=tree), 4)
