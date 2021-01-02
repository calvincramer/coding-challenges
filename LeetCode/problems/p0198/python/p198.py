#[COMPLETED]

class Solution(object):
    """
    :type nums: List[int]
    :rtype: int
    """
    def rob(self, nums):
        if nums is None or len(nums) == 0:
            return 0
        elif len(nums) == 1:
            return nums[0]
        elif len(nums) == 2:
            return max(nums)
        best = [0] * len(nums)
        best[0] = nums[0]
        best[1] = max(nums[0], nums[1])
        for i in range(2, len(nums)):
            best[i] = max(best[i-2] + nums[i], best[i-1])
        return best[-1]


sol = Solution()
print(sol.rob([1,2,3,1]), "expected 4")
print(sol.rob([2,7,9,3,1]), "expected 12")
print(sol.rob([183,219,57,193,94,233,202,154,65,240,97,234,100,249,186,66,90,238,168,128,177,235,50,81,185,165,217,207,88,80,112,78,135,62,228,247,211]), "expected ???")
print(sol.rob([2,1,1,2]), "expected 4")