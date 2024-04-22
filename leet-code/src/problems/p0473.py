from typing import List
from functools import cache

class Solution:
    def makesquare(self, matchsticks: List[int]) -> bool:
        perim = sum(matchsticks)
        # Perimeter must be able to form a square: divisible by 4
        if perim % 4 != 0:
            return False
        side_len = perim // 4
        # If any length > side length this it's impossible
        if any(stick > side_len for stick in matchsticks):
            return False
        matchsticks = list(reversed(sorted(matchsticks)))
        num_sticks = len(matchsticks)
        @cache
        def helper(e1, e2, e3, e4, idx) -> bool:
            if e1 == e2 == e3 == e4 == side_len:
                return True
            if idx >= num_sticks:
                return False
            if e1 > side_len or e2 > side_len or e3 > side_len or e4 > side_len:
                return False
            e1, e2, e3, e4 = sorted([e1, e2, e3, e4])   # Speed!
            return helper(e1 + matchsticks[idx], e2, e3, e4, idx+1) or helper(e1, e2 + matchsticks[idx], e3, e4, idx+1) or helper(e1, e2, e3 + matchsticks[idx], e4, idx+1) or helper(e1, e2, e3, e4 + matchsticks[idx], idx+1)
        return helper(0, 0, 0, 0, 0)


sol = Solution()
print(sol.makesquare([1,1,2,2,2]), "expected True")
print(sol.makesquare([3,3,3,3,4]), "expected False")