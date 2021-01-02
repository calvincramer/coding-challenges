#[COMPLETED]

from threading import Lock

class FooBar:
    def __init__(self, n):
        self.n = n
        self.a = Lock()
        self.b = Lock()
        self.a_up = self.a.acquire
        self.a_down = self.a.release
        self.b_up = self.b.acquire
        self.b_down = self.b.release
        self.b_up()


    def foo(self, printFoo: 'Callable[[], None]') -> None:
        for i in range(self.n):
            self.a_up()
            printFoo()
            self.b_down()

    def bar(self, printBar: 'Callable[[], None]') -> None:
        for i in range(self.n):
            self.b_up()
            printBar()
            self.a_down()



from threading import Thread
import time
sol = FooBar(50000)
def foo():
    # print("foo", end="")
    pass
def bar():
    # print("bar", end="\n")
    pass
def f1():
    sol.foo(foo)
def f2():
    sol.bar(bar)
t1 = Thread(target=f1)
t2 = Thread(target=f2)
start = time.time()
t1.start()
t2.start()
t1.join()
t2.join()
print("time: ", str(time.time() - start))
