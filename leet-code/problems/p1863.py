import main


class Solution:
    def subsetXORSum(self, nums: list[int]) -> int:
        n = len(nums)

        def sums(term, i):
            if i == n:
                return term
            return sums(term, i + 1) + sums(term ^ nums[i], i + 1)

        return sums(0, 0)


sol = Solution()
test = main.UTest()
test.test_eq(sol.subsetXORSum([1, 3]), 6)
test.test_eq(sol.subsetXORSum([5, 1, 6]), 28)
test.test_eq(sol.subsetXORSum([3, 4, 5, 6, 7, 8]), 480)
