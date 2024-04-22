#[COMPLETED]

class Solution:

    def complexNumberMultiply(self, a: str, b: str) -> str:

        def parse_complex(s):
            a = 0
            b = 0
            tok = s.split("+")
            if len(tok) == 1:
                if tok[0][-1] == "i":
                    return 0, int(tok[0][:-1])
                else:
                    return int(tok[0]), 0

            return int(tok[0]), int(tok[1][:-1])

        ar, ai = parse_complex(a)
        br, bi = parse_complex(b)
        tr = ar * br - (ai * bi)
        ti = ar * bi + br * ai
        return str(tr) + "+" + str(ti) + "i"



sol = Solution()
print(sol.complexNumberMultiply("1+1i", "1+1i"), "expected 0+2i")
print(sol.complexNumberMultiply("1+-1i", "1+-1i"), "expected 0+-2i")


