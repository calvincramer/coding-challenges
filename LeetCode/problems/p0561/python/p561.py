#[COMPLETED]

from typing import List

def pair_iter(iterable_obj):
    it = iter(iterable_obj)
    while True:
        first = next(it, None)
        second = next(it, None)
        if first is None:
            if second is None:
                break
            else:
                yield first, second
                break
        yield first, second

class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        return sum([a for a,_ in pair_iter(sorted(nums))])


sol = Solution()
print(sol.arrayPairSum([1,4,3,2]))
