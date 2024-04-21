import main


class Solution:
    def matchLevel(self, s: str, l, r) -> str:
        # Match left char with right char preserving level
        while True:
            removed = False
            for i in range(len(s)):
                if s[i] != l:
                    continue
                leftIdx = i
                j = i + 1
                while True:
                    if j >= len(s):
                        break
                    elif s[j] == l:
                        leftIdx = j
                    elif s[j] == r:
                        break
                    j += 1
                if j < len(s):  # Remove match
                    s = s[0:leftIdx] + s[leftIdx + 1 : j] + s[j + 1 :]
                    removed = True
                    break
            if removed is False:
                break
        return s

    def matchSimple(self, s: str, l, r) -> str:
        # Match left char with right char at any level, removing matches
        i = 0
        while i < len(s):
            if s[i] != l:
                i += 1
                continue
            j = i + 1
            while j < len(s) and s[j] != r:
                j += 1
            if j < len(s):
                s = s[0:i] + s[i + 1 : j] + s[j + 1 :]
                continue
            i += 1
        return s

    def checkValidString(self, s: str) -> bool:
        # O(n^2)
        s = self.matchLevel(s=s, l="(", r=")")  # Find matches and remove them, ignoring stars
        if len(s) == 0:
            return True
        if s[0] == ")" or s[-1] == "(":
            return False
        # Try match with stars
        s = self.matchSimple(s, l="(", r="*")
        s = self.matchSimple(s, l="*", r=")")
        s = s.replace("*", "")  # Any extra
        return len(s) == 0  # Everything should match


sol = Solution()
test = main.UTest()
test.test_eq(sol.checkValidString("()"), True)
test.test_eq(sol.checkValidString("(*)"), True)
test.test_eq(sol.checkValidString("(*))"), True)
test.test_eq(sol.checkValidString("())"), False)
test.test_eq(sol.checkValidString("(()"), False)
test.test_eq(sol.checkValidString("(("), False)
test.test_eq(sol.checkValidString("))"), False)
test.test_eq(sol.checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"), False)
test.test_eq(sol.checkValidString("****(("), False)
test.test_eq(sol.checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()"), True)
# Reduced "*****)*)(****"
