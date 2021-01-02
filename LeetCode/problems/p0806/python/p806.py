#[COMPLETED]

class Solution:
    def numberOfLines(self, widths, S: str):
        width_map = {}
        for n, width in enumerate(widths):
            c = chr(ord('a') + n)
            width_map[c] = width

        lines = 0
        i = 0
        while i < len(S):
            if i < len(S):
                lines += 1
            count = 0
            while i < len(S):
                if count + width_map[S[i]] <= 100:
                    count += width_map[S[i]]
                    i += 1
                else:
                    break
        return [lines, count]


sol = Solution()
widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "abcdefghijklmnopqrstuvwxyz"
print(sol.numberOfLines(widths, S), "expected [3, 60]")

widths = [4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "bbbcccdddaaa"
print(sol.numberOfLines(widths, S), "expected [2, 4]")
