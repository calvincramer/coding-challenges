#[COMPLETED]

class Solution(object):
    """
    :type nums: List[int]
    :rtype: List[int]
    """
    def findDisappearedNumbers(self, nums):
        not_present = list(range(1, len(nums) + 1))
        for n in nums:
            not_present[n-1] = 0
        real_not_present = []
        for n in not_present:
            if n != 0:
                real_not_present.append(n)
        return real_not_present


sol = Solution()
print(sol.findDisappearedNumbers([4,3,2,7,8,2,3,1]), "expected [5,6]")
