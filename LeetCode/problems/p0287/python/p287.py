#[IN PROGRESS]

class Solution(object):
    def findDuplicate(self, nums):
        prod = 1
        for n in nums:
            prod *= n
        for d in range(2, len(nums)):
            if prod % d == 0:
                prod /= d
        if prod == 1:
            return 1
        for d in range(2, len(nums)):
            if prod % d == 0:
                return d
        return -1   # Shouldn't get here unless there are no duplicates

# Solution can contain multiple of the same duplicate
sol = Solution()
print(sol.findDuplicate([1,3,4,2,2]), "expected 2")
print(sol.findDuplicate([2,2,2,2]), "expected 2")
print(sol.findDuplicate([2,2,2,2,2]), "expected 2")
print(sol.findDuplicate([2,2,2,2,2,2,2,2,2,2,2,2,2]), "expected 2")
print(sol.findDuplicate([1,1]), "expected 1")
print(sol.findDuplicate([1,4,4,2,4]), "expected 2")
