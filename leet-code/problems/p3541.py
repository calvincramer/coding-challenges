import main


class Solution:
    def maxFreqSum(self, s: str) -> int:
        ord_a = ord("a")

        idx_a = ord_a - ord_a
        idx_e = ord("e") - ord_a
        idx_i = ord("i") - ord_a
        idx_o = ord("o") - ord_a
        idx_u = ord("u") - ord_a

        freqs = [0] * (ord("z") - ord_a + 1)

        for c in s:
            freqs[ord(c) - ord_a] += 1

        max_vowel = max(freqs[idx_a], freqs[idx_e], freqs[idx_i], freqs[idx_o], freqs[idx_u])
        freqs[idx_a] = 0
        freqs[idx_e] = 0
        freqs[idx_i] = 0
        freqs[idx_o] = 0
        freqs[idx_u] = 0
        max_consonant = max(freqs)

        return max_vowel + max_consonant


sol = Solution()
test = main.UTest()
test.test_eq(sol.maxFreqSum("successes"), 6)
test.test_eq(sol.maxFreqSum("aeiaeia"), 3)
test.test_eq(sol.maxFreqSum("cz"), 1)
