#[COMPLETED]

class Solution(object):
    """
    :type prices: List[int]
    :rtype: int
    """
    def maxProfit(self, prices):
        profit = 0
        for i in range(len(prices) - 1):
            if prices[i] < prices[i+1]:
                profit += prices[i+1] - prices[i]
        return profit


sol = Solution()
print(sol.maxProfit([7,1,5,3,6,4]), "expected 7")