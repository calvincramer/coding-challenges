#[COMPLETED]

class Solution(object):
    """
    :type num: int
    :rtype: str
    """
    def toHex(self, num):
        dec_to_hex = {0:'0', 1:'1', 2:'2',  3:'3',  4:'4',  5:'5',  6:'6',  7:'7',
                      8:'8', 9:'9', 10:'a', 11:'b', 12:'c', 13:'d', 14:'e', 15:'f'}
        if num < 0:
            num += (2 ** 32)
        base = 1
        while base <= num:
            base *= 16
        base //= 16
        hex = ""
        while base > 0:
            hex_dig = num // base
            num -= hex_dig * base
            hex += dec_to_hex[hex_dig]
            base //= 16
        if hex == "":
            hex = "0"
        return hex


sol = Solution()
print(sol.toHex(26), "expected 1a")
print(sol.toHex(-1), "expected ffffffff")
print(sol.toHex(16), "expected 10")
print(sol.toHex(20480), "expected 5000")
