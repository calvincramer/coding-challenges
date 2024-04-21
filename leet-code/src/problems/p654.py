#[COMPLETED]

from typing import List

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    def print_t(self):
        print(str(self.val) + " -> " + str("none" if not self.left else self.left.val) + ", " + str("none" if not self.right else self.right.val))
        if self.left:
            self.left.print_t()
        if self.right:
            self.right.print_t()

class Solution:
    def constructMaximumBinaryTree(self, nums: List[int]) -> TreeNode:
        if not nums or len(nums) == 0:
            return None
        return self.trav(nums, 0, len(nums) - 1)

    # Low, high inclusive range
    def trav(self, nums, low, high) -> TreeNode:
        max_i = low
        max_n = nums[low]
        for i in range(low+1, high+1):
            if nums[i] > max_n:
                max_n = nums[i]
                max_i = i
        head = TreeNode(nums[max_i])
        if max_i - low >= 1:
            head.left = self.trav(nums, low, max_i - 1)
        if high - max_i >= 1:
            head.right = self.trav(nums, max_i + 1, high)
        return head


sol = Solution()
t = sol.constructMaximumBinaryTree([3,2,1,6,0,5])
t.print_t()
