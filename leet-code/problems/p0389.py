#[COMPLETED]

class Solution:
	def findTheDifference(self, s, t):
		"""
		:type s: str
		:type t: str
		:rtype: str
		"""
		occur = [0] * 26

		for c in s:
			index = ord(c) - ord('a')
			occur[index] = occur[index] + 1

		for c in t:
			index = ord(c) - ord('a')
			occur[index] = occur[index] - 1
			if occur[index] < 0:
				return c

		# no difference between s and t
		return "no difference"



print("Hello")
s = Solution()
print(s.findTheDifference("abcd", "abcde"))