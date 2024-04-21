#[COMPLETED]

class Solution:
    num_map = {
        "2": "abc",
        "3": "def",
        "4": "ghi",
        "5": "jkl",
        "6": "mno",
        "7": "pqrs",
        "8": "tuv",
        "9": "wxyz",
    }
    def letterCombinations(self, digits: str):
        if len(digits) == 0:
            return []
        elif len(digits) == 1:
            return list(Solution.num_map[digits])
        res = []
        for c in Solution.num_map[digits[0]]:
            temp_res = self.letterCombinations(digits[1:])
            res.extend([c + s for s in temp_res])
        return res

sol = Solution()
print(sol.letterCombinations("23"))