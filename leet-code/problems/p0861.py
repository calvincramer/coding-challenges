#[COMPLETED]

from typing import List

from main import UTest


def unsigned_bin_str_to_dec(s_bin: str) -> int:
    """ Copied from utils module """
    if s_bin == '' or s_bin is None:
        return 0
    if set(s_bin).issubset({0, 1}):
        raise ValueError('input string contains something other than zeros and ones')
    s_bin = s_bin[::-1]     # Reverse string
    base = 1
    dec_num = 0
    for bit in s_bin:
        if bit == '1':
            dec_num += base
        base *= 2
    return dec_num


class Solution:
    def matrixScore(self, A: List[List[int]]) -> int:
        if len(A) == 0:
            return 0
        num_rows = len(A)

        def flip_rows():
            for r_i in range(len(A)):
                if A[r_i][0] == 0:
                    # Flip row
                    for c_i in range(len(A[r_i])):
                        A[r_i][c_i] ^= 1

        def flip_columns():
            for c_i in range(len(A[0])):
                sum_col = sum(A[r_i][c_i] for r_i in range(len(A)))
                if sum_col * 2 < num_rows:
                    # Flip column
                    for r_i in range(len(A)):
                        A[r_i][c_i] ^= 1

        flip_rows()
        flip_columns()
        return sum(unsigned_bin_str_to_dec(''.join(str(elm) for elm in row)) for row in A)

test = UTest()
sol = Solution()
test.test_eq(sol.matrixScore([[0,0,1,1],[1,0,1,0],[1,1,0,0]]), 39)


"""
0011
1010
1100

1100
1010
1100   

1101
1011
1101

1111
1001        optimal solution, apparently
1111


Optimal strategy (not optimal):
1. Go through each column, flip if majority are zeroes
2. Go through each row, flip if first (leftmost) bit is 0
3. Repeat

###
0011
1010
1100

0111
1110
1000

0110
1111
1001

1001
1111
1001

1101
1011
1101

1101
1011
1101

1111
1001
1111

Optimal strategy:
1. Go through each row, flip if first (leftmost) bit is 0
2. Go through each column, flip if majority are zeroes
3. Repeat?

0011
1010
1100

1100
1010
1100

1110
1000
1110

1111
1001
1111

"""