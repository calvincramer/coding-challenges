#[COMPLETED]

from typing import List

class Solution:
    def findRelativeRanks(self, nums: List[int]) -> List[str]:
        if not nums:
            return []
        medals = ["Gold Medal", "Silver Medal", "Bronze Medal"]
        for i in range(len(nums)):
            nums[i] = (nums[i], i)
        nums.sort(key= lambda x: -x[0])     # Sort according to score, reversed
        print(nums)
        # One indexed ranks (#1 is best)
        ret = [""] * len(nums)
        place = 1
        for score, index in nums:
            if place <= 3:
                ret[index] = medals.pop(0)
            else:
                ret[index] = str(place)
            place += 1
        return ret


sol = Solution()
print(sol.findRelativeRanks([5,4,3,2,1,6]))
