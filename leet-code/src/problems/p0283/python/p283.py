#[COMPLETED]

class Solution(object):
    """
    :type nums: List[int]
    :rtype: None Do not return anything, modify nums in-place instead.
    """
    def moveZeroes(self, nums):
        i = 0
        place_at = 0
        num_zeros = 0
        # len(nums) = place_at + num_zeros
        while i < len(nums):
            if nums[i] == 0:
                i += 1
                num_zeros += 1
                continue
            nums[place_at] = nums[i]
            i += 1
            place_at += 1
        for j in range(len(nums) - num_zeros, len(nums)):
            nums[j] = 0


sol = Solution()
arr = [0,1,0,3,12]
print(arr)
sol.moveZeroes(arr)
print(arr)
print("expected [1,3,12,0,0]")
