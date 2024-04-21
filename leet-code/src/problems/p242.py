#[COMPLETED]

class Solution:
    def isAnagram(self, s, t):
        if len(s) != len(t):
            return False
        occ = [0] * 26
        for c in s:
            index = ord(c) - ord('a')
            occ[index] = occ[index] + 1
        for c in t:
            index = ord(c) - ord('a')
            occ[index] = occ[index] - 1
            if occ[index] < 0:
                return False
        return True

s = Solution()
print(s.isAnagram("abcd","dcbaz"))