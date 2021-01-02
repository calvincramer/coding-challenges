#[COMPLETED]

class Solution:
	def monotoneIncreasingDigits(self, N):
		"""
		:type N: int
		:rtype: int
		"""
		N = list(str(N))
		# from second to last digits (rightmost), subtract one from digits, make all digits to the right 
		# a 9, check if monotone increasing, if not, do same from digit to the left
		index = len(N) - 2

		while index >= 0:
			if ord(N[index]) > ord(N[index+1]):
				N[index] = chr(ord(N[index]) - 1)
				for j in range(index + 1, len(N)):
					N[j] = '9'
			index = index - 1

		return int(''.join(N))



string = list("abcd")
print(string[len(string) - 2])
string[-1] = chr(ord(string[-1]) + 1)
print(string)
string = ''.join(string)
print(string)
print("")

s = Solution()
print(s.monotoneIncreasingDigits(332))