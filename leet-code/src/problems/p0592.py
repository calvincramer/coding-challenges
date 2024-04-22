import main


class Solution:
    def consumeFrac(self, s: str) -> tuple[int, int, str]:
        if len(s) == 0:
            return None
        posNeg = "P"
        if s[0] == "+":
            s = s[1:]
        if s[0] == "-":
            posNeg = "N"
            s = s[1:]
        # Numerator
        numerator = ""
        while len(s) > 0 and s[0] in "0123456789":
            numerator = f"{numerator}{s[0]}"
            s = s[1:]
        numerator = int(numerator)
        # '/'
        assert s[0] == "/"
        s = s[1:]
        # Denom
        denom = ""
        while len(s) > 0 and s[0] in "0123456789":
            denom = f"{denom}{s[0]}"
            s = s[1:]
        denom = int(denom)
        if posNeg == "N":
            numerator = -1 * numerator
        return numerator, denom, s

    def addFracs(self, aNum, aDenom, bNum, bDenom) -> tuple[int, int]:
        return (aNum * bDenom) + (bNum * aDenom), aDenom * bDenom

    def gcd(self, a: int, b: int) -> int:
        while b != 0:
            b, a = a % b, b
        return a

    def reduce(self, num, denom) -> tuple[int, int]:
        while True:
            div = self.gcd(num, denom)
            if div == 1:
                return num, denom
            num = num // div
            denom = denom // div

    def fractionAddition(self, expression: str) -> str:
        fracs = []
        while expression != "":
            temp = self.consumeFrac(s=expression)
            if temp is None:
                break
            num, denom, expression = temp
            fracs.append((num, denom))
        while len(fracs) >= 2:
            a = fracs.pop(0)
            b = fracs.pop(0)
            fracs.append(self.addFracs(a[0], a[1], b[0], b[1]))
        num, denom = self.reduce(fracs[0][0], fracs[0][1])
        return f"{num}/{denom}"


sol = Solution()
test = main.UTest()
test.test_eq(sol.fractionAddition("-1/2+1/2"), "0/1")
test.test_eq(sol.fractionAddition("-1/2+1/2+1/3"), "1/3")
test.test_eq(sol.fractionAddition("1/3-1/2"), "-1/6")
