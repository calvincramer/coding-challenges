#[COMPLETED]

class Solution:
    # An atom is an UpperCase letter followed by optional lowercase letters followed by optional number
    def next_atom(self, s):
        atom = ""
        if len(s) > 0 and s[0].isalnum() and s[0].isupper():
            atom = atom + s[0]
            s = s[1:]
        else:
            return s, atom
        while len(s) > 0 and s[0].isalpha() and s[0].islower():
            atom = atom + s[0]
            s = s[1:]
        return s, atom


    def next_number(self, s):
        num_str = ""
        while len(s) > 0 and s[0].isdigit():
            num_str = num_str + s[0]
            s = s[1:]
        num = int(num_str) if len(num_str) > 0 else 1
        return s, num


    # Returns indices of first parenthesis, if found
    def get_parens(self, s):
        start = -1
        end = -1
        for i in range(len(s)):
            if s[i] == "(":
                start = i
                break
        # Must find at correct level = 0
        level = 1
        for i in range(start+1, len(s)):
            if s[i] == "(":
                level += 1
            elif s[i] == ")":
                level -= 1
            if level == 0:
                end = i
                break
        return start, end


    def trav(self, formula):
        atom_count = {}
        while True:
            l, r = self.get_parens(formula)
            if l == -1 and r == -1:
                break
            sub_count = self.trav(formula[l+1:r])
            after_parens_formula, mult = self.next_number(formula[r+1:])
            formula = formula[:l] + after_parens_formula
            for a in sub_count:
                if a not in atom_count:
                    atom_count[a] = mult * sub_count[a]
                else:
                    atom_count[a] += mult * sub_count[a]
        while True:
            formula, a = self.next_atom(formula)
            formula, n = self.next_number(formula)
            if a == "":
                break
            elif a in atom_count:
                atom_count[a] += n
            else:
                atom_count[a] = n

        return atom_count


    def countOfAtoms(self, formula):
        atom_count = self.trav(formula)
        # Format output
        atoms = list(atom_count.keys())
        atoms = sorted(atoms)
        output = ""
        for a in atoms:
            output = output + a + ("" if atom_count[a] == 1 else str(atom_count[a]))
        return output


sol = Solution()
print(sol.countOfAtoms("H2O"), "expected H2O")
print(sol.countOfAtoms("Mg(OH)2"), "expected H2MgO2")
print(sol.countOfAtoms("K4(ON(SO3)2)2"), "expected K4N2O14S4")
print(sol.countOfAtoms("((N42)24(OB40Li30CHe3O48LiNN26)33(C12Li48N30H13HBe31)21(BHN30Li26BCBe47N40)15(H5)16)14"), "expected B18900Be18984C4200H5446He1386Li33894N50106O22638")
