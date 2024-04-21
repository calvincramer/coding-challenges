#[COMPLETED]

from typing import List

from main import UTest


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def inorderTraversal(self, root: TreeNode) -> List[int]:
        if root is None:
            return []
        temp = self.inorderTraversal(root.left)
        temp.append(root.val)
        temp.extend(self.inorderTraversal(root.right))
        return temp


test = UTest()
sol = Solution()

tree1 = TreeNode(1)
tree1.right = TreeNode(2)
tree1.right.left = TreeNode(3)
test.test_eq(sol.inorderTraversal(tree1), [1, 3, 2])

tree2 = None
test.test_eq(sol.inorderTraversal(tree2), [])

tree3 = TreeNode(1)
test.test_eq(sol.inorderTraversal(tree3), [1])
