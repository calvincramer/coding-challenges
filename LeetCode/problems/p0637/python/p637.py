#[COMPLETED]

from typing import List

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def averageOfLevels(self, root: TreeNode) -> List[float]:
        sums = []
        counts = []
        self.trav(root, 0, sums, counts)
        return [s / c for s,c in zip(sums, counts)]

    def trav(self, root, level, sums, counts):
        if not root:
            return
        if len(sums) <= level:
            # New level
            sums.append(0)
            counts.append(0)
        # Go over this node
        sums[level] += root.val
        counts[level] += 1
        # Traverse down
        self.trav(root.left,  level + 1, sums, counts)
        self.trav(root.right, level + 1, sums, counts)
