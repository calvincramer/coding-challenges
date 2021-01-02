#[COMPLETED]

class TreeNode:
    def __init__(self):
        self.chars = {}  # Dictionary of characters [char -> TreeNode]
        self.can_end_here = False
    def __repr__(self):
        return str(sorted(list(self.chars)))
    def printTN(self):
        print("Root: ", self.__repr__(), sep="")
        self.printTN_trav(None)
    def printTN_trav(self, parentTN_val):
        if parentTN_val is None:
            parentTN_val = "root"
        for k,v in self.chars.items():
            print(str(parentTN_val), " -> ", str(k), " : ", v.__repr__(), sep="")
            v.printTN_trav(k)


class WordDictionary:
    """
    Initialize your data structure here.
    """
    def __init__(self):
        self.t = TreeNode()

    """
    Adds a word into the data structure.
    """
    def addWord(self, word: str) -> None:
        trav = self.t
        while word != "":
            ch = word[0]
            word = word[1:]
            if ch not in trav.chars:
                trav.chars[ch] = TreeNode()
            trav = trav.chars[ch]
        trav.can_end_here = True

    """
    Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
    """
    def search(self, word: str) -> bool:
        return self.search_from(self.t, word)

    def search_from(self, tn, word):
        while word != "":
            ch = word[0]
            word = word[1:]
            if ch == ".":
                results = []
                for next in tn.chars.values():
                    results.append(self.search_from(next, word))
                #results = [self.search_from(n, word) for n in trav.chars.values()]
                return any(results)
            if ch not in tn.chars:
                return False
            tn = tn.chars[ch]
        return len(tn.chars) == 0 or tn.can_end_here


# Your WordDictionary object will be instantiated and called as such:
word1 = "abc"
word2 = "add"

obj = WordDictionary()
obj.addWord(word1)
obj.addWord(word2)
obj.t.printTN()
print(obj.search("abc"), "expected True")
print(obj.search("add"), "expected True")
print(obj.search("a.c"), "expected True")
print(obj.search("..d"), "expected True")
print(obj.search("adc"), "expected False")

sol = WordDictionary()
sol.addWord("at")
sol.addWord("and")
sol.addWord("an")
sol.addWord("add")
print(sol.search("a"), "expected False")
print(sol.search(".at"), "expected False")
sol.addWord("bat")
print(sol.search(".at"), "expected True")
print(sol.search("an."), "expected True")
print(sol.search("a.d."), "expected False")
print(sol.search("b."), "expected False")
print(sol.search("a.d"), "expected True")
print(sol.search("."), "expected False")
