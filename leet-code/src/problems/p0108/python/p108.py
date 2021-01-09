#[COMPLETED]

# Definition for a binary tree node.
class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

    def print_tn(self):
        print(self.val, " -> left=", ("None" if self.left is None else self.left.val), " right=", ("None" if self.right is None else self.right.val))

        if self.left is not None:
            self.left.print_tn()
        if self.right is not None:
            self.right.print_tn()


class Solution(object):
    """
    :type nums: List[int]
    :rtype: TreeNode
    """
    def sortedArrayToBST(self, nums):
        if nums is None or nums == []:
            return None
        mid = len(nums) // 2
        head = TreeNode(nums[mid])
        self.trav(head, nums, 0, mid, len(nums) - 1)
        return head

    # left and right are inclusive
    # Fill out child nodes for cur_node based on left mid right
    def trav(self, cur_node, nums, left, mid, right):
        if right <= left:
            return
        mid_left = (left + (mid - 1)) // 2
        if left < mid:
            cur_node.left = TreeNode(nums[mid_left])
            self.trav(cur_node.left, nums, left, mid_left, mid - 1)

        mid_right = ((mid + 1) + right) // 2
        if right > mid:
            cur_node.right = TreeNode(nums[mid_right])
            self.trav(cur_node.right, nums, mid + 1, mid_right, right)


sol = Solution()
t = sol.sortedArrayToBST([-10,-3,0,5,9])
t.print_tn()
print()
t = sol.sortedArrayToBST([0,1,2,3,4,5])
t.print_tn()