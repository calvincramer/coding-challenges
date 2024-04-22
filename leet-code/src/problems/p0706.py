#[COMPLETED]

# Has a bin for each spot in the hash table
# If hash conflict, multiple entries are placed in same spot
# Searching for entries in the same spot is done in O(n)
# Can make better by binary search by key in each bin, to get O(log(n))

# Alternate implementations
# - Use a python dictionary for everything
# -
class MyHashMap:
    """
    Initialize your data structure here.
    """
    def __init__(self):
        self.table = [[] for i in range(1000)]

    """
    value will always be non-negative.
    """
    def put(self, key: int, value: int) -> None:
        # Find spot to place entry
        hsh = hash(key) % len(self.table)
        for i in range(len(self.table[hsh])):
            if self.table[hsh][i][0] == key:
                # Update existing entry
                self.table[hsh][i] = [key, value]
                return
        # Not found in bin
        self.table[hsh].append([key, value])

    """
    Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
    """
    def get(self, key: int) -> int:
        hsh = hash(key) % len(self.table)
        for [k, v] in self.table[hsh]:
            if k == key:
                return v
        return -1

    """
    Removes the mapping of the specified value key if this map contains a mapping for the key
    """
    def remove(self, key: int) -> None:
        hsh = hash(key) % len(self.table)
        for i in range(len(self.table[hsh])):
            if self.table[hsh][i][0] ==  key:
                del self.table[hsh][i]
                return

hashMap = MyHashMap()
hashMap.put(1, 1)       
hashMap.put(2, 2)         
assert hashMap.get(1) == 1  # returns 1
assert hashMap.get(3) == -1 # returns -1 (not found)
hashMap.put(2, 1)           # update the existing value
assert hashMap.get(2) == 1  # returns 1
hashMap.remove(2)           # remove the mapping for 2
assert hashMap.get(2) == -1 # returns -1 (not found)
print("Passed all tests for 1")

hashMap = MyHashMap()
hashMap.remove(27)
hashMap.put(65,65)
hashMap.remove(19)
hashMap.remove(0)
hashMap.get(18)
hashMap.remove(3)
hashMap.put(42, 0)
hashMap.get(19)
hashMap.remove(42)
hashMap.put(17,90)
hashMap.put(31,76)
hashMap.put(48,71)
hashMap.put(5,50)
hashMap.put(7,68)
hashMap.put(73,74)
hashMap.put(85,18)
hashMap.put(74,95)
hashMap.put(84,82)
hashMap.put(59,29)
hashMap.put(71,71)
hashMap.remove(42)
hashMap.put(51,40)
hashMap.put(33,76)
hashMap.get(17)
hashMap.put(89,95)
hashMap.get(95)
hashMap.put(30,31)
hashMap.put(37,99)
hashMap.get(51)
hashMap.put(95,35)
hashMap.remove(65)
hashMap.remove(81)
hashMap.put(61,46)
hashMap.put(50,33)
hashMap.get(59)
hashMap.remove(5)
hashMap.put(75,89)
hashMap.put(80,17)
hashMap.put(35,94)
hashMap.get(80)
hashMap.put(19,68)
hashMap.put(13,17)
hashMap.remove(70)
hashMap.put(28,35)
hashMap.remove(99)
hashMap.remove(37)
hashMap.remove(13)
hashMap.put(90,83)
hashMap.remove(41)
hashMap.get(50)
hashMap.put(29,98)
hashMap.put(54,72)
hashMap.put(6,8)
hashMap.put(51,88)
hashMap.remove(13)
hashMap.put(8,22)
hashMap.get(85)
hashMap.put(31,22)
hashMap.put(60,9)
hashMap.get(96)
hashMap.put(6,35)
hashMap.remove(54)
hashMap.get(15)
hashMap.get(28)
hashMap.remove(51)
hashMap.put(80,69)
hashMap.put(58,92)
hashMap.put(13,12)
hashMap.put(91,56)
hashMap.put(83,52)
hashMap.put(8,48)
hashMap.get(62)
hashMap.get(54)
hashMap.remove(25)
hashMap.put(36,4)
hashMap.put(67,68)
hashMap.put(83,36)
hashMap.put(47,58)
hashMap.get(82)
hashMap.remove(36)
hashMap.put(30,85)
hashMap.put(33,87)
hashMap.put(42,18)
hashMap.put(68,83)
hashMap.put(50,53)
hashMap.put(32,78)
hashMap.put(48,90)
hashMap.put(97,95)
hashMap.put(13,8)
hashMap.put(15,7)
hashMap.remove(5)
hashMap.remove(42)
hashMap.get(20)
hashMap.remove(65)
hashMap.put(57,9)
hashMap.put(2,41)
hashMap.remove(6)
hashMap.get(33)
hashMap.put(16,44)
hashMap.put(95,30)

print("Passed all tests for 2")