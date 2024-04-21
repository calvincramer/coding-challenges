#[COMPLETED]

from typing import List

class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        if len(words) == 0:
            return []
        words_dict = {}
        current = {}
        num_words = len(words)
        word_len = len(words[0])
        results = []
        num_matching = 0
        previous_moves = [None] * num_words
        i = 0
        begin_matching_i = 0
        is_beginning = True
        for word in words:
            current[word] = 0
            if word in words_dict:
                words_dict[word] += 1
            else:
                words_dict[word] = 1

        # To easily keep track of num_matching
        def update_current(word, value, n):
            if current[word] == value:
                return
            matched_before = current[word] == words_dict[word]
            current[word] = value
            matched_after = current[word] == words_dict[word]
            if matched_before and not matched_after:
                n -= 1
            elif not matched_before and matched_after:
                n += 1
            return n

        while i + word_len <= len(s):
            sub = s[i:i+word_len]
            # Add new word
            if sub in words_dict:
                if is_beginning:
                    is_beginning = False
                    begin_matching_i = i
                num_matching = update_current(sub, current[sub] + 1, num_matching)
                previous_moves.append(sub)
            else:
                # previous_moves.append(None)
                # Forget all progress and reset, go to beginning of pattern plus one char
                num_matching = 0
                previous_moves = [None] * num_words
                for word in words:
                    current[word] = 0
                if is_beginning is False:
                    is_beginning = True
                    i = begin_matching_i + 1
                else:
                    i += 1
                continue
            # Pop old word
            forget = previous_moves.pop(0)
            if forget is not None:
                num_matching = update_current(forget, current[forget] - 1, num_matching)

            # Check if pass
            if num_matching == len(words_dict):
                beginning_char = i - ((num_words - 1) * word_len)
                results.append(beginning_char)    # Start of word group
                # Forget and reset to beginning character + 1
                num_matching = 0
                previous_moves = [None] * num_words
                for word in words:
                    current[word] = 0
                i = beginning_char + 1
                is_beginning = True
                begin_matching_i = i
            else:
                i += word_len
        return results


print("Hello world")
sol = Solution()
print(sol.findSubstring("barfoothefoobarman", ["foo","bar"]), "expected [0,9]")
print(sol.findSubstring("wordgoodgoodgoodbestword", ["word", "good", "best", "word"]), "expected []")
print(sol.findSubstring("barfoofoobarthefoobarman", ["bar","foo","the"]), "expected ?")
print(sol.findSubstring("barfoofoobarthefoobarman", ["bar","foo","the"]), "expected [6,9,12]")
print(sol.findSubstring("wordgoodgoodgoodbestword", ["word","good","best","good"]), "expected [8]")
print(sol.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", ["fooo","barr","wing","ding","wing"]), "expected [13]")
print(sol.findSubstring("aaaaaaaa", ["aa","aa","aa"]), "expected [0,1,2]")
print(sol.findSubstring("ababaab", ["ab","ba","ba"]), "expected [1]")
