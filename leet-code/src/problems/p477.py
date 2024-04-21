#[COMPLETED]

from itertools import combinations

class Solution(object):
    """
    :type nums: List[int]
    :rtype: int
    """
    def totalHammingDistance(self, nums):
        # SOLUTION TAKEN FROM LEETCODE, NOT MY SOLUTION
        total = 0
        n = len(nums)
        for j in range(32):
            bit_count = 0
            for i in range(n):
                bit_count += (nums[i] >> j) & 1
            total += bit_count * (n - bit_count)
        return total

    """
    :type nums: List[int]
    :rtype: int
    """
    def totalHammingDistanceCalvin(self, nums):
        if nums is None or len(nums) < 2:
            return 0
        total = 0
        for n1, n2 in combinations(nums, 2):
            diff_bits = n1 ^ n2
            while diff_bits > 0:
                if diff_bits & 1 == 1:
                    total += 1
                diff_bits = diff_bits >> 1
        return total


sol = Solution()
print(sol.totalHammingDistance([4, 14, 2]), "expected 6")
