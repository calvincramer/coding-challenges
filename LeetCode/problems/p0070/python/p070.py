#[COMPLETED]

class Solution(object):
    """
    :type n: int
    :rtype: int
    """
    def climbStairs(self, n):
        prev = 1
        cur = 1
        for level in range(1, n):
            next = cur + prev
            prev = cur
            cur = next
        return cur

