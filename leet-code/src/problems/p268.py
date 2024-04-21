#[COMPLETED]

class Solution:
    def missingNumber(self, nums) -> int:
        return (len(nums) * (len(nums) + 1)) // 2 - sum(nums)

sol = Solution()
print(sol.missingNumber([3,0,1]), " expected 2")
print(sol.missingNumber([9,6,4,2,3,5,7,0,1]), " expected 8")