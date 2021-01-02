#[COMPLETED]

class Solution:
	def shiftingLetters(self, S, shifts):
		"""
		:type S: str
		:type shifts: List[int]
		:rtype: str
		"""
		for i in range(0, len(shifts)):
			shifts[i] = shifts[i] % 26
		for i in range(len(shifts)-2, -1, -1):
			shifts[i] = shifts[i] + shifts[i+1]
		for i in range(0, len(shifts)):
			shifts[i] = shifts[i] % 26

		newS = [''] * len(S)
		for i in range(0, len(S)):
			#print(newS)
			nextCharCode = shifts[i] + ord(S[i])
			if nextCharCode > ord('z'):
				nextCharCode = nextCharCode - 26
			newS[i] = chr(nextCharCode)
		return ''.join(newS)


s = Solution()
print(s.shiftingLetters("abc", [3,5,9]))

chararr = ['a','b','c']
print(chararr)
print(''.join(chararr))