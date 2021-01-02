#[IN PROGRESS]

from typing import List

class Solution:
    def leastInterval(self, tasks: List[str], n: int) -> int:
        from collections import defaultdict
        count = defaultdict(int)
        for t in tasks:
            count[t] += 1
