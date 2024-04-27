#[COMPLETED]

class MyStack:
    def __init__(self):
        self.q = []
        self.append_q = self.q.append   # Optimize
        self.pop_q = self.q.pop         # Optimize

    def push(self, x: int) -> None:
        self.append_q(x)    # Push back of queue

    def pop(self) -> int:
        for _ in range(len(self.q) - 1):
            self.append_q(self.pop_q(0))
        return self.pop_q(0)

    def top(self) -> int:
        for _ in range(len(self.q) - 1):
            self.append_q(self.pop_q(0))
        top = self.q[0]                 # Peek
        self.append_q(self.pop_q(0))    # Cycle top
        return top

    def empty(self) -> bool:
        return len(self.q) == 0


stack = MyStack()
stack.push(1)
stack.push(2)
print(stack.top())   # returns 2
print(stack.pop())   # returns 2
print(stack.empty()) # returns false
