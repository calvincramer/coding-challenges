#[COMPLETED]

from typing import List

class Solution:
    def summaryRanges(self, nums: List[int]) -> List[str]:
        ret = []
        i = 0
        len_n = len(nums)
        while i < len_n:
            j = i+1
            while j < len_n and nums[j] == nums[j-1] + 1:
                j += 1
            if j - i > 1:
                ret.append( str(nums[i]) + "->" + str(nums[j-1]) )
            else:
                ret.append(str(nums[i]))
            i = j
        return ret


sol = Solution()
print(sol.summaryRanges([0,1,2,4,5,7]))
