from main import UTest


class Solution:

    def isAdditiveNumber(self, num: str) -> bool:
        self.n = num
        self.n_len = len(self.n)
        for left_len in range(1, self.n_len):
            for right_len in range(1, self.n_len):
                if self.foo(left_len, right_len) == True:
                    return True
        return False

    def foo(self, left_len: int, right_len: int) -> bool:
        if left_len + right_len > self.n_len:
            return False

        start_idx = 0
        left = self.n[start_idx : start_idx + left_len]
        right = self.n[start_idx + left_len : start_idx + left_len + right_len]

        # No leading zero, except single '0'
        if (len(left) > 1 and left[0] == "0") or (len(right) > 1 and right[0] == "0"):
            return False

        # Need 3
        next_num = str(int(left) + int(right))
        if self.n[start_idx + left_len + right_len :].startswith(next_num) == False:
            return False

        # Continue until end
        while True:
            # Break?
            if start_idx + left_len + right_len + len(next_num) >= self.n_len:
                break

            # Next
            start_idx = start_idx + left_len
            left_len = right_len
            right_len = len(next_num)
            left = self.n[start_idx : start_idx + left_len]
            right = self.n[start_idx + left_len : start_idx + left_len + right_len]

            # No leading zero, except single '0'
            if (len(left) > 1 and left[0] == "0") or (len(right) > 1 and right[0] == "0"):
                return False

            next_num = str(int(left) + int(right))
            if self.n[start_idx + left_len + right_len :].startswith(next_num) == False:
                return False

        return True


UTest.exp_eq(Solution().isAdditiveNumber("112358"), True)
UTest.exp_eq(Solution().isAdditiveNumber("199100199"), True)
UTest.exp_eq(Solution().isAdditiveNumber("101"), True)
