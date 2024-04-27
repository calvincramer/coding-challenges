#[COMPLETED]

class Solution:
    def powerfulIntegers(self, x: int, y: int, bound: int):
        s = set()
        i = 0
        while True:
            j = 0
            x_pow_i = x ** i
            if x_pow_i > bound:
                break
            while True:
                t = x_pow_i + y ** j
                s.add(t)
                if t > bound:
                    s.remove(t)
                    break
                if y == 1:
                    break
                j += 1
            if x == 1:
                break
            i += 1
        return sorted(list(s))


sol = Solution()
print(sol.powerfulIntegers(2,3,10))
print(sol.powerfulIntegers(3,5,15))
print(sol.powerfulIntegers(2,1,10))
print(sol.powerfulIntegers(1,2,100))