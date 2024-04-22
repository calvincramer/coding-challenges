#[COMPLETED]

class Solution(object):
    """
    :type nums: List[int]
    :rtype: int
    """
    def majorityElement(self, nums):
        d = {}
        for n in nums:
            if n not in d:
                d[n] = 1
            else:
                d[n] += 1
            if d[n] > len(nums) // 2:
                return n
        return None     # Doesn't have majority element


sol = Solution()
print(sol.majorityElement([3,2,3]), "expected 3")
print(sol.majorityElement([2,2,1,1,1,2,2]), "expected 2")
print(sol.majorityElement([1]), "expected 1")
