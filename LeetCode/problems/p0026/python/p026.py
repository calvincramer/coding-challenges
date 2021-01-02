#[COMPLETED]

class Solution:
    def removeDuplicates(self, nums) -> int:
        if len(nums) == 0:
            return 0
        num_unique = 0
        i = 0
        while True:
            save_i = i
            i += 1
            while i < len(nums) and nums[i] == nums[save_i]:
                i += 1
            nums[num_unique] = nums[save_i]
            num_unique += 1
            if i >= len(nums):
                break
        return num_unique

sol = Solution()
arr = [1,1,2,2,3,3,3,3,3,3]
print(sol.removeDuplicates(arr))
print(arr)