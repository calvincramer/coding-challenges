"""
For each bit position, count total number of bits active among the candidates
The count represent some combination of candidates whose AND is of length COUNT
Return max
"""

import main


class Solution:
    def largestCombination(self, candidates: list[int]) -> int:
        maxN = max(candidates)
        maxCount = 0
        mask = 0x1
        while mask <= maxN:
            count = sum(1 for c in candidates if c & mask != 0)
            maxCount = max(maxCount, count)
            mask = mask << 1
        return maxCount


test = main.UTest()
sol = Solution()
test.test_eq(sol.largestCombination(candidates=[16, 17, 71, 62, 12, 24, 14]), 4)
test.test_eq(sol.largestCombination(candidates=[8, 8]), 2)
