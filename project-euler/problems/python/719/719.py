#!/usr/bin/env python3.7
import math
import timeit
import time

import numpy

"""
S-number -> natural number $n$ that is a perfect square and it's square root is equal to some way of splitting the 
decimal numbers into two or more groups.
-   n >= 10 (has two or more digits)
-   example: 81 is an S-number because sqrt(81) = 9 = 8 + 1

T(N) = sum of all S-numbers n <= N
-   T(10^4) = 41333

-----------------------------------------------------

I need to find a faster way to split the number to reach the target.
-   For a number with k digits, there are 2^(k-1) ways to split the number (minus 1 if you don't count the non-split option)
-   there are k-1 binary decisions, to either join digits or split them (considering pairs of digits left to right)
-   this forms a tree
-   the direct solution try_partition_sum tries all paths.
-   we can figure out to not go down 'wrong' paths by maintaining a min and max values for our current split / joins
-       consider our number has digits abcde
-       splits are denoted with |, joins are denoted with ., undecided are ?
-       at the start we have: a?b?c?d?e
-           the minimum is the sum of the digits: a+b+c+d+e
-           the maximum is joining all digits: abcde  aka: (a*10000)+(b*1000)+...
-       at a certain point consider that we have: a|b.cde
-           the minimum is: a + bc + d + e  (treat the rest of the undecideds as splits)
-           the maximum is a + bcde         (treat the rest of the undecideds as joins)
-       at each point, if not target not in the inclusive range [minimum, maximum], then we KNOW that any below paths we not get to the target
-   hopefully this is fast enough
"""


def sum_decimal_digits(num: int) -> int:
    return sum(int(c) for c in str(num))


def _try_partition_sum_helper(target: int, add_to_choice: int, num: int, num_splits: int):
    """Returns true / false, split"""
    if target < 0:
        return False, None  # Overshot
    if target == 0:
        if num == 0 and num_splits > 0:
            return True, []  # Exact, nothing left
        else:
            return False, None  # remaining parts to num, or 0 splits have been made
    if num == 0:
        return False, None  # target not reached, num ran out of stuff

    rmd = num % 10  # right-most digit

    if add_to_choice == 0:
        val_to_add = rmd
    else:
        val_to_add = int(str(rmd) + str(add_to_choice))  # Faster way to add left most digit?

    # Try split/take the rightmost digit
    res_b, res_list = _try_partition_sum_helper(
        target=target - val_to_add,  # take digit and anything in add_to_choice
        add_to_choice=0,  # add this to value
        num=num // 10,  # took digit
        num_splits=num_splits + 1,
    )
    if res_b is True:
        return True, res_list + [val_to_add]

    # Try save the rightmost digit for a later split
    if add_to_choice == 0:
        big_num = rmd
    else:
        big_num = int(str(rmd) + str(add_to_choice))  # Faster way to add left most digit?

    res_b, res_list = _try_partition_sum_helper(
        target=target,
        add_to_choice=big_num,
        num=num // 10,  # took digit and put in add_to_choice
        num_splits=num_splits,  # no split made yet
    )
    if res_b is True:
        return True, res_list
    return False, None


def _try_partition_sum_helper_no_list(target: int, add_to_choice: int, num: int, num_splits: int):
    """Returns true / false"""
    if target < 0:
        return False  # Overshot
    if target == 0:
        if num == 0 and num_splits > 0:
            return True  # Exact, nothing left
        else:
            return False  # remaining parts to num, or 0 splits have been made
    if num == 0:
        return False  # target not reached, num ran out of stuff

    rmd = num % 10  # right-most digit

    if add_to_choice == 0:
        val_to_add = rmd
    else:
        val_to_add = int(str(rmd) + str(add_to_choice))  # Faster way to add left most digit?

    # Try split/take the rightmost digit
    if _try_partition_sum_helper_no_list(
            target=target - val_to_add,  # take digit and anything in add_to_choice
            add_to_choice=0,  # add this to value
            num=num // 10,  # took digit
            num_splits=num_splits + 1,
    ):
        return True

    # Try save the rightmost digit for a later split
    if add_to_choice == 0:
        big_num = rmd
    else:
        big_num = int(str(rmd) + str(add_to_choice))  # Faster way to add left most digit?

    if _try_partition_sum_helper_no_list(
        target=target,
        add_to_choice=big_num,
        num=num // 10,  # took digit and put in add_to_choice
        num_splits=num_splits,  # no split made yet
    ):
        return True
    return False


