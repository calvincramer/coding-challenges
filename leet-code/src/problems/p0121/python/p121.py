#[COMPLETED]

class Solution:
    """
    :type prices: List[int]
    :rtype: int
    """
    def maxProfit(self, prices):
        if prices is None or len(prices) == 0:
            return 0
        maxP = 0
        lowestBuy = prices[0]
        for i in range(1, len(prices)):
            if prices[i] - lowestBuy > maxP:
                maxP = prices[i] - lowestBuy
            if prices[i] < lowestBuy:
                lowestBuy = prices[i]
        return maxP


# O(n^2) is easy
sol = Solution()
print(sol.maxProfit([7,1,5,3,6,4]), " expected 5")