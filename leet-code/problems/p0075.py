#[COMPLETED]

class Solution(object):
    """
    :type nums: List[int]
    :rtype: None Do not return anything, modify nums in-place instead.
    """
    def sortColors(self, nums):
        color_count = [0] * 3
        for c in nums:
            color_count[c] += 1
        ind = 0
        for c in range(3):
            for i in range(0, color_count[c]):
                nums[ind] = c
                ind += 1


sol = Solution()
arr = [2,0,2,1,1,0]
print(arr)
sol.sortColors(arr)
print(arr)