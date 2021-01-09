#[COMPLETED]

class Solution:
    def freqAlphabets(self, s: str) -> str:
        ret = ""
        ord_0 = ord('0')
        ord_a = ord('a')
        ord_j = ord('j')
        i = 0
        lens_ = len(s)
        while i < lens_:
            val = ord(s[i]) - ord_0
            if (i + 2 < lens_ and s[i+2] != '#') or i + 2 >= lens_:
                ret += chr(ord_a + val - 1)
                i += 1
                continue    # Go over one char
            # Get pound sign map
            val = int(s[i: i+2]) - 10   # Zero the number
            ret += chr(ord_j + val)
            i += 3
        return ret


sol = Solution()
print(sol.freqAlphabets("10#11#12"), "expected jkab")
print(sol.freqAlphabets("1326#"), "expected acz")
