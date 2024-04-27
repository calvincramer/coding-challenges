#[COMPLETED]

class Solution(object):
    """
    :type nums: List[int]
    :rtype: int
    """
    def findLHS(self, nums):
        nums = sorted(nums)
        lhs = 0
        start = 0
        for i in range(1, len(nums)):
            if nums[i] - nums[start] > 1:
                # walk up (slow way to do it but still O(n)
                while nums[i] - nums[start] > 1:
                    start += 1
            elif i - start + 1 > lhs and nums[i] - nums[start] == 1:
                lhs = i - start + 1
        return lhs


sol = Solution()
print(sol.findLHS([1,3,2,2,5,2,3,7]), "expected 5")
print(sol.findLHS([1,1,1,1]), "expected 0")
