import main


class Solution:
    def restoreIpAddresses(self, s: str) -> list[str]:
        return self.helper(s, depth=0)

    def helper(self, s: str, depth: int) -> list[str]:
        if s == "":
            return []

        ans = []

        for n in range(1, 4):
            sub = s[:n]

            # Skip leading zeros
            if n > 1 and sub[0] == "0":
                continue
            # Skip out of range numbers
            if int(sub) > 255:
                continue

            # Recurse or base
            if depth == 3:
                if len(s) == n:
                    ans.append(sub)
            else:
                rest = self.helper(s=s[n:], depth=depth + 1)
                ans.extend([f"{sub}.{part}" for part in rest])
        return ans


sol = Solution()
test = main.UTest()
test.test_eq(sol.restoreIpAddresses("25525511135"), ["255.255.11.135", "255.255.111.35"])
test.test_eq(sol.restoreIpAddresses("0000"), ["0.0.0.0"])
test.test_eq(sol.restoreIpAddresses("101023"), ["1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3"])
