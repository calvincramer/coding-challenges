from typing import List


class Solution:
    def findSubarrays(self, nums: List[int]) -> bool:
        sums = dict()
        for i in range(len(nums) - 1):
            s = nums[i] + nums[i + 1]
            if s in sums:
                return True
            sums[s] = False
        return False


print(Solution().findSubarrays([4, 2, 4]), "expected True")
print(Solution().findSubarrays([1, 2, 3, 4, 5]), "expected False")
print(Solution().findSubarrays([0, 0, 0]), "expected True")
