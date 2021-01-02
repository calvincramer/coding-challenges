#[COMPLETED]

class Solution:
    def countSegments(self, s: str) -> int:
        n = 0
        in_seg = False
        for c in s:
            if c == ' ':
                if in_seg:
                    n += 1
                    in_seg = False
                continue
            else:
                in_seg = True
        return n if in_seg is False else n + 1


sol = Solution()
print(sol.countSegments("Hello, my name is John"), "expected 5")
