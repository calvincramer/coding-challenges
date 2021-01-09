
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
