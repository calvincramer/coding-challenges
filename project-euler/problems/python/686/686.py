from collections import defaultdict
# from math import log2

import numpy
from numpy import log2

"""
Observations:

1. 
For starts with '12', there are only a few differences in powers that successive p(L,n), p(L,n+1) show up as. 
For the first 1737 p(12, n), where p(12, 1738) = 49992, the distribution of power differences is:
10 -> 1222 times (70.3%)
73 -> 498 times (28.7%)
83 -> 17 times (1.0%)
Maybe we could try from smallest to largest difference and check if it starts with L.

2. I don't think the power differences will occur in repeating sequences.

3. Computing number to directly check whether it starts with L is too expensive.

4. Can compute

p(123, 678910) = 
    193061442 no (python math log2)
    193061442 no (numpy log2
    193060761 no
    193060708 no (c++)
    
    
"""


def test5():
    L = 123

    def low_range_gen():
        log2_l = log2(L)
        log2_10 = log2(10)
        num = log2_l    # Start
        while True:
            yield num
            num += log2_10  # next

    def high_range_gen():
        log2_l_p1 = log2(L+1)
        log2_10 = log2(10)
        num = log2_l_p1    # Start
        while True:
            yield num
            num += log2_10  # next

    count = 0
    target_count = 678910
    for i, (low, high) in enumerate(zip(low_range_gen(), high_range_gen())):
        # See if low, high go over unit boundary

        # if int(low) != int(high):
        # if int(low) != int(high) or low == int(low):
        if high - int(low) >= 1.0:
            count += 1
            pow_2_val = int(high)
            if count == target_count:
                print(f"!!! {count}: {pow_2_val}")
                break
            else:
                print(f"{count}: {pow_2_val}")
                pass



def test4():
    """Guess incrementing 10, 73, 83 to see how fast we can go"""
    L = '123'
    count = 0   # n in p(L, n)
    n = 1
    power = 0
    # Find the first one to initialize us
    while True:
        if str(n).startswith(L) is True:
            count += 1
            break
        n *= 2
        power += 1

    _2pow196 = 2**196
    _2pow289 = 2**289
    _2pow485 = 2**485

    while True:
        # Try pow +196
        n_p196 = n * _2pow196
        if str(n_p196).startswith(L) is True:
            n = n_p196
            diff_power = 196
            power += diff_power
            count += 1
            print(f"{count}: {power}\t{diff_power}")
            continue

        # Try pow +289
        n_p289 = n * _2pow289
        if str(n_p289).startswith(L) is True:
            n = n_p289
            diff_power = 289
            power += diff_power
            count += 1
            print(f"{count}: {power}\t{diff_power}")
            continue

        # Must be pow +485
        n_p485 = n * _2pow485
        if str(n_p485).startswith(L) is True:
            n = n_p485
            diff_power = 485
            power += diff_power
            count += 1
            print(f"{count}: {power}\t{diff_power}")
            continue

        raise ValueError("Need more logic!")


def test3():
    """Guess incrementing 10, 73, 83 to see how fast we can go"""
    L = '12'
    count = 0   # n in p(L, n)
    n = 1
    power = 0
    # Find the first one to initialize us
    while True:
        if str(n).startswith(L) is True:
            count += 1
            break
        n *= 2
        power += 1

    _2pow10 = 2**10
    _2pow73 = 2**73
    _2pow83 = 2**83

    while True:
        if count > 10000:
            break

        # Try pow +10
        n_p10 = n * _2pow10
        if str(n_p10).startswith(L) is True:
            n = n_p10
            diff_power = 10
            power += diff_power
            count += 1
            print(f"{count}: {power}\t{diff_power}")
            continue

        # Try pow +73
        n_p73 = n * _2pow73
        if str(n_p73).startswith(L) is True:
            n = n_p73
            diff_power = 73
            power += diff_power
            count += 1
            print(f"{count}: {power}\t{diff_power}")
            continue

        # Must be pow +83
        n_p83 = n * _2pow83
        if str(n_p83).startswith(L) is True:
            n = n_p83
            diff_power = 83
            power += diff_power
            count += 1
            print(f"{count}: {power}\t{diff_power}")
            continue

        raise ValueError("Need more logic!")


def test1(L: str = '12', test_repeat_diff: list = None):
    count = 1   # n in p(L, n)
    n = 1
    power = 0
    last_power = None
    hist = defaultdict(int)
    repeat_i = 0
    for _ in range(50000):
        # Check
        if str(n).startswith(L) is True:
            diff_power = "n/a" if last_power is None else power - last_power
            print(f"{count}: {power}\t{diff_power}")
            last_power = power
            hist[diff_power] += 1
            count += 1
            # Check repeat
            if diff_power != 'n/a':
                if test_repeat_diff[repeat_i] != diff_power:
                    print(f"\tError in repeat, expected {test_repeat_diff[repeat_i]}, got {diff_power}")
                repeat_i = (repeat_i + 1) % len(test_repeat_diff)
        n *= 2
        power += 1
    print(hist)


def test0(L: str = '123'):
    count = 1   # n in p(L, n)
    n = 1
    power = 0
    last_power = None
    hist = defaultdict(int)
    for _ in range(40000):
        # Check
        if str(n).startswith(L) is True:
            diff_power = "n/a" if last_power is None else power - last_power
            print(f"{count}: {power}\t{diff_power}")
            last_power = power
            hist[diff_power] += 1
            count += 1
        n *= 2
        power += 1
    print(hist)



def main():
    # test0(L='12')
    # test1(L='1', test_repeat_diff=[4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,4,3,3,3])
    # test3()
    # test4()
    test5()

if __name__ == '__main__':
    main()