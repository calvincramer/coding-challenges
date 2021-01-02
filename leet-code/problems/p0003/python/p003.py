#[COMPLETED]

class Solution(object):
    """
    :type s: str
    :rtype: int
    """
    def lengthOfLongestSubstring(self, s):
        max_substr_len = 0
        chars_seen = {}     # Map from unique char seen to index
        start_substr_ind = 0
        for i,c in enumerate(s):
            if c in chars_seen and chars_seen[c] >= start_substr_ind:
                start_substr_ind = chars_seen[c] + 1
            chars_seen[c] = i
            if i - start_substr_ind + 1 > max_substr_len:
                max_substr_len = i - start_substr_ind + 1
        return max_substr_len


sol = Solution()
print(sol.lengthOfLongestSubstring("abcabcbb"), "expected 3")
print(sol.lengthOfLongestSubstring("bbbbb"), "expected 1")
print(sol.lengthOfLongestSubstring("pwwkew"), "expected 3")
print(sol.lengthOfLongestSubstring("aab"), "expected 2")
print(sol.lengthOfLongestSubstring("dvdf"), "expected 3")
