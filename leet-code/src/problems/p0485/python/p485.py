#[COMPLETED]

class Solution:
    def findMaxConsecutiveOnes(self, nums) -> int:
        m = 0
        cur = 0
        for n in nums:
            if n == 1:
                cur += 1
            else:
                m = max(m, cur)
                cur = 0
        return max(m, cur)
sol = Solution()
print(sol.findMaxConsecutiveOnes([1,1,0,1,1,1]), "expected 3")