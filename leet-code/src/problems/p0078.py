#[COMPLETED]

from itertools import combinations

class Solution(object):
    """
    :type nums: List[int]
    :rtype: List[List[int]]
    """
    def subsets(self, nums):
        powerset = []
        for length in range(0, len(nums) + 1):
            for comb in combinations(nums, length):
                powerset.append(list(comb))
        return powerset

    # Used combinations, but how to make combinations(list, n) myself?


sol = Solution()
out = sol.subsets([1,2,3])
print("[")
for l in out:
    print(l)
print("]")
