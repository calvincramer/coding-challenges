#[COMPLETED]

import time

class Solution:
    def longestPalindrome(self, s: str) -> str:
        max_palindrome = (0, 0)     # Range representing the maximum length palindrome
        max_palindrome_length = 0
        candidates = list()     # Candidate ranges stored in inclusive - exclusive format
                                # All candidates ARE valid palindromes
        len_s = len(s)              # Speed
        append = candidates.append  # Speed
        pop = candidates.pop        # Speed
        for i in range(len(s)):
            append( (i, i+1) )
        for i in range(len(s) - 1):
            if s[i] == s[i+1]:
                append( (i, i+2) )
        while candidates:
            low, high = pop()
            length = high - low
            if length > max_palindrome_length:
                max_palindrome_length = length
                max_palindrome = (low, high)
            if low > 0 and high < len_s:
                low -= 1
                high += 1
                if s[low] == s[high - 1]:
                    append( (low, high) )
        return s[max_palindrome[0]: max_palindrome[1]]


sol = Solution()
# print(sol.isPal("abc", 0, 3), "expected False")
# print(sol.isPal("abcba", 0, 5), "expected True")
# print(sol.isPal("abcbaabcba", 0, 10), "expected True")
# print(sol.isPal("abcbaabcbaa", 0, 11), "expected False")
print(sol.longestPalindrome("babad"), "expected bab or aba")
print(sol.longestPalindrome("cbbd"), "expected bb")
print(sol.longestPalindrome("bb"), "expected bb")
print(sol.longestPalindrome("cbbd"), "expected bb")
long_str = "321012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210012321001232100123210123210012321001232100123210123"
start_t = time.time()
print(sol.longestPalindrome(long_str), "expected ?")
print("long test case time: ", time.time() - start_t)

# How to make faster?
# Start with start point for each palindrome, grow by one character each side
#   Starting seed can either be single character or two characters (same character)

# Old slow brute force
# class Solution:
#     def longestPalindrome(self, s: str) -> str:
#         # Brute force O(len(s)^3)
#         if len(s) == 0:
#             return ""
#         maxp = 1
#         maxs = s[0]
#         len_s = len(s)      # Store for speed
#         isPal = self.isPal  # Store function reference for speed
#         for start in range(0, len_s - 1):
#             for l in range(1, len_s - start + 1):
#                 # temp = s[start: start + l]
#                 # mid = l // 2
#                 # lower = s[:mid]
#                 # if l % 2 == 1:
#                 #     mid += 1
#                 # rev = temp[mid:len(temp)]
#                 # rev = rev[::-1]
#                 if isPal(s, start, l):
#                 # if lower == rev:
#                     if l > maxp:
#                         maxp = l
#                         maxs = s[start: start+l]
#         return maxs
#
#     def isPal(self, s, start, length):
#         s = s[start: start + length]
#         mid = length // 2
#         l = s[:mid]
#         if length % 2 == 1:
#             mid += 1
#         r = s[mid:len(s)]
#         r = r[::-1]
#         return l == r