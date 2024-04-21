#[COMPLETED]

from typing import List
from collections import defaultdict

from main import UTest

class Solution:
    def rearrangeBarcodes(self, barcodes: List[int]) -> List[int]:
        if len(barcodes) <= 1:
            return barcodes
        counts = defaultdict(int)
        for b in barcodes:
            counts[b] += 1
        max_count = 0
        max_b = 0
        for b, c in counts.items():
            if c > max_count:
                max_count = c
                max_b = b
        everything_else = [b for b in barcodes if b != max_b]
        arranged = [max_b] * max_count
        # Split everything once
        i = 1
        while i <= len(arranged) and len(everything_else) != 0:
            arranged.insert(i, everything_else.pop(0))
            i += 2
        # Add everything else
        def can_add_at(i, b):
            if i == 0:
                return arranged[i] != b
            elif i == len(arranged):
                return arranged[-1] != b
            else:
                return arranged[i] != b and arranged[i-1] != b
        i = 0
        while len(everything_else) != 0:
            if can_add_at(i, everything_else[0]):
                arranged.insert(i, everything_else.pop(0))
            i += 1
        return arranged


def check(lst):
    for i in range(len(lst) - 1):
        if lst[i] == lst[i+1]:
            return False
    return True

sol = Solution()
test = UTest()

test.test_true(check(sol.rearrangeBarcodes([1,1,1,2,2,2])))
test.test_true(check(sol.rearrangeBarcodes([1,1,1,1,2,2,3,3])))
test.test_true(check(sol.rearrangeBarcodes([3,7,3,7,7,7,7,2,2,2])))
test.test_true(check(sol.rearrangeBarcodes([1])))
test.test_true(check(sol.rearrangeBarcodes([1,1,2])))
test.test_true(check(sol.rearrangeBarcodes([4,3,8,4,4,4,8,3,3,3])))
