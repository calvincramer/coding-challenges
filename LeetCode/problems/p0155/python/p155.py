#[COMPLETED]

class MinStack(object):
    """
    initialize your data structure here.
    """
    def __init__(self):
        self.l = []
        self.min_l = []

    """
    :type x: int
    :rtype: None
    """
    def push(self, x):
        self.l.insert(0, x)
        if len(self.min_l) == 0 or x <= self.min_l[0]:
            self.min_l.insert(0, x)

    """
    :rtype: None
    """
    def pop(self):
        if self.l[0] == self.min_l[0]:
            self.min_l.pop(0)
        self.l.pop(0)

    """
    :rtype: int
    """
    def top(self):
        return self.l[0]

    """
    :rtype: int
    """
    def getMin(self):
        return self.min_l[0]


minStack = MinStack()
minStack.push(-2)
minStack.push(0)
minStack.push(-3)
assert minStack.getMin() == -3
minStack.pop()
assert minStack.top() == 0
assert minStack.getMin() == -2

s2 = MinStack()
s2.push(0)
s2.push(1)
s2.push(0)
assert s2.getMin() == 0
s2.pop()
assert s2.getMin() == 0

print("Passed all")