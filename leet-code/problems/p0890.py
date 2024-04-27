#[COMPLETED]

from typing import List

class Solution:
    def findAndReplacePattern(self, words: List[str], pattern: str) -> List[str]:
        ret = []
        for word in words:
            if len(word) != len(pattern):
                continue
            word_map = {}
            mapped_chars = set()
            good_map = True
            for char_p, char_w in zip(pattern, word):
                if char_p in word_map and word_map[char_p] != char_w:
                    good_map = False
                    break
                if char_p not in word_map and char_w in mapped_chars:
                    good_map = False
                    break
                word_map[char_p] = char_w
                mapped_chars.add(char_w)
            if good_map:
                ret.append(word)
        return ret


sol = Solution()
print(sol.findAndReplacePattern(words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"), "expected ['mee', 'aqq']")
