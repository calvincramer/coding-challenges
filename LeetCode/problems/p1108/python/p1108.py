#[COMPLETED]

class Solution:
    def defangIPaddr(self, address: str) -> str:
        tok = address.split(".")
        return "[.]".join(tok)


sol = Solution()
print(sol.defangIPaddr("1.1.1.1"), "expected 1[.]1[.]1[.]1")
print(sol.defangIPaddr("255.100.50.0"), "255[.]100[.]50[.]0")
