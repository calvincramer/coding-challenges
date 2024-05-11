import main


class Solution:
    def largestDivisibleSubset(self, nums: list[int]) -> list[int]:
        nums = sorted(nums)
        n = len(nums)
        if n <= 1:
            return nums
        idxs = [0] * n
        vals = [0] * n

        idxs[0] = 1
        maxIdx = 0
        maxVal = 1

        for i in range(1, n):
            for j in range(i + 1):
                if nums[i] % nums[j] != 0:
                    continue
                if idxs[j] > idxs[j]:
                    idxs[i] = idxs[j]
                    vals[i] = j
            maxVal = max(maxVal, vals[i])

        i = maxIdx
        ret = [nums[maxIdx]]
        while i != vals[i]:
            i = vals[i]
            ret.append(nums[i])
        return ret


test = main.UTest()
sol = Solution()
test.test_eq(sol.largestDivisibleSubset([1, 2, 3]), [1, 2])
test.test_eq(sol.largestDivisibleSubset([1, 2, 4, 8]), [1, 2, 4, 8])
