#!/usr/bin/env python3.10

import time
import datetime

from termcolor import colored


class UTest:
    """
    Small unit test class for each problem to run a few test cases. Not a replacement for unittest package.
    """
    def __init__(self):
        self.test_num = 0

    def _common_message(self, message) -> str:
        if message is not None:
            return f'test{self.test_num} : {message} :'
        else:
            return f'test{self.test_num} :'

    def _ran_a_test_common(self):
        self.test_num += 1

    def test_true(self, inp, message=None):
        """ Assert input is True """
        if inp is not True:
            print(colored(f'{self._common_message(message)} FAILED (should have been true)', 'red'))
        else:
            print(colored(f'{self._common_message(message)} passed', 'green'))
        self._ran_a_test_common()

    def test_false(self, inp, message=None):
        """ Assert input is False """
        if inp is not False:
            print(colored(f'{self._common_message(message)} FAILED (should have been false)', 'red'))
        else:
            print(colored(f'{self._common_message(message)} passed', 'green'))
        self._ran_a_test_common()

    def test_eq(self, input1, input2, message=None):
        """ Assert inputs are equal """
        if input1 != input2:
            print(colored(f'{self._common_message(message)} FAILED (arg1={input1} and arg2={input2} are not equal)', 'red'))
        else:
            print(colored(f'{self._common_message(message)} passed', 'green'))
        self._ran_a_test_common()


def main():
    # Run a specific problem
    start = time.time()
    # Run problem by importing it
    import problems.p0744.python.p0744
    elapsed = datetime.timedelta(seconds=time.time() - start)
    print('Elapsed: {}'.format(elapsed))


if __name__ == '__main__':
    main()