def try_partition_sum(num: int):
    """Partitions the square of num"""
    return _try_partition_sum_helper(target=num, add_to_choice=0, num=num * num, num_splits=0)
    # return _try_partition_sum_helper_no_list(target=num, add_to_choice=0, num=num * num, num_splits=0)


def _calculate_test(nums, split_joins, undecided_use='j') -> int:
    """
    Calculate the value of an array of digits with decisions to either join or split each one.
    Can change '?' values to a either join or split
    """
    # split_joins may be too small
    if len(split_joins) + 1 < len(nums):
        diff = len(nums) - len(split_joins) - 1
        split_joins = split_joins + ([undecided_use] * diff)

    # Decide on undecided
    for idx in range(len(split_joins)):
        if split_joins[idx] == '?':
            split_joins[idx] = undecided_use

    value = 0
    i = 0
    nums_len = len(nums)
    split_joins_len = len(split_joins)

    while i < nums_len:
        temp_sum = nums[i]
        if i == split_joins_len:    # Last number special
            value += temp_sum
            break
        if split_joins[i] == 'j':
            j = i
            while j < split_joins_len and split_joins[j] == 'j':
                j += 1
                temp_sum = (temp_sum*10) + nums[j]
            i = j + 1
        else:   # sj_i == 's':
            i += 1
        value += temp_sum
    return value


def smart_tree_search(nums, split_joins, target: int) -> bool:
    """Don't descent down impossible paths"""
    # Leaf
    if len(split_joins) + 1 == len(nums):
        return target == _calculate_test(nums, split_joins)

    # Descend
    _min = _calculate_test(nums, split_joins, undecided_use='s')
    _max = _calculate_test(nums, split_joins, undecided_use='j')

    if _min <= target <= _max:
        # Left -> join
        left_ans = smart_tree_search(nums, split_joins + ['j'], target)
        if left_ans is True:
            return True
        # Right -> split
        right_ans = smart_tree_search(nums, split_joins + ['s'], target)
        if right_ans is True:
            return True
    return False


def T(N: int) -> int:
    end = int(math.sqrt(N))
    # nums = numpy.arange(end + 1)
    # nums = numpy.power(nums, 2)     # Get squares

    total = 0
    for n in range(end+1):
        # res_b, res_list = try_partition_sum(n)
        # res_b = try_partition_sum(n)
        n_sqr = n*n

        res_b = smart_tree_search(nums=[int(c) for c in str(n*n)], split_joins=[], target=n)

        if res_b is True:
            # print(f"{n*n} -> {res_list}")
            total += n ** 2

    return total


def testing():
    print(f"[6.7.2.4] -> {_calculate_test([6, 7, 2, 4], ['j', 'j', 'j'])} (expected 6724)")
    print(f"[6.7.2|4] -> {_calculate_test([6, 7, 2, 4], ['j', 'j', 's'])} (expected 676)")
    print(f"[6.7|2.4] -> {_calculate_test([6, 7, 2, 4], ['j', 's', 'j'])} (expected 91)")
    print(f"[6.7|2|4] -> {_calculate_test([6, 7, 2, 4], ['j', 's', 's'])} (expected 73)")
    print(f"[6|7.2.4] -> {_calculate_test([6, 7, 2, 4], ['s', 'j', 'j'])} (expected 730)")
    print(f"[6|7.2|4] -> {_calculate_test([6, 7, 2, 4], ['s', 'j', 's'])} (expected 82)")
    print(f"[6|7|2.4] -> {_calculate_test([6, 7, 2, 4], ['s', 's', 'j'])} (expected 37)")
    print(f"[6|7|2|4] -> {_calculate_test([6, 7, 2, 4], ['s', 's', 's'])} (expected 19)")
    print()
    print(f"[6|7?2?4] -> {_calculate_test([6, 7, 2, 4], ['s', '?', '?'])} (expected 730)")
    return

    # n = 82
    # print(smart_tree_search(nums=[int(c) for c in str(n*n)], split_joins=[], target=n))
    # return


def main():
    # testing()


    start = time.time()
    print(f"T(10^4) = {T(10 ** 4)}\tReal answer = 41333\ttime = {time.time() - start}s")
    start = time.time()
    print(f"T(10^6) = {T(10 ** 6)}\ttime = {time.time() - start}s")
    start = time.time()
    print(f"T(10^8) = {T(10 ** 8)}\ttime = {time.time() - start}s")
    start = time.time()
    print(f"T(10^9) = {T(10 ** 9)}\ttime = {time.time() - start}s")
    start = time.time()
    print(f"T(10^12) = {T(10 ** 12)}\ttime = {time.time() - start}s")


if __name__ == "__main__":
    main()
