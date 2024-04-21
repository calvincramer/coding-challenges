#[COMPLETED]

class Trie:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root = {}
        

    def insert(self, word):
        """
        Inserts a word into the trie.
        :type word: str
        :rtype: void
        """
        current = self.root
        for char in word:
            if char not in current:
                current[char] = {}
            current = current[char]
        current['#'] = '#'

    def getRoot(self):
        return self.root
        

    def search(self, word):
        """
        Returns if the word is in the trie.
        :type word: str
        :rtype: bool
        """
        current = self.root
        for char in word:
            if char not in current:
                return False
            current = current[char]
        if '#' in current:
            return True
        return False
        

    def startsWith(self, prefix):
        """
        Returns if there is any word in the trie that starts with the given prefix.
        :type prefix: str
        :rtype: bool
        """
        current = self.root
        for char in prefix:
            if char not in current:
                return False
            current = current[char]
        return True


t = Trie()
t.insert("ab")
print(t.getRoot())
t.insert("cbcd")
print(t.getRoot())
t.insert("ae")
print(t.getRoot())

print("ab: " + str(t.search("ab")))
print("cbcd: " + str(t.search("cbcd")))
print("ae: " + str(t.search("ae")))
print("cbc: " + str(t.search("cbc")))

print("")

print("ab: " + str(t.startsWith("ab")))
print("cbcd: " + str(t.startsWith("cbcd")))
print("ae: " + str(t.startsWith("ae")))
print("cbc: " + str(t.startsWith("cbc")))
