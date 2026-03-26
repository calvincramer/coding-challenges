import main

from math import factorial


class Solution:
    def numberOfWays(self, startPos: int, endPos: int, k: int) -> int:
        if k < 0:
            return 0

        # Distance is number of steps needed to get from start to end
        dist = abs(endPos - startPos)
        extra_steps = k - dist

        # Not close enough
        if extra_steps < 0:
            return 0

        # Will not land on end because of parity
        if extra_steps % 2 != 0:
            return 0

        if k == 0 and dist == 0:
            return 1

        # Number of steps needed to go left and right, order doesn't matter
        # left + right = k
        left_steps = extra_steps // 2
        right_steps = dist + (extra_steps // 2)

        # permute left and right k times
        answer = factorial(k) // (factorial(left_steps) * factorial(right_steps))
        return answer % ((10**9) + 7)


test = main.UTest()
test.test_eq(Solution().numberOfWays(1, 2, 3), 3)
test.test_eq(Solution().numberOfWays(2, 5, 10), 0)
