#[COMPLETED]

class MyHashSet(object):
    """
    Initialize your data structure here.
    """
    def __init__(self):
        self.bins = [[] * 10000]

    """
    :type key: int
    :rtype: None
    """
    def add(self, key):
        if key is None:
            return
        hsh = hash(key) % len(self.bins)
        if key not in self.bins[hsh]:
            self.bins[hsh].append(key)

    """
    :type key: int
    :rtype: None
    """
    def remove(self, key):
        if key is None:
            return
        hsh = hash(key) % len(self.bins)
        if key in self.bins[hsh]:
            self.bins[hsh].remove(key)

    """
    Returns true if this set contains the specified element
    :type key: int
    :rtype: bool
    """
    def contains(self, key):
        if key is None:
            return
        hsh = hash(key) % len(self.bins)
        return key in self.bins[hsh]


# Your MyHashSet object will be instantiated and called as such:
obj = MyHashSet()
obj.add(5)
assert obj.contains(5) == True
obj.remove(5)
assert obj.contains(5) == False
print("All passed")