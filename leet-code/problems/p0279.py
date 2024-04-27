#[IN PROGRESS]

class Solution:
	def numSquares(self, n):
		"""
		:type n: int
		:rtype: int
		"""
		opt = [n]*(n+1)		#default n or large number, since we are minimizing\
		opt[0] = 0
		length = n+1
		n = 1	#fill in squares
		while n**2 < length:
			opt[n**2] = 1
			n = n+1
		#print(opt)

		squares = [1]
		squareIndex = 1;	#to be added to squares

		for i in range(1, length):
			for square in squares:
				if i + square < length and opt[i] + 1 < opt[i+square]:
					opt[i+square] = opt[i] + 1
			#maybe add the next square?
			if (i + 1 < length) and opt[i+1] != 0:
				squareIndex = squareIndex + 1
				squares.append(squareIndex**2)
			#print(opt)
		return opt[length - 1]



s = Solution()
print(s.numSquares(12))
print(s.numSquares(13))
print(s.numSquares(7168))