#[COMPLETED]

class Solution:
    morse_arr = [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]

    def __init__(self):
        self.morse_dict = {}
        for i, char_code in zip(range(len(self.morse_arr)), range(ord('a'), ord('z') + 1)):
            self.morse_dict[chr(char_code)] = self.morse_arr[i]
        #print(self.morse_dict)

    def uniqueMorseRepresentations(self, words) -> int:
        str_set = set([])
        for word in words:
            morse_str = ""
            for c in word:
                morse_str += self.morse_dict[c]
            str_set.add(morse_str)
        return len(str_set)

sol = Solution()
print(sol.uniqueMorseRepresentations(["gin", "zen", "gig", "msg"]), "expected 2")
