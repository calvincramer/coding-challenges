#[COMPLETED]

class Solution:
    def removeDuplicates(self, nums):
        if len(nums) == 0:
            return
        j = 0   # index to place
        count_same = 1
        n = nums[0]
        for i in range(1, len(nums)):
            if nums[i] == n:
                count_same += 1
            else:
                count_same = 1
                n = nums[i]
            if count_same <= 2:
                j += 1
            nums[j] = nums[i]
        return j+1


sol = Solution()
l = [1,1,1,2,2,3]
print(sol.removeDuplicates(l))
print(l)
