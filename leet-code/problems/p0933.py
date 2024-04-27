class RecentCounter:
    def __init__(self):
        self.arr = []

    def ping(self, t: int) -> int:
        self.arr.append(t)
        # Remove old ones
        while len(self.arr) > 0 and t - self.arr[0] > 3000:
            self.arr.pop(0)
        return len(self.arr)


# Your RecentCounter object will be instantiated and called as such:
# obj = RecentCounter()
# param_1 = obj.ping(t)
