#[IN PROGRESS]

# Too slow
# Maybe can solve by merging two at a time?
# Or can solve by just iterating over every row
# for each row keep sum (location of split)
# keep track of each location of split, keep dictionary of number of times split occurs (increment)
# O(number of bricks)

class Solution:
    def leastBricks(self, wall) -> int:
        nums = [row[0] for row in wall]
        indices = [0] * len(wall)
        min_crossings = len(wall)
        while True:
            min_brick = min(nums)
            nums = [n - min_brick for n in nums]
            crossings = len(nums) - nums.count(0)
            for i, n in enumerate(nums):
                if n == 0:
                    indices[i] += 1
                    if indices[i] >= len(wall[i]):  # Stop when get to end of one row
                        return min_crossings
                    nums[i] = wall[i][indices[i]]
            min_crossings = min(min_crossings, crossings)

print("Hello?")
sol = Solution()
wall = [[1,2,2,1],
        [3,1,2],
        [1,3,2],
        [2,4],
        [3,1,2],
        [1,3,1,1]]
print(sol.leastBricks(wall), "expected 2")

wall = [[1, 1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]]
print(sol.leastBricks(wall), "expected 0")





















