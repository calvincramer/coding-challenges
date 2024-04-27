import main


class Solution:
    def reverseOnlyLetters(self, s: str) -> str:
        rev = [c for c in s if c.isalpha()]
        rev.reverse()
        s = list(s)
        for i in range(len(s)):
            if s[i].isalpha():
                s[i] = rev[0]
                rev = rev[1:]
        return "".join(s)


test = main.UTest()
sol = Solution()
test.test_eq(sol.reverseOnlyLetters("ab-cd"), "dc-ba")
test.test_eq(sol.reverseOnlyLetters("a-bC-dEf-ghIj"), "j-Ih-gfE-dCba")
test.test_eq(sol.reverseOnlyLetters("Test1ng-Leet=code-Q!"), "Qedo1ct-eeLg=ntse-T!")
