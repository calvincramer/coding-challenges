#[COMPLETED]

digit_map = {
    "0": "0", "1":"1", "8":"8",
    "2":"5", "5":"2", "6":"9", "9":"6",
}

def rotate(num: int):
    rot = ""
    for c in str(num):
        if c not in digit_map:
            return -1
        rot += digit_map[c]
    return int(rot)

def is_good(num: int):
    rotated = rotate(num)
    if rotated == -1:
        return False
    return rotated != num

class Solution:
    def __init__(self):
        self.memo = [-1] * 10001
        total = 0
        for n in range(0, 10001):
            if is_good(n):
                total += 1
            self.memo[n] = total

    def rotatedDigits(self, N: int) -> int:
        if not 1 <= N <= 10000:
            return -1
        return self.memo[N]


for i in range(100):
    print(str(i), "\t", str(rotate(i)))
