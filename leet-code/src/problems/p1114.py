#[COMPLETED]

import threading

class Foo:
    def __init__(self):
        self.lock_2 = threading.Lock()
        self.lock_2.acquire()
        self.lock_3 = threading.Lock()
        self.lock_3.acquire()

    def first(self, printFirst: 'Callable[[], None]') -> None:
        printFirst()    # printFirst() outputs "first". Do not change or remove this line.
        self.lock_2.release()

    def second(self, printSecond: 'Callable[[], None]') -> None:
        self.lock_2.acquire()
        printSecond()   # printSecond() outputs "second". Do not change or remove this line.
        self.lock_3.release()

    def third(self, printThird: 'Callable[[], None]') -> None:
        self.lock_3.acquire()   # printThird() outputs "third". Do not change or remove this line.
        printThird()

