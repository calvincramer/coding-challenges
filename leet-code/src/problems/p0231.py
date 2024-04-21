#[COMPLETED]

class Solution(object):
    """
    :type n: int
    :rtype: bool
    """
    def isPowerOfTwo(self, n):
        #Use bit masking
        if n <= 0:
            return False
        while n & 1 != 1:
            n = n >> 1
        return n == 1


    def isPowerOfTwoEasy(self, n):
        # Simple math solution
        guess = 1
        while guess < n:
            guess *= 2
        return guess == n


sol = Solution()
for i in range(100):
    print(i, sol.isPowerOfTwo(i))
    assert sol.isPowerOfTwoEasy(i) == sol.isPowerOfTwo(i)
