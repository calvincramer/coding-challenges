#[COMPLETED]

class Solution:
    def checkRecord(self, s) -> bool:
        return False if ("LLL" in s or s.count("A") > 1) else True

sol = Solution()
print(sol.checkRecord("PPALLP"), "expected True")
print(sol.checkRecord("PPALLL"), "expected False")
