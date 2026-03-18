import main


class Solution:
    def longestPrefix(self, s: str) -> str:
        n = len(s)
        lps = [0] * n

        # length of the previous longest prefix suffix
        _len = 0
        i = 1

        while i < n:
            if s[i] == s[_len]:
                # match
                _len += 1
                lps[i] = _len
                i += 1
            else:
                if _len != 0:
                    # fall back in the pattern
                    _len = lps[_len - 1]
                else:
                    lps[i] = 0
                    i += 1

        return s[0 : lps[n - 1]]


sol = Solution()
test = main.UTest()
test.test_eq(sol.longestPrefix("level"), "l")
test.test_eq(sol.longestPrefix("ababab"), "abab")
