#[COMPLETED]

class Solution(object):
    """
    :type nums: List[int]
    :type k: int
    :rtype: bool
    """
    def containsNearbyDuplicate(self, nums, k):
        d = {}  #
        for i,n in enumerate(nums):
            if n not in d:
                d[n] = i
            elif i - d[n] <= k:
                return True
            else:
                d[n] = i
        return False

    """
    :type nums: List[int]
    :type k: int
    :rtype: bool
    """
    def containsNearbyDuplicateOld(self, nums, k):
        for i in range(min(len(nums) - 1, k), len(nums)):
            for j in range(max(i - k, 0), min(i + k + 1, len(nums))):
                if i != j and nums[i] == nums[j]:
                    return True
        return False


sol = Solution()
print(sol.containsNearbyDuplicate([1,2,3,1,2,3], 2), "expected False")
print(sol.containsNearbyDuplicate([1,2,3,1,2,3], 3), "expected True")