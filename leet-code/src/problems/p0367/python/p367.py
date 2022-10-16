#[COMPLETED]

class Solution(object):
    """
    :type num: int
    :rtype: bool
    """
    def isPerfectSquare(self, num):
        return int(num ** 0.5) ** 2 == num
