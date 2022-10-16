#[COMPLETED]

class Solution(object):
    """
    :type nums: List[int]
    :type val: int
    :rtype: int
    """
    def removeElement(self, nums, val):

        num_removed = 0
        for i,n in enumerate(nums):
            if n == val:
                num_removed += 1
            else:
                nums[i - num_removed] = nums[i]
        return len(nums) - num_removed


sol = Solution()
l = [3,2,2,3]
print(sol.removeElement(l, 3), "expected 2")

l = [0,1,2,2,3,0,4,2]
print(sol.removeElement(l, 2), "expected 5")