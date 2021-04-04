extern crate rust_math_tools;
use rust_math_tools::is_prime;

fn main() {
    let mut sum : u64 = 0;
    for n in 2..2_000_000 {
        if is_prime(n) {
            sum += n;
        }
    }
    println!("{}", sum);
}
// Answer: 142913828922
