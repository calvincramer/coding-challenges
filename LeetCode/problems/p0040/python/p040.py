#[COMPLETED]

from typing import List

class Solution:
    def helper(self, nums: List[int], target: int, candidates: List[int], sol_set: List[List[int]]):
        # If target = 0, add solution, return
        if target == 0:
            sol_set.append(candidates)
            return
        if len(nums) == 0:
            return
        # If nums[0] <= target, try with nums[0]
        if target >= nums[0]:
            self.helper(nums[1:], target - nums[0], candidates + [nums[0]], sol_set)
        # Try without nums[0]
        self.helper(nums[1:], target, candidates, sol_set)


    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        sols = []
        self.helper(sorted(candidates), target, [], sols)
        unique = []
        for s in sols:
            if s not in unique:
                unique.append(s)
        return unique


sol = Solution()
print(sol.combinationSum2([10,1,2,7,6,1,5], 8), "expected [[1, 7], [1, 2, 5], [2, 6], [1, 1, 6]]")
print(sol.combinationSum2([2,5,2,1,2], 5), "expected [[1,2,2], [5]]")
