#[COMPLETED]

class Solution(object):
    """
    :type a: str
    :type b: str
    :rtype: str
    """
    def addBinary(self, a, b):
        sol = ""
        carry = False
        while a != "" or b != "":
            a_bit = a[-1] if a != "" else '0'
            b_bit = b[-1] if b != "" else '0'

            if carry:
                sol = ('1' if a_bit == b_bit else '0') + sol
                if a_bit == '0' and b_bit == '0':
                    carry = False
            else:
                sol = ('0' if a_bit == b_bit else '1') + sol
                if a_bit == '1' and b_bit == '1':
                    carry = True

            a = a[0:-1]
            b = b[0:-1]

        return "1" + sol if carry else sol


# Look at Karnaugh map is useful
sol = Solution()
print(sol.addBinary("11", "1"), "expected 100")
print(sol.addBinary("1010", "1011"), "expected 10101")
