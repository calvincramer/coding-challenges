
from typing import List


def unsigned_bin_str_to_dec(s_bin: str) -> int:
    """
    Convert string of 0's and 1's to decimal. Treats binary string as unsigned, left most digit is most significant.

    For example: "10011 -> 16 + 2 + 1 = 19
    """
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


def bin_search(arr: List, search_for: object, left: int, right: int) -> int:
    """ Binary search"""
    if right < left:
        return -1
    mid = left + ((right - left) // 2)
    mid_n = arr[mid]
    if mid_n == search_for:
        return mid
    elif mid_n > search_for:
        return bin_search(search_for, left, mid - 1)
    return bin_search(search_for, mid + 1, right)
