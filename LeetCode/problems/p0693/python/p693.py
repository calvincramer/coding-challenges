#[COMPLETED]

class Solution:
	def hasAlternatingBits(self, n):
		"""
		:type n: int
		:rtype: bool
		"""
		cur = n % 2
		n = n // 2
		while n != 0:
			next = n % 2
			if cur == next:
				return False
			cur = next
			n = n // 2

		return True


s = Solution()
print("")
print(s.hasAlternatingBits(5))
print("")
print(s.hasAlternatingBits(7))
print("")
print(s.hasAlternatingBits(11))
print("")
print(s.hasAlternatingBits(10))