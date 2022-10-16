#[COMPLETED]

class Solution:
    def singleNumber(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        d = {}
        for n in nums:
            print(d)
            if n not in d:
                d[n] = 1
            else:
                d[n] = 2
        for key in d:
            if d[key] == 1:
                return key
        return "FAIL"

s = Solution()
arr = [1,1,2,2,3]
print(s.singleNumber(arr))