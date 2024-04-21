#[COMPLETED]

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def __init__(self):
        self.total = 0

    def trav(self, root: TreeNode, current_val: int):
        if root.val == 1:
            current_val += 1
        # Terminate here if leaf node
        if root.left is None and root.right is None:
            self.total += current_val
            return
        # Traverse down
        current_val *= 2
        if root.left:
            self.trav(root.left, current_val)
        if root.right:
            self.trav(root.right, current_val)

    def sumRootToLeaf(self, root: TreeNode) -> int:
        if root is None:
            return 0
        self.total = 0  # Collect results
        self.trav(root, 0)
        return self.total


sol = Solution()
print(sol.sumRootToLeaf(None))
