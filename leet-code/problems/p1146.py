import main


class SnapshotArray:

    def __init__(self, length: int):
        self.t = [0] * length
        self.changed = set()  # set of indices
        self.snapIdNext = 0
        # dict of changed index -> val
        # BETTER TO DO THIS PER INDEX
        self.hist = []

    def set(self, index: int, val: int) -> None:
        self.t[index] = val
        self.changed.add(index)

    def snap(self) -> int:
        sid = self.snapIdNext
        self.hist.append({idx: self.t[idx] for idx in self.changed})
        # Reset
        self.snapIdNext += 1
        self.changed.clear()
        return sid

    def get(self, index: int, snap_id: int) -> int:
        for i in range(snap_id, -1, -1):
            if index in self.hist[i].keys():
                return self.hist[i][index]
        return 0


# Your SnapshotArray object will be instantiated and called as such:
# obj = SnapshotArray(length)
# obj.set(index,val)
# param_2 = obj.snap()
# param_3 = obj.get(index,snap_id)


test = main.UTest()
sa = SnapshotArray(3)
sa.set(0, 5)
test.test_eq(sa.snap(), 0)
sa.set(0, 6)
test.test_eq(sa.get(0, 0), 5)
