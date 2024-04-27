#[COMPLETED]

from typing import List
from bisect import bisect

class Solution:
    def f(self, s: str) -> int:
        min_char = float("inf")
        for c in s:
            min_char = min(min_char, ord(c))
        return s.count(chr(min_char))

    def numSmallerByFrequency(self, queries: List[str], words: List[str]) -> List[int]:
        ret = [0] * len(queries)
        # Apply f to words, and queries
        queries = list(map(self.f, queries))
        words = list(sorted(map(self.f, words)))    # Sort
        for i in range(len(ret)):
            ind = bisect(words, queries[i])  # Find number of nums < queries[i]
            ret[i] = len(words) - ind
        return ret


sol = Solution()

q = ["bbb","cc"]
w = ["a","aa","aaa","aaaa"]
print(sol.numSmallerByFrequency(q, w), "expected [1,2]")

q = ["cbd"]
w = ["zaaaz"]
print(sol.numSmallerByFrequency(q, w), "expected [1]")

q = ["bba","abaaaaaa","aaaaaa","bbabbabaab","aba","aa","baab","bbbbbb","aab","bbabbaabb"]
w = ["aaabbb","aab","babbab","babbbb","b","bbbbbbbbab","a","bbbbbbbbbb","baaabbaab","aa"]
print(sol.numSmallerByFrequency(q, w), "expected [6,1,1,2,3,3,3,1,3,2]")
