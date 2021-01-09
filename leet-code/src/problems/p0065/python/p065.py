#[COMPLETED]

class Solution(object):
    """
    :type s: str
    :rtype: bool
    """
    def isNumber(self, s):
        s = s.strip()
        spl = s.split("e")
        if len(spl) != 1 and len(spl) != 2:
            return False
        if len(spl) == 1:
            return self.isDecimal(spl[0])
        else:
            return self.isDecimal(spl[0]) and self.isSignedInteger(spl[1])

    def isDecimal(self, s):
        spl = s.split(".")
        if len(spl) != 1 and len(spl) != 2:
            return False
        if len(spl) == 1:
            return self.isSignedInteger(spl[0])
        elif spl[0] == '' or spl[0] == '-' or spl[0] == '+':
            return self.isUnsignedInteger(spl[1])
        elif spl[1] == '':
            return self.isSignedInteger(spl[0])

        else:
            return self.isSignedInteger(spl[0]) and self.isUnsignedInteger(spl[1])

    def isSignedInteger(self, s):
        if len(s) == 0:
            return False
        if s[0] == '+' or s[0] == '-':
            if len(s) == 1:
                return False
            return self.isUnsignedInteger(s[1:])
        else:
            return self.isUnsignedInteger(s)

    def isUnsignedInteger(self, s):
        if len(s) == 0:
            return False
        # Rest of integer should be numbers
        for c in s:
            if ord(c) < ord('0') or ord(c) > ord('9'):
                return False
        return True


sol = Solution()
assert sol.isNumber("0") == True
assert sol.isNumber(" 0.1 ") == True
assert sol.isNumber("abc") == False
assert sol.isNumber("1 a") == False
assert sol.isNumber("2e10") == True
assert sol.isNumber(" -90e3   ") == True
assert sol.isNumber(" 1e") == False
assert sol.isNumber("e3") == False
assert sol.isNumber(" 6e-1") == True
assert sol.isNumber(" 99e2.5 ") == False
assert sol.isNumber("53.5e93") == True
assert sol.isNumber(" --6 ") == False
assert sol.isNumber("-+3") == False
assert sol.isNumber("95a54e53") == False
assert sol.isNumber(".1") == True
assert sol.isNumber("3.") == True
assert sol.isNumber("-1.") == True
assert sol.isNumber("+.8") == True
assert sol.isNumber("96 e5") == False

assert sol.isNumber("                12376387463987463.123847123948123e-129381723           ") == True
print("passed all")