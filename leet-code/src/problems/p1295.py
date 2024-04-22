#[COMPLETED]

from typing import List

class Solution:
    def findNumbers(self, nums: List[int]) -> int:
        return sum([1 if len(str(n)) % 2 == 0 else 0 for n in nums])
