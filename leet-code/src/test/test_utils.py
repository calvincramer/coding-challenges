
import unittest

import utils as utils


class TestUtils(unittest.TestCase):
    def test_unsigned_bin_str_to_dec(self):
        f = utils.unsigned_bin_str_to_dec
        self.assertEqual(f(''), 0)
        self.assertEqual(f('0'), 0)
        self.assertEqual(f('1'), 1)
        self.assertEqual(f('10'), 2)
        self.assertEqual(f('101011100001110'), 22286)
