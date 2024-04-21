#[COMPLETED]

class Solution:
    # Binary search on sorted list nums
    def searchInsert(self, nums, target) -> int:
        l = 0
        r = len(nums) - 1
        m = (r-l)//2
        while l < r and nums[m] != target:
            if nums[m] < target:    # Go to right side
                l = m + 1
            else:   # Go to left side
                r = m - 1
            m = (r + l) // 2
        if l >= r:
            return l+1 if nums[l] < target else l
        return m


sol = Solution()
print(sol.searchInsert([1,3,5,6], 5), " expected 2")
print(sol.searchInsert([1,3,5,6], 2), " expected 1")
print(sol.searchInsert([1,3,5,6], 7), " expected 4")
print(sol.searchInsert([1,3,5,6], 0), " expected 0")
print(sol.searchInsert([1,3], 0), " expected 0")