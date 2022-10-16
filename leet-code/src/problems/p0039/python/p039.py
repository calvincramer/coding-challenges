#[COMPLETED]

from typing import List

class Solution:
    # Nums is sorted
    def helper(self, nums: List[int], target: int) -> List[List[int]]:
        if target == 0:
            return set()
        if target < 0 or len(nums) == 0:
            return None
        if target < nums[0]:
            return None

        sol_set = []
        # Try with rest and with n * 0th element
        orig_num = nums[0]
        n = 1
        val = orig_num * n
        while val <= target:
            if val == target:
                sol_set.append( [orig_num] * n )  # Will only ever get one solution at a time
                break
            temp_set = self.helper(nums[1:], target - val)
            if temp_set:
                # Prepend n times orig_num to temp_set
                for lst in temp_set:
                    sol_set.append( ([orig_num] * n) + lst)
            n += 1
            val = orig_num * n
        # Try with rest and without 0th element
        if len(nums) > 0:
            rest_set = self.helper(nums[1:], target)
            if rest_set:
                sol_set.extend(rest_set)
        return sol_set


    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        ret = self.helper(sorted(candidates), target)
        return ret if ret else []



sol = Solution()
print(sol.combinationSum([2, 3, 6, 7], 7), "expected [ [7], [2,2,3] ]")
print(sol.combinationSum([2, 3, 5], 8), "expected [ [2,2,2,2], [2,3,3], [3,5] ]")
