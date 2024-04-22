#[COMPLETED]

class MyQueue(object):
    """
    Initialize your data structure here.
    """
    def __init__(self):
        self.stack1 = []
        self.stack2 = []

    """
    Push element x to the back of queue.
    :type x: int
    :rtype: None
    """
    def push(self, x):
        # pop everything from stack1 to stack2
        while len(self.stack1) != 0:
            self.stack2.insert(0, self.stack1.pop(0))
        # push to stack1
        self.stack1.insert(0, x)
        # pop everything from stack2 to stack1
        while len(self.stack2) != 0:
            self.stack1.insert(0, self.stack2.pop(0))
        pass

    """
    Removes the element from in front of queue and returns that element.
    :rtype: int
    """
    def pop(self):
        return self.stack1.pop(0)

    """
    Get the front element.
    :rtype: int
    """
    def peek(self):
        if len(self.stack1) == 0:
            return None
        return self.stack1[0]

    """
    Returns whether the queue is empty.
    :rtype: bool
    """
    def empty(self):
        return len(self.stack1) == 0


# Your MyQueue object will be instantiated and called as such:
obj = MyQueue()
obj.push(5)
param_2 = obj.pop()
param_3 = obj.peek()
param_4 = obj.empty()