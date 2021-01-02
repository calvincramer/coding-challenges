#[COMPLETED]

import math

class Solution:
    def constructRectangle(self, area: int):
        l = int(math.sqrt(area)) # Root
        while area % l != 0:
            l -= 1
        return [int(area / l), l]

sol = Solution()
print(sol.constructRectangle(15))
