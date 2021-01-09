#[COMPLETED]

from typing import List

class Solution:
    def compress(self, chars: List[str]) -> int:
        place_i = 0
        i = 0
        len_chars = len(chars)
        while i < len_chars:
            end_i = i + 1
            char_i = chars[i]
            while end_i < len_chars and chars[end_i] == char_i:
                end_i += 1
            length = end_i - i
            chars[place_i] = char_i
            if length == 1:
                place_i += 1
            else:
                # Insert number
                place_i += 1
                for c in str(length):
                    chars[place_i] = c
                    place_i += 1
            i = end_i
        return place_i

def do(l, exp):
    sol = Solution()
    res = sol.compress(l)
    print(res, "expected ", exp)
    print(l)
    print()

do(["a","a","b","b","c","c","c"], 6)
do(["a"], 1)
do(["a","b","b","b","b","b","b","b","b","b","b","b","b"], 4)

