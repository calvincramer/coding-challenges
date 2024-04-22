#[COMPLETED]

from main import UTest


class Solution:
    def makeGood(self, s: str) -> str:
        while True:
            removed_something = False
            i = 0
            while i < len(s) - 1:
                res_1 = 0 if s[i].islower() else 1
                res_2 = 0 if s[i+1].islower() else 1
                if res_1 + res_2 == 1 and s[i].lower() == s[i+1].lower():
                    s = s[0:i] + s[i+2:]
                    removed_something = True
                else:
                    i += 1
            if not removed_something:
                break
        return s


sol = Solution()
test = UTest()
test.test_eq(sol.makeGood('leEeetcode'), 'leetcode')
test.test_eq(sol.makeGood('abBAcC'), '')
test.test_eq(sol.makeGood('s'), 's')
test.test_eq(sol.makeGood('mC'), 'mC')

