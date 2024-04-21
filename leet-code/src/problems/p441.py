#[COMPLETED]

class Solution:
	def arrangeCoins(self, n):
		"""
		:type n: int
		:rtype: int
		"""
		r = 1
		while n >= r:
			n = n - r
			r = r + 1
		return r-1

s = Solution()
print(s.arrangeCoins(5))
print(s.arrangeCoins(8))