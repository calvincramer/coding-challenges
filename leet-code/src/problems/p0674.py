#[COMPLETED]

class Solution:
    def findLengthOfLCIS(self, nums):
        if len(nums) < 2:
            return len(nums)
        longest = 0
        i = 0
        while i < len(nums) - 1:
            j = i+1
            while j < len(nums) and nums[j] > nums[j-1]:
                j += 1
            length = j - i
            i = j
            longest = max(longest, length)
        return longest


sol = Solution()
print(sol.findLengthOfLCIS([1,3,5,4,7]), "expected 3")
print(sol.findLengthOfLCIS([2,2,2,2,2]), "expected 1")
