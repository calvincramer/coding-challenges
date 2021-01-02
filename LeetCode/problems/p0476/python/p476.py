#[COMPLETED]

class Solution(object):
    """
    :type num: int
    :rtype: int
    """
    def findComplement(self, num):
        firstSet = 0
        copy = num
        while copy != 0:
            firstSet += 1
            copy = copy >> 1
        return (~num) & ((1 << firstSet) - 1)


sol = Solution()
print(sol.findComplement(5), "expected 2")
print(sol.findComplement(1), "expected 0")
