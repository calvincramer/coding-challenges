#[COMPLETED]

class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        prev_match = None
        while len(s) > 0 and len(p) > 0:
            if p[0] == '.':
                # Try to match with the characters first or try to consume the character and * in p
                if len(p) > 1 and p[1] == '*':
                    if self.isMatch(s, p[2:]):
                        return True
                prev_match = p[0]
                s = s[1:]
                p = p[1:]
            elif p[0] == '*':
                if prev_match is None or prev_match == '':
                    return False
                p = p[1:]
                for l in range(0, len(s) + 1):
                    if self.isMatch(s, (prev_match * l) + p):
                        return True
                return False
            else:
                if p[0] != s[0]:
                    # Last ditch effort to consume mismatch with a *
                    if len(p) > 1 and p[1] == '*':
                        p = p[2:]
                        prev_match = ''
                        continue
                    else:
                        return False
                # Try to match with the characters first or try to consume the character and * in p
                if len(p) > 1 and p[1] == '*':
                    if self.isMatch(s, p[2:]):
                        return True
                prev_match = p[0]
                s = s[1:]
                p = p[1:]

        # Last ditch effort to consume pattern to null string
        go_again = True
        while go_again:
            go_again = False
            if len(p) >= 1 and p[0] == '*':
                p = p[1:]
                go_again = True
            if len(p) >= 2 and p[1] == '*':
                p = p[2:]
                go_again = True
        return len(s) == 0 and len(p) == 0


if __name__ == "__main__":
    sol = Solution()
    print(sol.isMatch("aa", "a"), "expected False")
    print(sol.isMatch("aa", "a*"), "expected True")
    print(sol.isMatch("ab", ".*"), "expected True")
    print(sol.isMatch("aab", "c*a*b"), "expected True")
    print(sol.isMatch("a", "ab*"), "expected True")
    print(sol.isMatch("bbbba", ".*a*a"), "expected True")
    print(sol.isMatch("", ".*"), "expected True")
    print("Done")
