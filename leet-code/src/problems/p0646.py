#[COMPLETED]

from typing import List

from main import UTest


class Solution:
    def findLongestChain(self, pairs: List[List[int]]) -> int:
        # Credit: https://leetcode.com/problems/maximum-length-of-pair-chain/discuss/105607/4-Liner-Python-Greedy
        # Iterate though sorted pairs, find chain greedily
        result = 0                      # Chain length
        last_right = float('-inf')
        pairs.sort(key=lambda x: x[1])
        for (p_l, p_r) in pairs:
            if last_right < p_l:
                last_right = p_r
                result += 1
        return result


test = UTest()
sol = Solution()

test.test_eq(sol.findLongestChain(pairs=[[1,2], [2,3], [3,4]]), 2)
test.test_eq(sol.findLongestChain(pairs=[[3,4], [2,3], [1,2]]), 2)
