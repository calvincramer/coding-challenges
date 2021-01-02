#[COMPLETED]

class Solution:
    def wordPattern(self, pattern, str) -> bool:
        if len(pattern) != len(str.split()):
            return False
        map = {}
        patterns_already_mapped = set()
        for pattern_c, word in zip(pattern, str.split()):
            print(pattern_c, word)
            if word in map:
                # Check if word maps correctly
                if map[word] != pattern_c:
                    return False
            else:
                if pattern_c in patterns_already_mapped:
                    return False
                map[word] = pattern_c
                patterns_already_mapped.add(pattern_c)
        return True


sol = Solution()
print(sol.wordPattern("abba", "dog cat cat dog"))
