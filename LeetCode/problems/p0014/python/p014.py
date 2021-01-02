#[COMPLETED]

class Solution(object):
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        if len(strs) == 0:
            return ""
        min_len = min([len(s) for s in strs])
        for i in range(min_len):
            for j in range(1, len(strs)):
                if strs[0][i] != strs[j][i]:
                    return strs[0][:i]
        return strs[0][:min_len]


sol = Solution()
print(sol.longestCommonPrefix(["flower","flow","flight"]), "expected 'fl'")
print(sol.longestCommonPrefix(["dog","racecar","car"]), "expected ''")
