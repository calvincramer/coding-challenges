#[COMPLETED]

class Solution:
	def canConstruct(self, ransomNote, magazine):
		"""
		:type ransomNote: str
		:type magazine: str
		:rtype: bool
		"""
		noteOccur = [0] * 26
		magOccur =  [0] * 26
		for char in ransomNote:
			index = ord(char) - ord('a')
			noteOccur[index] = noteOccur[index] + 1
		for char in magazine:
			index = ord(char) - ord('a')
			magOccur[index] = magOccur[index] + 1

		for i in range(0, 26):
			if noteOccur[i] > magOccur[i]:
				return False
		return True

note = "aa"
mag = "aab"
s = Solution()
print(s.canConstruct(note, mag))