import main


class Solution:
    def minSwaps(self, nums: list[int]) -> int:
        n_ones = sum(nums)
        nums = nums + nums
        inWindow = 0
        maxInWin = 0
        for i in range(len(nums)):
            if i >= n_ones and nums[i - n_ones] == 1:
                inWindow -= 1
            if nums[i] == 1:
                inWindow += 1
            maxInWin = max(maxInWin, inWindow)
        return n_ones - maxInWin


sol = Solution()
test = main.UTest()
test.test_eq(sol.minSwaps([0, 1, 0, 1, 1, 0, 0]), 1)
test.test_eq(sol.minSwaps([0, 1, 1, 1, 0, 0, 1, 1, 0]), 2)
test.test_eq(sol.minSwaps([1, 1, 0, 0, 1]), 0)
