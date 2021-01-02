#[COMPLETED]

class Solution:
    def isLongPressedName(self, name, typed):
        # Holds a tuple of each character in name and how many times it occurs in a row
        name_char_occ = []
        i = 0
        while i < len(name):
            j = i+1
            while j < len(name) and name[j] == name[i]:
                j += 1
            name_char_occ.append((name[i], j-i))
            i = j
        # Do same for typed
        typed_char_occ = []
        i = 0
        while i < len(typed):
            j = i+1
            while j < len(typed) and typed[j] == typed[i]:
                j += 1
            typed_char_occ.append((typed[i], j-i))
            i = j
        # Check
        if len(name_char_occ) != len(typed_char_occ):
            return False
        for i in range(len(name_char_occ)):
            if name_char_occ[i][0] != typed_char_occ[i][0]:
                return False
            if name_char_occ[i][1] > typed_char_occ[i][1]:
                return False

        return True


sol = Solution()
print(sol.isLongPressedName("aalex", "alex"), " expected true")

