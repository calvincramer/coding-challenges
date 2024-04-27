#[COMPLETED]

class Solution:
	def isPowerOfFour(self, num):
		"""
		:type num: int
		:rtype: bool
		"""
		if num <= 0:
			return False
		while num != 1:
			if num % 4 != 0:
				return False
			num = num // 4
		return True


s = Solution()
for n in range(0, 33):
	print(str(n) + "\t" + str(s.isPowerOfFour(n)))

n = 1
for i in range(0, 10):
	print("{0:b}".format(n))
	n = n *4