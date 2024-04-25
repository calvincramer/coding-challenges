import main
from typing import List


class Solution:
    def __foo(self, x: int) -> int:
        res = 0
        while x > 0:
            if x & 0x1 == 1:
                res += 1
            x = x >> 1
        return res

    def countBits(self, n: int) -> List[int]:
        return [self.__foo(i) for i in range(n + 1)]


test = main.UTest()
sol = Solution()
test.test_eq(sol.countBits(2), [0, 1, 1])
test.test_eq(sol.countBits(5), [0, 1, 1, 2, 1, 2])
