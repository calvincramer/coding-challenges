#[COMPLETED]

from typing import List

# All numbers are distinct
# All numbers point to some other numbers in the array
# Therefore all numbers will make a loop
# Go through all loops, clearing numbers as we go
# Visit each number at most twice, so O(1)

class Solution:
    def arrayNesting(self, nums: List[int]) -> int:
        max_steps = 0
        for i in range(len(nums)):
            if nums[i] == -1:
                continue
            cur_steps = 1
            cur_i = i
            while i != nums[cur_i]:
                cur_steps += 1
                next = nums[cur_i]
                nums[cur_i] = -1    # Clear current
                cur_i = next
            max_steps = max(max_steps, cur_steps)
            # Clear last place visited
            nums[cur_i] = -1
        return max_steps

sol = Solution()
print(sol.arrayNesting([5,4,0,3,1,6,2]), "expected 4")
