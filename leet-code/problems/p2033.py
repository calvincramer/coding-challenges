import main


class Solution:
    def minOperations(self, grid: list[list[int]], x: int) -> int:
        nums = []
        for row in grid:
            for n in row:
                nums.append(n)
        nums = sorted(nums)

        # Possible? All numbers need to be in the same congruence class mod x
        classes = set()
        for n in nums:
            classes.add(n % x)
        if len(classes) != 1:
            return -1

        # Target number is median
        median = nums[len(nums) // 2]
        return sum(abs(median - n) // x for n in nums)


sol = Solution()
test = main.UTest()
test.test_eq(sol.minOperations(grid=[[2, 4], [6, 8]], x=2), 4)
test.test_eq(sol.minOperations(grid=[[1, 5], [2, 3]], x=1), 5)
test.test_eq(sol.minOperations(grid=[[1, 2], [3, 4]], x=2), -1)
