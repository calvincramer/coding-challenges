class Solution:
    def rearrangeArray(self, nums: list[int]) -> list[int]:
        pos = 0
        neg = 1
        ans = [0] * len(nums)
        for n in nums:
            if n < 0:
                ans[neg] = n
                neg += 2
            else:
                ans[pos] = n
                pos += 2
        return ans


sol = Solution()
print(f"{sol.rearrangeArray([3,1,-2,-5,2,-4])}, expected [3,-2,1,-5,2,-4]")
print(f"{sol.rearrangeArray([-1,1])}, expected [1,-1]")
inp = [28, -41, 22, -8, -37, 46, 35, -9, 18, -6, 19, -26, -37, -10, -9, 15, 14, 31]
exp = [28, -41, 22, -8, 46, -37, 35, -9, 18, -6, 19, -26, 15, -37, 14, -10, 31, -9]
print(f"{sol.rearrangeArray(inp)}, expected {exp}")
