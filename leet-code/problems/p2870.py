from main import UTest

from collections import defaultdict


class Solution:
    def minOperations(self, nums: list[int]) -> int:
        # Count numbers
        freq = defaultdict(int)
        for n in nums:
            freq[n] += 1

        # Any unique occurrence is error, ever other frequency is OK
        for val in freq.values():
            if val == 1:
                return -1

        # Minimum number of steps to remove all N is floor((freq_N - 1) / 3) + 1
        # Just build a table and see simple formula
        ans = 0
        for val in freq.values():
            ans += ((val - 1) // 3) + 1
        return ans


UTest.exp_eq(Solution().minOperations(nums=[2, 3, 3, 2, 2, 4, 2, 3, 4]), 4)
UTest.exp_eq(Solution().minOperations(nums=[2, 1, 2, 2, 3, 3]), -1)
