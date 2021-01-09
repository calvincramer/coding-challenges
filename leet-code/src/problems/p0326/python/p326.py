#[COMPLETED]

class Solution(object):
    """
    :type n: int
    :rtype: bool
    """
    def isPowerOfThree(self, n):
        if n <= 0:
            return False
        try_n = 1
        while try_n < n:
            try_n *= 3
        return try_n == n


sol = Solution()
for i in range(-2,300000):
    if sol.isPowerOfThree(i):
        print(i)
