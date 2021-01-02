#[COMPLETED]

class Solution(object):
    """
    :type nums: List[int]
    :rtype: bool
    """
    def containsDuplicate(self, nums):
        d = {}
        for n in nums:
            if n in d:
                return True
            d[n] = None
        return False


sol = Solution()
print(sol.containsDuplicate([1,2,3,1]), "expected True")
print(sol.containsDuplicate([1,2,3,4]), "expected False")
