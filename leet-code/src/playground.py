#!/usr/bin/env python3


# Diagonal indexing
t = [[0] * 8 for _ in range(8)]
n = len(t)
for d in range(0, n):
    for y in range(0, n - d):
        x = d + y
        print(f"{y=} {x=}")
    print()
