from typing import Optional


# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:

    def trav(self, root: Optional[TreeNode]) -> tuple[int, int]:
        if root is None:
            return 0, 0

        leftSum, leftTilt = self.trav(root.left)
        rightSum, rightTilt = self.trav(root.right)
        tilt = abs(leftSum - rightSum)

        return leftSum + rightSum + root.val, tilt + leftTilt + rightTilt

    def findTilt(self, root: Optional[TreeNode]) -> int:
        _, tilt = self.trav(root)
        return tilt
