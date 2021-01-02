#[COMPLETED]

from typing import List

from main import UTest


class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        len_nums = len(nums)
        collected = []      # Sum adjacent ones and see if 0 gaps are one wide or larger
        i = 0
        # Fill out collected
        while i < len_nums:
            j = i + 1
            nums_i = nums[i]
            while j < len_nums and nums_i == nums[j]:
                j += 1
            if nums_i == 0:
                if j - i == 1:
                    collected.append(0)     # Can combine to join 1's
                else:
                    collected.append(-1)    # Length of 0's too big to join
            else:
                collected.append(j - i)
            i = j
        # Find answer
        if len(collected) == 1:
            if collected[0] > 0:
                return collected[0] - 1
            else:
                return max(0, collected[0])
        elif len(collected) == 2:
            return max(collected)
        else:
            max_ = max(collected)
            for i in range(len(collected) - 2):
                if collected[i+1] == 0:
                    temp = collected[i] + collected[i+2]
                    max_ = max(max_, temp)
            return max_


sol = Solution()
test = UTest()
test.test_eq(sol.longestSubarray([1,1,0,1]), 3)
test.test_eq(sol.longestSubarray([0,1,1,1,0,1,1,0,1]), 5)
test.test_eq(sol.longestSubarray([1,1,1]), 2)
test.test_eq(sol.longestSubarray([1,1,0,0,1,1,1,0,1]), 4)
test.test_eq(sol.longestSubarray([0,0,0]), 0)
