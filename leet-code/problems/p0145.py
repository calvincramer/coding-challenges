from typing import Optional, List
import main


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def postorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        if root is None:
            return []
        res = []
        if root.left is not None:
            res.extend(self.postorderTraversal(root=root.left))
        if root.right is not None:
            res.extend(self.postorderTraversal(root=root.right))
        res.append(root.val)
        return res


sol = Solution()
test = main.UTest()
tree = TreeNode(val=1, right=TreeNode(val=2, left=TreeNode(val=3)))
test.test_eq(sol.postorderTraversal(root=tree), [3, 2, 1])
