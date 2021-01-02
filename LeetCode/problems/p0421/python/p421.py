#[COMPLETED]

# Credit https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/91049/Java-O(n)-solution-using-bit-manipulation-and-HashMap
class Solution:
    def findMaximumXOR(self, nums):
        max = 0
        mask = 0
        for i in range(31, -1, -1):
            mask = mask | (1 << i)
            s = set()
            for num in nums:
                s.add(num & mask)
            tmp = max | (1 << i)
            for prefix in s:
                if (tmp ^ prefix) in s:
                    max = tmp
                    break
        return max;


sol = Solution()
print(sol.findMaximumXOR([3, 10, 5, 25, 2, 8]), " expected 28")