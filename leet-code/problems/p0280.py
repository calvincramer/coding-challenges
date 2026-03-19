import main


class Solution:
    def wiggleSort(self, nums: list[int]) -> None:
        nums.sort()
        for i in range(1, len(nums) - 1, 2):
            nums[i], nums[i + 1] = nums[i + 1], nums[i]


sol = Solution()
test = main.UTest()

arr = [3, 5, 2, 1, 6, 4]
sol.wiggleSort(arr)
test.test_eq(arr, [3, 5, 1, 6, 2, 4])

arr = [6, 6, 5, 6, 3, 8]
sol.wiggleSort(arr)
test.test_eq(arr, [6, 6, 5, 6, 3, 8])
