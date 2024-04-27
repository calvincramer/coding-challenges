#[COMPLETED]

class Solution:
    def reverseWords(self, s):
        words = s.split(" ")
        return " ".join([word[::-1] for word in words])


sol = Solution()
s = "Let's take LeetCode contest"
print(s, "\n", sol.reverseWords(s), sep="")