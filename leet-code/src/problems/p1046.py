#[COMPLETED]

from typing import List

from main import UTest


class Solution:
    def lastStoneWeight(self, stones: List[int]) -> int:
        from queue import PriorityQueue
        q = PriorityQueue()
        for stone in stones:
            q.put(stone * -1)
        while q.qsize() > 1:
            s1 = q.get()
            s2 = q.get()
            if s1 == s2:
                continue
            else:
                q.put(s1 - s2)

        return q.get() * -1 if q.qsize() > 0 else 0


sol = Solution()
test = UTest()
test.test_eq(sol.lastStoneWeight([2, 7, 4, 1, 8, 1]), 1)
test.test_eq(sol.lastStoneWeight([2, 2]), 0)
