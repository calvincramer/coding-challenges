#!/usr/bin/env python3

import main

from collections import defaultdict


class Solution:
    def numTilePossibilities(self, tiles: str) -> int:
        tiles = sorted(tiles)
        self.counts = defaultdict(int)
        for t in tiles:
            self.counts[t] += 1
        return self.helper()

    def helper(self) -> int:
        s = 0
        for k in self.counts.keys():
            if self.counts[k] == 0:
                continue
            s += 1
            self.counts[k] -= 1
            s += self.helper()
            self.counts[k] += 1
        return s


sol = Solution()
test = main.UTest()
test.test_eq(sol.numTilePossibilities("V"), 1)
test.test_eq(sol.numTilePossibilities("AAB"), 8)
test.test_eq(sol.numTilePossibilities("AAABBC"), 188)
