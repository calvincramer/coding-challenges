#[COMPLETED]

from typing import List

from main import UTest


class Solution:
    def jump(self, nums: List[int]) -> int:
        len_n = len(nums)
        max_jump_num = len_n * 9999     # Some large number
        jump_nums = [max_jump_num] * len_n        # min jump nums

        for i in range(len_n - 1, -1, -1):
            if i == len(nums) - 1:
                jump_nums[i] = 0
            elif nums[i] == 0:
                nums[i] = max_jump_num
            else:   # Find min jump to
                jump_nums[i] = min(jump_nums[i+1: min(len_n, i+nums[i]+1)]) + 1   # Add one jump
        return jump_nums[0]


sol = Solution()
test = UTest()
test.test_true(sol.jump([2, 3, 1, 1, 4]) == 2)
