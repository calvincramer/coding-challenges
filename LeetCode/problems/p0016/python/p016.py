#[COMPLETED]

class Solution:
    def threeSumClosest(self, nums, target: int) -> int:
        closest = nums[0] + nums[1] + nums[2]
        nums = sorted(nums)
        len_nums = len(nums)
        for i in range(len_nums):
            nums_i = nums[i]
            if i > 0 and nums_i == nums[i - 1]:
                continue
            if target <= 0 and nums_i > 0:
                break
            l = i + 1
            r = len_nums - 1
            while l < r:
                nums_l = nums[l]
                nums_r = nums[r]
                s = nums_i + nums_l + nums_r
                if s < target:
                    l += 1
                elif s > target:
                    r -= 1
                else:
                    closest = s
                    return closest
                if abs(s - target) < abs(closest - target):
                    closest = s
        return closest


sol = Solution()
print(sol.threeSumClosest([0,1,2], 3), "expected ?")
