#[COMPLETED]

class Solution(object):
    """
    :type s: str
    :rtype: str
    """
    def reverseVowels(self, s):
        vowels = []
        vowel_inds = []
        for i,c in enumerate(s):
            if c.lower() in "aeiou":
                vowels.append(c)
                vowel_inds.append(i)
        vowel_inds = vowel_inds[::-1]
        rev_s = s
        for i,vi in enumerate(vowel_inds):
            rev_s = rev_s[0:vi] + vowels[i] + rev_s[vi+1: len(rev_s)]
        return rev_s


sol = Solution()
print(sol.reverseVowels("hello"), "expected holle")
print(sol.reverseVowels("leetcode"), "expected leotcede")
print(sol.reverseVowels("aA"), "expected Aa")

# Should do in a better way, like setting the characters in an array rather
# than splitting string each time