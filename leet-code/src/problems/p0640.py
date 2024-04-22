#[COMPLETED]

class Solution:
    def solveEquation(self, equation: str) -> str:
        left, right = equation.split("=")
        l_x, l_n = self.simplify(left)
        r_x, r_n = self.simplify(right)
        (l_x, r_x) = (l_x - r_x, 0)
        (l_n, r_n) = (0, r_n - l_n)
        if l_x == 0:
            if r_n == 0:
                return "Infinite solutions"
            else:
                return "No solution"
        else:
            return "x=" + str(r_n // l_x)

    def simplify(self, expr):
        # Insert + before every minus
        def format(c):
            if c == '-':
                return '+-'
            return c
        def strip_x(s):
            s = s[:-1]
            if s == "":
                return 1
            elif s == "-":
                return -1
            else:
                return int(s)
        expr = "".join([format(c) for c in expr])
        expr = list([s for s in expr.split("+") if s != ""])
        totalX = sum([strip_x(s) for s in expr if s[-1] == 'x'])
        totalN = sum([int(s) for s in expr if s[-1] != 'x'])
        return totalX, totalN


sol = Solution()
print(sol.solveEquation("x+5-3+x=6+x-2"), "expected x=2")
print(sol.solveEquation("x=x"), "expected 'Infinite solutions'")
print(sol.solveEquation("2x=x"), "expected x=0")
print(sol.solveEquation("2x+3x-6x=x+2"), "expected x=-1")
print(sol.solveEquation("x=x+2"), "expected 'No solution'")
print(sol.solveEquation("-5+2x=x+2"), "expected x=7")
print(sol.solveEquation("-x=-1"), "expected x=1")
