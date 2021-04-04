extern crate rust_math_tools;
use rust_math_tools::is_prime;

fn main() {
    let mut num : u64 = 13;
    let mut prime_num : u32 = 6;  // Sixth prime is 13
    while prime_num < 10001 {
        num += 2;
        if is_prime(num) {
            prime_num += 1;
        }
    }
    println!("{} {}", num, prime_num);
}
// Answer: 104743
