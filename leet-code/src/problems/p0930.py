import main

from collections import Counter


class Solution:
    def numSubarraysWithSum(self, nums: list[int], goal: int) -> int:
        # https://leetcode.com/problems/binary-subarrays-with-sum/solutions/186683/c-java-python-sliding-window-o-1-space/
        # Sliding window
        return self.atMost(nums, goal) - self.atMost(nums, goal - 1)

    def atMost(self, nums, goal) -> int:
        if goal < 0:
            return 0
        res = 0
        i = 0
        for j in range(len(nums)):
            goal -= nums[j]
            while goal < 0:
                goal += nums[i]
                i += 1
            res += j - i + 1
        return res

    def numSubarraysWithSumHashMap(self, nums: list[int], goal: int) -> int:
        # HashMap solution
        # I don't understand this. https://leetcode.com/problems/binary-subarrays-with-sum/solutions/186683/c-java-python-sliding-window-o-1-space/
        counts = Counter()
        counts[0] = 1
        partial = 0
        res = 0
        for n in nums:
            partial += n
            res += counts[partial - goal]
            counts[partial] += 1
        return res


sol = Solution()
test = main.UTest()
test.test_eq(sol.numSubarraysWithSum(nums=[1, 0, 1, 0, 1], goal=2), 4)
test.test_eq(sol.numSubarraysWithSum([0, 0, 0, 0, 0], goal=0), 15)
