extern crate rust_math_tools;
use rust_math_tools::is_palindrome;

use std::cmp;

fn main() {
    let mut largest : u64 = 0;
    for n1 in 100u64..1000u64 {
        for n2 in 100u64..1000u64 {
            if is_palindrome(n1 * n2) {
                largest = cmp::max(largest, n1 * n2)
            }
        }
    }
    println!("{}", largest)
}
// Answer: 906609
