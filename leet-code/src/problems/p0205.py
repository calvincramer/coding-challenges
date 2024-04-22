#[COMPLETED]

class Solution:
    def isIsomorphic(self, s, t) -> bool:
        if len(s) != len(t):
            return False
        map = {}
        t_already_mapped = set()
        for i in range(0, len(s)):
            s_char = s[i]
            t_char = t[i]
            if s_char not in map:
                if t_char in t_already_mapped:
                    return False
                map[s_char] = t_char
                t_already_mapped.add(t_char)
                continue
            if map[s_char] != t_char:
                return False
        return True

s = Solution()
print(s.isIsomorphic("egg", "add"), "expected True")
print(s.isIsomorphic("foo", "bar"), "expected False")
print(s.isIsomorphic("paper", "title"), "expected True")
print(s.isIsomorphic("ab", "aa"), "expected False")
