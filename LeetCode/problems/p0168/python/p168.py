#[IN PROGRESS]

class Solution:
    def convertToTitle(self, n: int) -> str:
        n -= 1
        # bases = [0]
        # while bases[0] <= n:
        #     bases.insert(0, bases[0] + 3 ** len(bases))
        # bases.pop(0)
        # bases.pop(-1)
        # s = ""
        # for i, b in enumerate(bases):
        #     times_into = n // b
        #     s += str(times_into - (1 if i == 0 else 0))
        #     n -= b * times_into
        #     if n < 0:
        #         break
        # s += str(n)
        s = ""
        while True:
            s = str(n % 3) + s
            #n //= 3
            n -= 3 * (n // 3)
            if n == 0:
                break

        return s


    # def convertToTitle(self, n: int) -> str:
    #     n -= 1  # Zero the columns because 1 indexing sucks.
    #     base = 1
    #     maxn = 25
    #     while maxn <= n:
    #         base *= 26
    #         maxn += 25 * base
    #     base //= 26
    #     base = max(1, base)
    #     s = ""
    #     while base != 1:
    #         t = n // base
    #         s = s + chr(ord('A') + t - 1)
    #         n -= t * base
    #         base //= 26
    #     s += chr(ord('A') + n)
    #     #return "A" if s == "" else s
    #     return s


sol = Solution()
sol.convertToTitle(3841209348713)
print(sol.convertToTitle(1), "expected A")
print(sol.convertToTitle(701), "expected ZY")
print(sol.convertToTitle(677), "expected ZA")
print(sol.convertToTitle(678), "expected ZB")
print(sol.convertToTitle(28), "expected AB")
print(sol.convertToTitle(3750), "expected ???")

for i in range(1, 710):
    print(str(i) + "\t: ", sol.convertToTitle(i))
