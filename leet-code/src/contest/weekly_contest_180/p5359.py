from typing import List

class Solution:
    def maxPerformance(self, n: int, speed: List[int], efficiency: List[int], k: int) -> int:
        if k == 1:
            single = [speed[i] * efficiency[i] for i in range(len(speed))]
            return max(single)
