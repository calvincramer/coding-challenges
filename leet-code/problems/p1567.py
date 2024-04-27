from main import UTest


class Solution:
    def getMaxLen(self, nums: list[int]) -> int:
        p = 0
        n = 0
        ans = 0
        for x in nums:
            if x == 0:
                # Need to reset
                p = 0
                n = 0
            elif x > 0:
                # positive streak increase
                p += 1
                # negative streak increase as long as have encountered at lease one negative before
                n = 0 if n == 0 else n + 1
            else:  # x < 0
                # positive streak and negative streak flip
                # increment both by one
                p, n = 0 if n == 0 else n + 1, p + 1
            ans = max(ans, p)
        return ans

    def getMaxLenBad(self, nums: list[int]) -> int:
        # Bad solution, doesn't work
        n = len(nums) + 1
        nums = [None] + nums
        T = [None] * n
        starts = [None] * n
        T[0] = None
        starts[0] = None
        for i in range(1, n):
            if nums[i] == 0:
                T[i] = None
                starts[i] = None
                continue
            if T[i - 1] is None:
                T[i] = nums[i]
                starts[i] = i
                continue
            if T[i - 1] == -1 and nums[i] > 0:
                T[i] = nums[i]
                starts[i] = i
                continue
            T[i] = T[i - 1] * nums[i]
            starts[i] = starts[i - 1]
        maxLen = 0
        for i in range(n):
            if T[i] is None:
                continue
            if T[i] <= 0:
                continue
            maxLen = max(maxLen, i - starts[i] + 1)
        return maxLen


test = UTest()
sol = Solution()
test.test_eq(sol.getMaxLen([1, -2, -3, 4]), 4)
test.test_eq(sol.getMaxLen([0, 1, -2, -3, -4]), 3)
test.test_eq(sol.getMaxLen([-1, -2, -3, 0, 1]), 2)
test.test_eq(sol.getMaxLen([-1, 2]), 1)
